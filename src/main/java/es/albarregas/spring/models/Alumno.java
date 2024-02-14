package es.albarregas.spring.models;

import java.util.Objects;

public class Alumno {
	
	private Integer id;
	private String nombre;
	private String apellidos;
	private String email;
	private boolean tieneBeca;
	
	public Alumno() {
	}

	public Alumno(Integer id, String nombre, String apellidos, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public Alumno(Integer id, String nombre, String apellidos, String email, boolean tieneBeca) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.tieneBeca = tieneBeca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getTieneBeca() {
		return tieneBeca;
	}

	public void setTieneBeca(boolean tieneBeca) {
		this.tieneBeca = tieneBeca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, email, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", tieneBeca="
				+ tieneBeca + "]";
	}
	
	

}
