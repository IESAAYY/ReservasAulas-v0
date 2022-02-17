package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	
	
	
	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new NullPointerException("Error: Modelo inexistente");
		}		
		if (vista == null) {
			throw new NullPointerException("Error: Vista inexistente");
		}
		
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("Programa finalizado");
	}
	
	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		modelo.insertarAula(aula);
	}
	
	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		modelo.borrarAula(aula);
	}
	
	public Aula buscarAula(Aula aula) {
		return modelo.buscarAula(aula);
	}
	
	public String[] representarAulas() throws OperationNotSupportedException {
		return modelo.representarAulas();
	}
	
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
	}
	
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}
	
	public Profesor buscarProfesor(Profesor profesor) throws OperationNotSupportedException {
		return modelo.buscarProfesor(profesor);
	}
	
	public String[] representarProfesores() {
		return modelo.representarProfesores();
	}
	
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}
	
	public String[] represerntarReservas() throws OperationNotSupportedException {
		return modelo.representarReservas();
	}
	
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		 modelo.anularReserva(reserva);
	}
	
	public Reserva[] getReservasAula(Aula aula) throws OperationNotSupportedException {
		return modelo.getReservasAula(aula);
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) throws OperationNotSupportedException {
		return modelo.getReservasProfesor(profesor);
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return modelo.getReservasPermanencia(permanencia);
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}
	
	public boolean existeAula() {
		if (modelo.getNumAulas() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean existeProfesor() {
		if (modelo.getNumProfesores() > 0) {
			return true;
		}
		return false;
	}
	
	

}
