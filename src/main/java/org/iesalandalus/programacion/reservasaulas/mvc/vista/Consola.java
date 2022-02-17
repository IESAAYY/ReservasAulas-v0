package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {
	}
	
	public static void mostrarMenu() {
		for (int i = 0; i < Opcion.values().length; i++) {
			System.out.println(Opcion.values()[i]);
		}
	}
	
	public static void mostrarCabecera(String opcion) {
		String formato = "%0" + opcion.length() + "d%n";
		System.out.print(String.format(formato, 0).replace("0", "*"));
		System.out.printf("%s%n", opcion);	
		System.out.println(String.format(formato, 0).replace("0", "*"));
	}
	
	public static int elegirOpcion() {
		int opcion;
		
		do {
			System.out.println("");
			System.out.print("Introduzca una opción (0 - 15): ");
			opcion = Entrada.entero();
		} while (Opcion.esOrdinalValido(opcion) == false);
		
		return opcion;
	}
	
	public static Aula leerAula() {
		String nombre;
		
		System.out.print("Introduzca el nombre del aula para realizar la operación: ");
		nombre = Entrada.cadena();
		
		return new Aula(nombre);
	}
	
	public static String leerNombreAula() {
		String nombre;
		
		System.out.print("Introduzca el nombre del aula: ");
		nombre = Entrada.cadena();
		
		return nombre;
	}
 	
	public static Profesor leerProfesor() {
		String nombre, correo, telefono;

		
		System.out.print("Introduzca el nombre del profesor: ");
		nombre = Entrada.cadena();
		
		System.out.print("Introduzca el correo del profesor: ");
		correo = Entrada.cadena();
		
		System.out.print("Introduzca el telefono del profesor: ");
		telefono = Entrada.cadena();
		
		if (telefono == null || telefono.trim().isEmpty()) {
			return new Profesor(nombre, correo);
		} else {
		return new Profesor(nombre, correo, telefono);	
		}
	}
	
	public static String leerNombreProfesor() {
		String nombreProfesor;
		
		System.out.print("Introduzca el nombre del profesor: ");
		nombreProfesor = Entrada.cadena();
		
		return nombreProfesor;
	}
	
	public static Tramo leerTramo() {
		int opcion;
		Tramo tramo = null;

		do {
			System.out.println("Elija el tramo de la reserva ");
			System.out.println("0.  Mañana");
			System.out.println("1.  Tarde");
			System.out.print("Introduzca una opción (0-1): ");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion >= Tramo.values().length);

		tramo = Tramo.values()[opcion];

		return tramo;
	}
	
	public static LocalDate leerDia() {
		LocalDate fecha = null;
		boolean fValida = false;

		do {
			try {
				System.out.println("Introduzca el día, el mes, y el año en el siguiente formato ");
				System.out.print("dd/MM/yyyy" + " (Ejemplo: 17/01/2022): ");
				fecha = LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
				fValida = true;
			} catch (DateTimeParseException e) {
				fValida = false;
			}
			System.out.println("");
		} while (!fValida);

		return fecha;
	}

}
