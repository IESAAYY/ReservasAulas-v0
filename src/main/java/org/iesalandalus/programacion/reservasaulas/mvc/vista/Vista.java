package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
/*
 * En esta clase se atrapa a todas las excepciones lanzadas anteriormente.
 * Tambi�n  se encarga de interactuar con el usuario sustituyendo a la 
 * funci�n de la clase main en tareas anteriores.
 */
public class Vista {
	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre valido";
	private static final String CORREO_VALIDO = "Correo valido";
	
	// Constantes no indicados en enunciado
	private static final String OPERACION_CORRECTA = "La operaci�n ha sido realizada correctamente.";
	private static final String INSERTAR_PRIMERO = "Inserte primero para realizar esta operaci�n.";
	private static final String LINEA_SEPARACION = "------------------------------------------------------------------------"
			+ "-------------------------------------------------------------------------------------------------------------";

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		int ordinalOpcion;

		System.out.println("    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("    | Gestor de reserva de aulas |");
		System.out.println("    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("");

		do {
			System.out.println(LINEA_SEPARACION);
			System.out.println("    =-=-=-=-=-=-=-=-=-");
			System.out.println("    |Men� de opciones|");
			System.out.println("    =-=-=-=-=-=-=-=-=-");
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			System.out.println(LINEA_SEPARACION);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		Consola.mostrarCabecera(Opcion.SALIR.getMensaje());
		controlador.terminar();
		System.out.println(LINEA_SEPARACION);
	}

	public void insertarAula() {
		Consola.mostrarCabecera(Opcion.INSERTAR_AULA.getMensaje());
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("");
			System.out.println(OPERACION_CORRECTA);
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	//Funci�n a�adida: Satar mensaje error en caso de usuario intente borrar sin aula(No indicado en enunciado)
	public void borrarAula() {
		Consola.mostrarCabecera(Opcion.BORRAR_AULA.getMensaje());
		try {	
			if (!controlador.existeAula()) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("");
			System.out.println(OPERACION_CORRECTA);
			}
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera(Opcion.BUSCAR_AULA.getMensaje());
		Aula aula;
		try {
			if (!controlador.existeAula()) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				aula = controlador.buscarAula(Consola.leerAula());
				System.out.println("");
				if (aula == null) {
					System.out.println(ERROR + "No se ha encontrado a ninguna aula con los datos introducidos.");
				} else {
					System.out.println(aula);
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

	// Atrapar excepci�n con try-catch en caso de que usuario intente listar array vac�o(no indicado en enunciado)	
	public void listarAulas() {
		Consola.mostrarCabecera(Opcion.BUSCAR_AULA.getMensaje());
		try {	
			String[] aula = controlador.representarAulas();

			for (int i = 0; i < aula.length; i++) {
				System.out.println(aula[i]);
			}
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	public void insertarProfesor() {
		Consola.mostrarCabecera(Opcion.INSERTAR_PROFESOR.getMensaje());

		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("");
			System.out.println(OPERACION_CORRECTA);
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}	
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera(Opcion.INSERTAR_PROFESOR.getMensaje());
		try {
			if (!controlador.existeProfesor()) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				controlador.borrarProfesor(Consola.leerProfesor());
				System.out.println("");
				System.out.println(OPERACION_CORRECTA);
			}
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	public void buscarProfesor() {
		Profesor profesor;
		Consola.mostrarCabecera(Opcion.BUSCAR_PROFESOR.getMensaje());

		try {	
			if (!controlador.existeProfesor()) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				profesor = controlador.buscarProfesor(Consola.leerProfesor());
				System.out.println("");
				if (profesor == null) {
					System.out.println(ERROR + "No se ha encontrado a ning�n profesor con los datos introducidos.");
				} else {
					System.out.println("");
				System.out.println(profesor);
				}
			}
		} catch (NullPointerException | IllegalArgumentException| OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}	
	}
	
	public void listarProfesores() {
		Consola.mostrarCabecera(Opcion.LISTAR_PROFESORES.getMensaje());	
		 try {
			 String[] profesores = controlador.representarProfesores();
			 for (int i = 0; i < profesores.length; i++) {
				 System.out.println(profesores[i]);
			 }
		 } catch (NullPointerException | IllegalArgumentException  e) {
			 System.out.println(e.getMessage());
			 System.out.println(e.getClass());
		 }
	}
	
	public void realizarReserva() {

		try {

			Profesor profesor = null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("");
			System.out.println("Reserva insertada correctamente, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO + ".");
			// Capturamos todas las posibles excepciones al hacer una reserva
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	private Reserva leerReserva(Profesor profesor) throws OperationNotSupportedException {
		Consola.mostrarCabecera("Realizar Reserva");

		String nombreProfesor;
		String nombreAula;
		String[] profesores = controlador.representarProfesores();
		String[] aulas = controlador.representarAulas();
		String correo = new String();
		String correoFinal = new String();

		Aula aula = null;
		Reserva reserva = null;
		Permanencia permanencia = null;
		
		boolean aulaRegistrada = false;
		boolean profesorRegistrado = false;
		
		try {
			nombreProfesor = Consola.leerNombreProfesor();
			nombreAula = Consola.leerNombreAula();
			
			for (int i = 0; i < profesores.length; i++) {
				String datosProfesores = profesores[i].toString();		
				if (nombreProfesor.equals(datosProfesores.substring(datosProfesores.indexOf('=') + 1, datosProfesores.indexOf(',')))) {
					profesorRegistrado = true;
					correo = datosProfesores.substring(datosProfesores.indexOf('=') + 1, datosProfesores.lastIndexOf(','));
					correoFinal = correo.replace(nombreProfesor + ", correo=", "");
				}
			}
			for (int j = 0; j < aulas.length; j++) {
				if (aulas[j].toString().replace("nombre Aula=", "").equals(nombreAula)) {
					aula = new Aula(nombreAula);
					aulaRegistrada = true;
				}
			}

			if (!aulaRegistrada || !profesorRegistrado) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				if (!aulaRegistrada) {
					System.out.println(ERROR + "No est� registrada el aula " + nombreAula + " en el sistema.");
				} else {
				}
				if (!profesorRegistrado) {
					System.out.println(ERROR + "No est� registrado el profesor " + nombreProfesor + " en el sistema.");
				} else {
					permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
					Profesor profesorALeer = new Profesor(nombreProfesor, correoFinal);
					reserva = new Reserva(profesorALeer, aula, permanencia);
				}
			}

		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return reserva;

	}
	
	public void anularReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {
			Profesor profesor = null;
			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Reserva anulada correctamente, " + NOMBRE_VALIDO + CORREO_VALIDO + ".");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		Consola.mostrarCabecera(Opcion.LISTAR_RESERVAS.getMensaje());
		
		try {
			String[] reservas = controlador.represerntarReservas();
			for (int i = 0; i < reservas.length; i++) {
				System.out.println(reservas[i]);
			}	
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

	public void listarReservasAula() {
		Consola.mostrarCabecera(Opcion.LISTAR_RESERVAS_AULA.getMensaje());

		try {
			Reserva[] reservasAula = controlador.getReservasAula(Consola.leerAula());
			System.out.println("");
			
			if (reservasAula.length <= 0) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				for (int i = 0; i < reservasAula.length; i++) {
					if (reservasAula[i] == null) {
						System.out.println("No se ha encontrado a ninguna reserva con los datos introducidos.");
					} else {
						System.out.println(reservasAula[i]);
					}
				}
			}

		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	public void listarReservasProfesor() {
		Consola.mostrarCabecera(Opcion.LISTAR_RESERVAS_PROFESOR.getMensaje());

		try {
			Reserva[] reservasProfesor = controlador.getReservasProfesor(Consola.leerProfesor());
			System.out.println("");
			
			if (reservasProfesor.length <= 0) {
				System.out.println(ERROR + INSERTAR_PRIMERO);
			} else {
				for (int i = 0; i < reservasProfesor.length; i++) {
					if (reservasProfesor[0] == null) {
						System.out.println("No se ha encontrado a ninguna reserva con los datos introducidos.");
					} else {
						System.out.println(reservasProfesor[i]);
					}
				}
			}

		}catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}
	
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera(Opcion.LISTAR_RESERVAS_PERMANECIA.getMensaje());
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas[0] != null) {
			for (Reserva reserva : reservas) {
				if (reserva != null)
					System.out.println(reserva);
			}
		} else {
			System.out.println("No se ha encontrado a ninguna reserva con los datos introducidos.");
		}
	}
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera(Opcion.CONSULTAR_DISPONIBILIDAD.getMensaje());
		try {
			Aula aula = Consola.leerAula();
			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
			if (controlador.consultarDisponibilidad(aula, permanencia)) {
				System.out.println(aula + " se encuentra disponible el dia " + permanencia.getDia() + ".");
			} else {
				System.out.println(aula + " no se encuentra disponible el dia " + permanencia.getDia() + ".");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

	
	
	
	
	
}
