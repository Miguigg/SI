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
import com.proyecto_si.pr_si.entidades.Accidente;
import com.proyecto_si.pr_si.entidades.Conductor;
import com.proyecto_si.pr_si.servicios.AccidenteService;
import com.proyecto_si.pr_si.servicios.ConductorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/accidentes", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AccidenteController {
    @Autowired
    AccidenteService accidenteService;

    @GetMapping(path = "{id}")
	public ResponseEntity<Accidente> buscarPorId(@PathVariable("id") Long id) {
		Optional<Accidente> accidenteOpt = accidenteService.buscarPorId(id);

		if (!accidenteOpt.isPresent()) {
			throw new ResourceNotFoundException("id no está en la BD");
		}else {
			return new ResponseEntity<>(accidenteOpt.get(), HttpStatus.OK);
		} 
	}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Accidente> crear(@RequestBody @Valid Accidente accidente) {
		Long id = accidente.getId();
		if ((id != null)) {
			Optional<Accidente> accOptional = accidenteService.buscarPorId(id);

			if (accOptional.isEmpty()) {
				Accidente nuevoAccidente = accidenteService.crear(accidente);
				URI uri = crearURIAccidente(nuevoAccidente);
				return ResponseEntity.created(uri).body(nuevoAccidente);
			}
		}
		throw new WrongParameterException("Falta indicar id");
	}

		private URI crearURIAccidente(Accidente accidente) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{dni}")
				.buildAndExpand(accidente.getId())
				.toUri();
	}

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Accidente> modificar(@PathVariable("id") Long id, @RequestBody @Valid Accidente accidente) {
		Optional<Accidente> accidOptional = accidenteService.buscarPorId(id);

		if (!accidOptional.isPresent()) {
			throw new ResourceNotFoundException("Id no está presente");
		}
		else {
			Accidente nuevoAccidente = accidenteService.modificar(accidente);
			return new ResponseEntity<>(nuevoAccidente, HttpStatus.OK);
		} 
	}

    @DeleteMapping(path = "{id}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
		Optional<Accidente> accOptional = accidenteService.buscarPorId(id);

		if (!accOptional.isPresent()) {
			throw new ResourceNotFoundException("id no está presente");
		}
		else {
			accidenteService.eliminar(accOptional.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}

	// http://localhost:8080/api/accidentes?patron=%22grave%22
    @RequestMapping(params = "patron", method = RequestMethod.GET)
	public ResponseEntity<List<Accidente>> findByPatronDescripcionGravedad(@RequestParam(name = "patron", required = true) String patron) {
		List<Accidente> resultado = new ArrayList<>();
		resultado = accidenteService.findByPatronDescripcionGravedad(patron);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

    @RequestMapping(params = "costeEstimado", method = RequestMethod.GET)
	public ResponseEntity<List<Accidente>> findByCosteEstimado(@RequestParam(name = "costeEstimado", required = true) Double costeEstimado) {
		List<Accidente> resultado = new ArrayList<>();
		resultado = accidenteService.findByCosteEstimado(costeEstimado);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
