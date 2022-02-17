package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;


import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private int capacidad;
	private int tamano;

	private Aula[] arrayAula;

	public Aulas(int capacidad) {
		if(capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		arrayAula = new Aula[capacidad];
		this.capacidad = capacidad;
	}

	public Aula[] get() {
		return copiaProfundaAulas();
	}
	
	private Aula[] copiaProfundaAulas() {
		Aula[] copiaArrayAula = new Aula[capacidad];
		
		for (int i = 0; i < tamano; i++) {
			copiaArrayAula[i] = arrayAula[i];
		}
		
		return copiaArrayAula;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		
		if(capacidadSuperada(buscarIndice(aula))) {	
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
		
		if(!tamanoSuperado(buscarIndice(aula))) {
			throw new OperationNotSupportedException("ERROR: El aula que desea insertar ya existe.");
		}
		
		arrayAula[buscarIndice(aula)] = new Aula(aula);
		tamano++;
	}
	
	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		
		if (tamanoSuperado(buscarIndice(aula))) {
			return null;
		} else {
			return new Aula(aula);
		}
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		
		if (tamanoSuperado(buscarIndice(aula))) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
		
		desplazarUnaPosicionHaciaIzquierda(buscarIndice(aula));
	}
	
	/*
	 * !citaEncontrada: bandera para optimizar el código(permite parar el
	 * algoritmo al encontrar índice)
	 */
	private int buscarIndice(Aula aula) {
		int indice = 0;
		boolean citaEncontrada = false;
		
		while (!tamanoSuperado(indice) && !citaEncontrada) { 		
			if (arrayAula[indice].equals(aula)) {
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
	
	/*
	 * Método: valor elemento indice eliminado, cada elemento tras parámetro indice es
	 * desplazado una posición hacia izquierda
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < tamano; i++) {

			arrayAula[i] = arrayAula[i + 1];
		}
		
		tamano--;
	}
	
	
	// Declaración de una excepción en caso de que usuario intente listar un array vacío(no indicado en enunciado) 
	public String[] representar() throws OperationNotSupportedException {
		String[] representacion = new String[tamano];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			representacion[i] = arrayAula[i].toString();
		}
		
		if(representacion.length <= 0) {
			throw new OperationNotSupportedException("ERROR: Inserte primero un aula para realizar dicha operación");
		}
		
		return representacion;
	}

}
