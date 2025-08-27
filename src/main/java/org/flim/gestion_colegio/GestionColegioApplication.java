package org.flim.gestion_colegio;

import org.flim.gestion_colegio.entity.Estudiante;
import org.flim.gestion_colegio.service.EstudianteService;
import org.flim.gestion_colegio.service.IEstudianteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


//@SpringBootApplication
public class GestionColegioApplication implements CommandLineRunner {

	@Autowired
	private EstudianteService estudianteService;

	private static final Logger logger = LoggerFactory.getLogger(GestionColegioApplication.class);

	String salto = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("INICIALIZANDO LA APLICACION ");
		SpringApplication.run(GestionColegioApplication.class, args);
		logger.info("FINALIZANDO LA APLICACION ");
	}

	@Override
	public void run(String... args) throws Exception {
		gestionColegioApp();
	}

	private void gestionColegioApp(){
		logger.info("-Bienvenido a la aplicacion de gestion de lista de Estudiantes-");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(" ");

		}
	}
	private int mostrarMenu(Scanner consola){
		logger.info("""
				-Aplicacion-
				1.Listar Estudiantes
				2.Buscar Estudiantes
				3.Agregar Estudiantes
				4.Modificar Estudiantes
				5.Dar de baja un Estudiante
				6.Salir
				""");
		var opcion =  Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
			logger.info(salto+"---Lista de Estudiantes---"+salto);
				List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString()+salto));
			}
			case 2 -> {
				logger.info(salto + "---Buscar Estudiante por Codigo---"+ salto);
				var codigo = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteService.buscarEstudianteporId(codigo);
				if (estudiante != null){
					logger.info("Estudiante encontrado" + estudiante + salto);
				}else{
					logger.info("Estudiante No encontrado" + estudiante + salto);
				}
			}
			case 3 -> {
				logger.info(salto+"---Agregar un Estudiante---"+salto);
				logger.info("Ingrese el nombre del Estudiante: ");
				var nombreEstudiante = consola.nextLine();
				logger.info("Ingrese el apellido del Estudiante: ");
				var apellidoEstudiante = consola.nextLine();
				logger.info("Ingrese el correo del Estudiante: ");
				var correo = consola.nextLine();
				logger.info("Ingrese el genero del Estudiante: ");
				var genero = consola.nextLine();
				logger.info("Ingrese el edad del Estudiante: ");
				var edad = Integer.parseInt(consola.nextLine());
				var estudiante = new Estudiante();
				estudiante.setNombreEstudiante(nombreEstudiante);
				estudiante.setApellidoEstudiante(apellidoEstudiante);
				estudiante.setCorreo(correo);
				estudiante.setGenero(genero);
				estudiante.setEdad(edad);
				estudianteService.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado " + estudiante+salto);
			}
			case 4 -> {
				logger.info(salto+"---Modificar un Estudiante---"+salto);
				logger.info("Agregue el código del estudiante a modificar: ");
				var codigo = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteService.buscarEstudianteporId(codigo);
				if (estudiante != null){
					logger.info("Ingrese el nombre del Estudiante: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del Estudiante: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el telefono del Estudiante: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo del Estudiante: ");
					var correo = consola.nextLine();
					logger.info("Ingrese el genero del Estudiante: ");
					var genero = consola.nextLine();
					logger.info("Ingrese el edad del Estudiante: ");
					var edad = Integer.parseInt(consola.nextLine());
					estudiante.setNombreEstudiante(nombre);
					estudiante.setApellidoEstudiante(apellido);
					estudiante.setCorreo(correo);
					estudiante.setGenero(genero);
					estudiante.setEdad(edad);
					estudianteService.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado" + estudiante +salto);
				}else {
					logger.info("Estudiante No encontrado" + estudiante + salto);
				}
			}
			case 5 -> {
				logger.info(salto+"---Dar de Baja un Estudiante---"+salto);
				logger.info(salto+"Ingrese el código del estudiante a eliminar: " + salto);
				var codigo = Integer.parseInt(consola.nextLine());
				var estudiante = estudianteService.buscarEstudianteporId(codigo);
				if (estudiante != null){
					estudianteService.bajaEstudiante(estudiante);
					logger.info("Estudiante eliminado, adios "+ estudiante + salto);
				}else{
					logger.info("Estudiante No encontrado " + estudiante + salto);
				}
			}
			case 6 ->{
				logger.info("Fiuuuuumba " + salto + salto);
				salir = true;
			}
			default -> logger.info("Opción invalida");

		}
		return salir;
	}
}
