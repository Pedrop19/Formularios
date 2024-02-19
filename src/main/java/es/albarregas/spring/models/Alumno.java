package es.albarregas.spring.models;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Alumno {
	
	@Min(value = 0, message = "{alumno.errorID}")
	private Integer id;
	@NotEmpty(message = "{alumno.errorNombre}")
	private String nombre;
	private String apellidos;
	@Email(message = "{alumno.errorEmail}")
	private String email;
	private boolean tieneBeca;
	private String avatar;
	
	public Alumno() {
	}

	public Alumno(Integer id, String nombre, String apellidos, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public Alumno(@Min(0) Integer id, @NotEmpty String nombre, String apellidos, @Email String email,
			boolean tieneBeca) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.tieneBeca = tieneBeca;
	}

	
	public Alumno(@Min(0) Integer id, @NotEmpty String nombre, String apellidos, @Email String email,
			boolean tieneBeca, String avatar) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.tieneBeca = tieneBeca;
		this.avatar = avatar;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
