// Esta clase es prácticamente idéntica a la clase Aulas

package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	private int capacidad;
	private int tamano;

	private Profesor[] arrayProfesor;

	public Profesores(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		arrayProfesor = new Profesor[capacidad];
		this.capacidad = capacidad;
	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}
	
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaArrayProfesor = new Profesor[capacidad];
		
		for (int i = 0; i < tamano; i++) {
			copiaArrayProfesor[i] = arrayProfesor[i];
		}
		
		return copiaArrayProfesor;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if(profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		
		if(capacidadSuperada(buscarIndice(profesor))) {	
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}
		
		if(!tamanoSuperado(buscarIndice(profesor))) {
			throw new OperationNotSupportedException("ERROR: El profesor que desea insertar ya existe.");
		}
		
		arrayProfesor[buscarIndice(profesor)] = new Profesor(profesor);
		tamano++;
	}
	
	public Profesor buscar(Profesor profesor)  {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		// Algoritmo: Permite devolver datos del profesor introducido en método insertarProfesor de Vista
		for (int i = 0; i < getTamano(); i++) {
			if (get()[i].equals(profesor)) {
				System.out.println("Se ha encontrado un profesor con los datos introducidos.");
				System.out.print("Detalles del profesor: ");
				profesor = get()[i];		
			} 
		}
		
		if (tamanoSuperado(buscarIndice(profesor))) {
			return null;
		} else {
			return profesor;
		}
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		
		if (tamanoSuperado(buscarIndice(profesor))) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		
		desplazarUnaPosicionHaciaIzquierda(buscarIndice(profesor));
	}
	
	private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean citaEncontrada = false;
		
		while (!tamanoSuperado(indice) && !citaEncontrada) { 		
			if (arrayProfesor[indice].equals(profesor)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}

		return indice;
	}

	private boolean capacidadSuperada(int capacidad) {
		if (capacidad >= this.capacidad) {
			return true;
		} else {
			return false;
		}
	}

	private boolean tamanoSuperado(int tamano) {
		if (tamano >= this.tamano) {
			return true;
		} else {
			return false;
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano; i++) {
			arrayProfesor[i] = arrayProfesor[i + 1];
		}
		
		tamano--;
	}
	
	public String[] representar() {
		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = arrayProfesor[i].toString();
		}
		
		return representacion;
	}

}
