package com.proyecto_si.pr_si.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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
import com.proyecto_si.pr_si.entidades.Vehiculo;
import com.proyecto_si.pr_si.servicios.VehiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/vehiculos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

	@GetMapping()
	public ResponseEntity<List<Vehiculo>> buscarTodos() {
		List<Vehiculo> resultado = new ArrayList<>();
		resultado = vehiculoService.buscarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

    @GetMapping(path = "{licencePlate}")
	public ResponseEntity<List<Vehiculo>> buscarPorMatricula(@PathVariable("licencePlate") String licencePlate) {
        List<Vehiculo> resultado = new ArrayList<>();
		resultado = vehiculoService.buscarPorMatricula(licencePlate);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

    @RequestMapping(params = "defectoVehiculo", method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> buscarPorDefecto(@RequestParam(name = "defectoVehiculo", required = true) String defectoVehiculo) {
        List<Vehiculo> resultado = new ArrayList<>();
		resultado = vehiculoService.buscarPorDefecto(defectoVehiculo);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

    @RequestMapping(params = "{fechaMatriculacion}", method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> buscarFechaMatriculacion(@RequestParam(name = "fechaMatriculacion", required = true) Date fechaMatriculacion) {
        List<Vehiculo> resultado = new ArrayList<>();
		resultado = vehiculoService.buscarFechaMatriculacion(fechaMatriculacion);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	//http://127.0.0.1:8080/api/vehiculos?patron=Frenos
    @RequestMapping(params = "patron", method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> buscarPorPatroDefecto(@RequestParam(name = "patron", required = true) String patron) {
        List<Vehiculo> resultado = new ArrayList<>();
		resultado = vehiculoService.buscarPorPatroDefecto(patron);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}


    
	@PutMapping(path = "{licencePlate}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehiculo> modificar(@PathVariable String licencePlate, @RequestBody @Valid Vehiculo vehiculo) {
		List<Vehiculo> vehiculos = vehiculoService.buscarPorMatricula(licencePlate);

		if (vehiculos.isEmpty()) {
			throw new ResourceNotFoundException("El vehiculo no está en la DB");
		}
		else {
			Vehiculo nuevoVehiculo = vehiculoService.modificar(vehiculo);
			return new ResponseEntity<>(nuevoVehiculo, HttpStatus.OK);
		} 
	}

    @DeleteMapping(path = "{licencePlate}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("licencePlate") String licencePlate) {
		List<Vehiculo> vehiculos = vehiculoService.buscarPorMatricula(licencePlate);

		if (vehiculos.isEmpty()) {
			throw new ResourceNotFoundException("El vehiculo no está en la DB");
		}
		else {
			vehiculoService.eliminar(vehiculos.get(0));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
	}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehiculo> crear(@RequestBody @Valid Vehiculo vehiculo) {
		Vehiculo nuevoVehiculo = vehiculoService.crear(vehiculo);
		URI uri = crearURIVehiculo(nuevoVehiculo);

		return ResponseEntity.created(uri).body(nuevoVehiculo);
	}

		private URI crearURIVehiculo(Vehiculo v) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{licencePlate}")
				.buildAndExpand(v.getlicencePlate())
				.toUri();
	}
}
