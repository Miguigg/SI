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


@SpringBootApplication(scanBasePackages={
	"com.proyecto_si.pr_si.servicios", "com.proyecto_si.pr_si.controladores", "com.proyecto_si.pr_si.daos", "com.proyecto_si.pr_si.entidades"})
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
		String date_string = "11-09-2015";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(date_string);


		List<DefinicionTipo> listTipos = new ArrayList<>();
		DefinicionTipo definicionTipo = new DefinicionTipo("Se ha matado Juan");
		definicionTipo.setTipoAccidente(TipoAccidente.ALCANCE);
		definicionTipo = definicionTipoDAO.save(definicionTipo);
		listTipos.add(definicionTipo);


		List<Vehiculo> listVehiculo = new ArrayList<>();
		Vehiculo v1 = new Vehiculo("1222PCW","Frenos en mal estado", date); 
		v1.settipoVehiculo(TipoVehiculo.BUS);
		v1 = vehiculoDAO.save(v1);
		listVehiculo.add(v1);


		List<Conductor> listCondurtores = new ArrayList<>();
		Conductor c1 = new Conductor("19706812A",1,"Juan","Hombre");
		c1.setNivelEducativo(NivelEducativo.MEDIO);
		c1 = conductorDAO.save(c1);
		listCondurtores.add(c1);

		ParteSiniestro p1 = new ParteSiniestro(1,1,"12:00",listVehiculo,listCondurtores);
		p1 = parteSiniestroDAO.save(p1);

		List<Condiciones> listCondiciones = new ArrayList<>();
		Condiciones co1 = new Condiciones("Mal estado de la carretera","Salida de la calzada");
		co1.setTipoCondicion(TipoCondicion.CONDICIONES_LUMINICAS);
		co1 = condicionesDAO.save(co1);
		listCondiciones.add(co1);

		Accidente a1 = new Accidente(1300.0,"muy grave",date,"AM",listTipos, p1, listCondiciones);
		a1 = accidenteDAO.save(a1);
	}
}
