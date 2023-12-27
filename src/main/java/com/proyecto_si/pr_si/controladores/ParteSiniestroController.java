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
import com.proyecto_si.pr_si.entidades.Conductor;
import com.proyecto_si.pr_si.entidades.ParteSiniestro;
import com.proyecto_si.pr_si.servicios.ConductorService;
import com.proyecto_si.pr_si.servicios.ParteSiniestroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/parteSiniestro", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ParteSiniestroController {
    @Autowired
    ParteSiniestroService parteSiniestroService;


	@GetMapping()
	public ResponseEntity<List<ParteSiniestro>> buscarTodos() {
		List<ParteSiniestro> resultado = new ArrayList<>();
		resultado = parteSiniestroService.buscarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}


    @GetMapping(path = "{id}")
	public ResponseEntity<ParteSiniestro> buscarPorId(@PathVariable("id") Long id) {
		Optional<ParteSiniestro> pOptional = parteSiniestroService.buscarPorId(id);

		if (!pOptional.isPresent()) {
			throw new ResourceNotFoundException("El parte no está en la BD");
		}else {
			return new ResponseEntity<>(pOptional.get(), HttpStatus.OK);
		} 
	}


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParteSiniestro> crear(@RequestBody @Valid ParteSiniestro parteSiniestro) {
		ParteSiniestro nuevoParteSiniestro = parteSiniestroService.crear(parteSiniestro);
		URI uri = crearURIParte(nuevoParteSiniestro);

		return ResponseEntity.created(uri).body(nuevoParteSiniestro);
	}

		private URI crearURIParte(ParteSiniestro parteSiniestro) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(parteSiniestro.getId())
				.toUri();
	}

	//http://127.0.0.1:8080/api/parteSiniestro?numVehiculos=2
    @RequestMapping(params = "numVehiculos", method = RequestMethod.GET)
	public ResponseEntity<List<ParteSiniestro>> findByNumVehiculos(@RequestParam(name = "numVehiculos", required = true) int numVehiculos) {
		List<ParteSiniestro> resultado = new ArrayList<>();
		resultado = parteSiniestroService.findByNumVehiculos(numVehiculos);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	//http://127.0.0.1:8080/api/parteSiniestro?numPasajeros=3
    @RequestMapping(params = "numPasajeros", method = RequestMethod.GET)
	public ResponseEntity<List<ParteSiniestro>> findByNumPasajeros(@RequestParam(name = "numPasajeros", required = true) int numPasajeros) {
		List<ParteSiniestro> resultado = new ArrayList<>();
		resultado = parteSiniestroService.findByNumPasajeros(numPasajeros);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		Optional<ParteSiniestro> parteSiniestroOptional = parteSiniestroService.buscarPorId(id);

		if (!parteSiniestroOptional.isPresent()) {
			throw new ResourceNotFoundException("No existe este parte");
		}
		else {
			parteSiniestroService.eliminar(parteSiniestroOptional.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParteSiniestro> modificar(@PathVariable("dni") Long id, @RequestBody @Valid ParteSiniestro parteSiniestro) {
		Optional<ParteSiniestro> parteOpcional = parteSiniestroService.buscarPorId(id);

		if (!parteOpcional.isPresent()) {
			throw new ResourceNotFoundException("El parte no existe");
		}
		else {
			ParteSiniestro nuevoParte = parteSiniestroService.modificar(parteSiniestro);
			return new ResponseEntity<>(nuevoParte, HttpStatus.OK);
		} 
	}
}
