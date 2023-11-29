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
import com.proyecto_si.pr_si.servicios.ConductorService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/conductores", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ConductorController {
    @Autowired
    ConductorService conductorService;

    @GetMapping(path = "{dni}")
	public ResponseEntity<Conductor> buscarPorDNI(@PathVariable("dni") String dni) {
		Optional<Conductor> conductor = conductorService.buscarPorDNI(dni);

		if (!conductor.isPresent()) {
			throw new ResourceNotFoundException("DNI no está en la DB");
		}else {
			return new ResponseEntity<>(conductor.get(), HttpStatus.OK);
		} 
	}

    //http://localhost:8080/api/conductores?anho=1
	@RequestMapping(params = "anho", method = RequestMethod.GET)
	public ResponseEntity<List<Conductor>> buscarPorAnho(@RequestParam(name = "anho", required = true) int anho) {
		List<Conductor> resultado = new ArrayList<>();
		resultado = conductorService.buscarPorAnho(anho);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping(path = "{dni}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("dni") String dni) {
		Optional<Conductor> conductor = conductorService.buscarPorDNI(dni);

		if (!conductor.isPresent()) {
			throw new ResourceNotFoundException("DNI no está en la DB");
		}
		else {
			conductorService.eliminar(conductor.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}

	@PutMapping(path = "{dni}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conductor> modificar(@PathVariable("dni") String dni, @RequestBody @Valid Conductor conductor) {
		Optional<Conductor> conductorOptional = conductorService.buscarPorDNI(dni);

		if (!conductorOptional.isPresent()) {
			throw new ResourceNotFoundException("DNI no está en la DB");
		}
		else {
			Conductor nuevoConductor = conductorService.modificar(conductor);
			return new ResponseEntity<>(nuevoConductor, HttpStatus.OK);
		} 
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conductor> crear(@RequestBody @Valid Conductor conductor) {
		String dni = conductor.getDNI();
		if ((dni != null) && !dni.isBlank()) {
			Optional<Conductor> conductorOptional = conductorService.buscarPorDNI(dni);

			if (conductorOptional.isEmpty()) {
				Conductor nuevoConductor = conductorService.crear(conductor);
				URI uri = crearURICliente(nuevoConductor);
				return ResponseEntity.created(uri).body(nuevoConductor);
			}
		}
		throw new WrongParameterException("Falta indicar DNI");
	}

		private URI crearURICliente(Conductor conductor) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{dni}")
				.buildAndExpand(conductor.getDNI())
				.toUri();
	}
}
