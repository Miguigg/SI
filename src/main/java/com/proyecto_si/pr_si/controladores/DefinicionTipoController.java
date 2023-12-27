package com.proyecto_si.pr_si.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyecto_si.pr_si.controladores.excepciones.ResourceNotFoundException;
import com.proyecto_si.pr_si.controladores.excepciones.WrongParameterException;
import com.proyecto_si.pr_si.entidades.DefinicionTipo;
import com.proyecto_si.pr_si.entidades.Vehiculo;
import com.proyecto_si.pr_si.servicios.DefinicionTipoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/definicion-tipo", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class DefinicionTipoController {
    @Autowired
    DefinicionTipoService definicionTipoService;

    @GetMapping(path = "{id}")
	public ResponseEntity<DefinicionTipo> buscarPorId(@PathVariable("id") Long id) {
		Optional<DefinicionTipo> defOptional = definicionTipoService.findById(id);

		if (!defOptional.isPresent()) {
			throw new ResourceNotFoundException("id no está en la base de datos");
		}else {
			return new ResponseEntity<>(defOptional.get(), HttpStatus.OK);
		} 
	}
    
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DefinicionTipo> crear(@RequestBody @Valid DefinicionTipo definicionTipo) {
		DefinicionTipo nuevaDefinicionTipo = definicionTipoService.crear(definicionTipo);
		URI uri = crearURIDefinicionTipo(nuevaDefinicionTipo);

		return ResponseEntity.created(uri).body(nuevaDefinicionTipo);
	}

		private URI crearURIDefinicionTipo(DefinicionTipo nuevaDefinicionTipo) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(nuevaDefinicionTipo.getId())
				.toUri();
	}

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DefinicionTipo> modificar(@PathVariable("dni") Long id, @RequestBody @Valid DefinicionTipo definicionTipo) {
		Optional<DefinicionTipo> defOptional = definicionTipoService.findById(id);

		if (!defOptional.isPresent()) {
			throw new ResourceNotFoundException("DNI no está en la DB");
		}
		else {
			DefinicionTipo nuevaDefinicion = definicionTipoService.modificar(definicionTipo);
			return new ResponseEntity<>(nuevaDefinicion, HttpStatus.OK);
		} 
	}
	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		Optional<DefinicionTipo> defOptional = definicionTipoService.findById(id);

		if (!defOptional.isPresent()) {
			throw new ResourceNotFoundException("DNI no está en la DB");
		}
		else {
			definicionTipoService.eliminar(defOptional.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}
	
	//http://127.0.0.1:8080/api/definicion-tipo?def=matado
    @RequestMapping(params = "def", method = RequestMethod.GET)
	public ResponseEntity<List<DefinicionTipo>> findByPatronDescripcionCondicion(@RequestParam(name = "def", required = true) String def) {
		List<DefinicionTipo> resultado = new ArrayList<>();
		resultado = definicionTipoService.findByPatronDescripcionCondicion(def);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

}
