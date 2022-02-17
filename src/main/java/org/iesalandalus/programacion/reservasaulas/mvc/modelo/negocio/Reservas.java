// Esta clase es prácticamente idéntica a la clase Aulas

package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	private int capacidad;
	private int tamano;

	private Reserva[] arrayReserva;

	public Reservas(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		arrayReserva = new Reserva[capacidad];
		this.capacidad = capacidad;
	}

	public Reserva[] get() {
		return copiaProfundaReservas();
	}
	
	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaArrayReservas = new Reserva[capacidad];
		
		for (int i = 0; i < tamano; i++) {
			copiaArrayReservas[i] = arrayReserva[i];
		}
		
		return copiaArrayReservas;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if(reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		
		if(capacidadSuperada(buscarIndice(reserva))) {	
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}
		
		if(!tamanoSuperado(buscarIndice(reserva))) {
			throw new OperationNotSupportedException("ERROR: La reserva que desea insertar ya existe.");
		}
		
		arrayReserva[buscarIndice(reserva)] = new Reserva(reserva);
		tamano++;
	}
	
	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		
		if (tamanoSuperado(buscarIndice(reserva))) {
			return null;
		} else {
			return new Reserva(reserva);
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		
		if (tamanoSuperado(buscarIndice(reserva))) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
		
		desplazarUnaPosicionHaciaIzquierda(buscarIndice(reserva));
	}
	
	private int buscarIndice(Reserva reserva) {
		int indice = 0;
		boolean citaEncontrada = false;
		
		while (!tamanoSuperado(indice) && !citaEncontrada) { 		
			if (arrayReserva[indice].equals(reserva)) {
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

			arrayReserva[i] = arrayReserva[i + 1];
		}
		
		tamano--;
	}
	
	public String[] representar() throws OperationNotSupportedException {
		String[] representacion = new String[tamano];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = arrayReserva[i].toString();
		}
		
		if (representacion.length <= 0) {
			throw new OperationNotSupportedException("ERROR: Inserte primero una reserva para poder listar");
		}
		
		return representacion;
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) throws OperationNotSupportedException {
		Reserva[] reservasProfesor = new Reserva[tamano];
		
		int j = 0;
		for (int i = 0; i < tamano ; i++) {
			if(arrayReserva[i].getProfesor().equals(profesor)) {
				reservasProfesor[j++] = arrayReserva[i];
			}
		}
		
		if (reservasProfesor.length <= 0) {
			throw new OperationNotSupportedException("ERROR: Inserte primero una reserva para realizar dicha operación");
		}
		
		return reservasProfesor;
	}
	
	public Reserva[] getReservasAula(Aula aula) throws OperationNotSupportedException  {
		Reserva[] reservasAula = new Reserva[tamano];
		
		int j = 0;
		for (int i = 0; i < tamano ; i++) {
			if(arrayReserva[i].getAula().equals(aula)) {
				reservasAula[j++] = arrayReserva[i];
			}
		}
		
		if (reservasAula.length <= 0) {
			throw new OperationNotSupportedException("ERROR: Inserte primero una reserva para realizar dicha operación");
		}
		
		return reservasAula;
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia = new Reserva[capacidad];
		
		int j = 0;
		for (int i = 0; i < tamano ; i++) {
			if(arrayReserva[i].getPermanencia().equals(permanencia)) {
				reservasPermanencia[j++] = arrayReserva[i];
			}
		}
		
		return reservasPermanencia;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		int indice = 0;
		boolean disponible = true;
		
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}
		
		if(permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		
		 do {
			if ((arrayReserva[indice].getAula().equals(aula)) && (arrayReserva[indice].getPermanencia().equals(permanencia))) {
				disponible = false;
			}
			indice++;
		} while (!tamanoSuperado(indice));

		return disponible;
	}

}
