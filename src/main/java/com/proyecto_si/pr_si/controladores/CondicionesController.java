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
import com.proyecto_si.pr_si.entidades.Condiciones;
import com.proyecto_si.pr_si.servicios.CondicionesService;


import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/condiciones", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CondicionesController {
    @Autowired
    CondicionesService condicionesService;

    @GetMapping(path = "{id}")
	public ResponseEntity<Condiciones> buscarPorId(@PathVariable("id") Long id) {
		Optional<Condiciones> condOptional = condicionesService.buscarPorId(id);

		if (!condOptional.isPresent()) {
			throw new ResourceNotFoundException("id no está en la BD");
		}else {
			return new ResponseEntity<>(condOptional.get(), HttpStatus.OK);
		} 
	}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Condiciones> crear(@RequestBody @Valid Condiciones condiciones) {
		Long id = condiciones.getId();
		if ((id != null)) {
			Optional<Condiciones> cOptional = condicionesService.buscarPorId(id);

			if (cOptional.isEmpty()) {
				Condiciones nuevaCondicion = condicionesService.crear(condiciones);
				URI uri = crearURICondicion(nuevaCondicion);
				return ResponseEntity.created(uri).body(nuevaCondicion);
			}
		}
		throw new WrongParameterException("Falta indicar id");
	}

		private URI crearURICondicion(Condiciones c) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(c.getId())
				.toUri();
	}

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Condiciones> modificar(@PathVariable("id") Long id, @RequestBody @Valid Condiciones condiciones) {
		Optional<Condiciones> condOptional = condicionesService.buscarPorId(id);

		if (!condOptional.isPresent()) {
			throw new ResourceNotFoundException("Id no está presente");
		}
		else {
			Condiciones condicionesNueva = condicionesService.modificar(condiciones);
			return new ResponseEntity<>(condicionesNueva, HttpStatus.OK);
		} 
	}

    @DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		Optional<Condiciones> condOptional = condicionesService.buscarPorId(id);

		if (!condOptional.isPresent()) {
			throw new ResourceNotFoundException("id no está presente");
		}
		else {
			condicionesService.eliminar(condOptional.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}

    //http://localhost:8080/api/condiciones?nombre=Mal%20estado%20de%20la%20carretera --- Nombre tiene que ser exacto
    @RequestMapping(params = "nombre", method = RequestMethod.GET)
	public ResponseEntity<List<Condiciones>> findByNombreCondicion(@RequestParam(name = "nombre", required = true) String nombre) {
		List<Condiciones> resultado = new ArrayList<>();
		resultado = condicionesService.findByNombreCondicion(nombre);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
