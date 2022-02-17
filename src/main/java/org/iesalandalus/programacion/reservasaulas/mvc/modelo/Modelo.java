package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	private static final int CAPACIDAD = 10;

	private Aulas aulas;
	private Profesores profesores;
	private Reservas reservas;

	public Modelo() {
		aulas = new Aulas(CAPACIDAD);
		profesores = new Profesores(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);

	}

	public Aula[] getAulas() {
		return aulas.get();
	}

	public int getNumAulas() {
		return aulas.getTamano();
	}

	public String[] representarAulas() throws OperationNotSupportedException {
		return aulas.representar();
	}

	public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}

	public int getNumProfesores() {
		return profesores.getTamano();
	}

	public String[] representarProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) throws OperationNotSupportedException {
		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public Reserva[] getReservas() {
		return reservas.get();
	}

	public int getNumReservas() {
		return reservas.getTamano();
	}

	public String[] representarReservas() throws OperationNotSupportedException {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reserva) {
		return reservas.buscar(reserva);
	}

	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}

	public Reserva[] getReservasAula(Aula aula) throws OperationNotSupportedException {
		return reservas.getReservasAula(aula);
	}

	public Reserva[] getReservasProfesor(Profesor profesor) throws OperationNotSupportedException {
		return reservas.getReservasProfesor(profesor);
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}
