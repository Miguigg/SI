package com.proyecto_si.pr_si;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

import com.proyecto_si.pr_si.daos.AccidenteDAO;
import com.proyecto_si.pr_si.daos.CondicionesDAO;
import com.proyecto_si.pr_si.daos.ConductorDAO;
import com.proyecto_si.pr_si.daos.DefinicionTipoDAO;
import com.proyecto_si.pr_si.daos.ParteSiniestroDAO;
import com.proyecto_si.pr_si.daos.VehiculoDAO;
import com.proyecto_si.pr_si.entidades.Accidente;
import com.proyecto_si.pr_si.entidades.Condiciones;
import com.proyecto_si.pr_si.entidades.Conductor;
import com.proyecto_si.pr_si.entidades.DefinicionTipo;
import com.proyecto_si.pr_si.entidades.ParteSiniestro;
import com.proyecto_si.pr_si.entidades.Vehiculo;
import com.proyecto_si.pr_si.entidades.Enumerados.NivelEducativo;
import com.proyecto_si.pr_si.entidades.Enumerados.TipoAccidente;
import com.proyecto_si.pr_si.entidades.Enumerados.TipoCondicion;
import com.proyecto_si.pr_si.entidades.Enumerados.TipoVehiculo;

import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication
public class PrSiApplication implements CommandLineRunner {

	@Autowired
	AccidenteDAO accidenteDAO;

	@Autowired
	CondicionesDAO condicionesDAO;

	@Autowired
	ConductorDAO conductorDAO;

	@Autowired
	DefinicionTipoDAO definicionTipoDAO;

	@Autowired
	ParteSiniestroDAO parteSiniestroDAO;

	@Autowired
	VehiculoDAO vehiculoDAO;

	public static void main(String[] args) {
		SpringApplication.run(PrSiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		crearEntidades();
	}

	private void crearEntidades() throws ParseException {
		String date_string = "12-12-2007";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(date_string);


		List<DefinicionTipo> listTipos = new ArrayList<>();
		DefinicionTipo definicionTipo = new DefinicionTipo("Se ha matado paco");
		definicionTipo.setTipoAccidente(TipoAccidente.ATROPELLO);
		definicionTipo = definicionTipoDAO.save(definicionTipo);
		listTipos.add(definicionTipo);


		List<Vehiculo> listVehiculo = new ArrayList<>();
		Vehiculo v1 = new Vehiculo("5836PCT","Dirección torcida", date); 
		v1.settipoVehiculo(TipoVehiculo.FURGON);
		v1 = vehiculoDAO.save(v1);
		listVehiculo.add(v1);


		List<Conductor> listCondurtores = new ArrayList<>();
		Conductor c1 = new Conductor("49707812A",7,"Paco","Hombre");
		c1.setNivelEducativo(NivelEducativo.MEDIO);
		c1 = conductorDAO.save(c1);
		listCondurtores.add(c1);

		ParteSiniestro p1 = new ParteSiniestro(3,2,"9:00",listVehiculo,listCondurtores);
		p1 = parteSiniestroDAO.save(p1);

		List<Condiciones> listCondiciones = new ArrayList<>();
		Condiciones co1 = new Condiciones("Mala luz","La luz del anochecer le cegó");
		co1.setTipoCondicion(TipoCondicion.CONDICIONES_LUMINICAS);
		co1 = condicionesDAO.save(co1);
		listCondiciones.add(co1);

		Accidente a1 = new Accidente(1000.0,"muy grave",date,"AM",listTipos, p1, listCondiciones);
		a1 = accidenteDAO.save(a1);
	}
}
