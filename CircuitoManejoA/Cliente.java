package clases;

public class Cliente implements Comparable<Cliente> {
	private String dni;
	private String nombre;
	private String email;
	private String telefono;
	
	public Cliente(String dni, String nombre, String email, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	@Override
	public int compareTo(Cliente o) {
		return this.dni.compareTo(o.dni);
	}
}
