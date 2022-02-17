package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Profesor {

	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";
	private static final String ER_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(Profesor p) {
		if (p == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}

		setNombre(p.getNombre());
		setCorreo(p.getCorreo());
		setTelefono(p.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}

		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}

		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}

		if (correo.matches(ER_CORREO) == false) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}

		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono != null && telefono.matches(ER_TELEFONO) == false) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}
		
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		if (telefono == null || telefono == "") {
			return "nombre=" + nombre + ", correo=" + correo;
		} else {
			return "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono;
		}
	}

}
