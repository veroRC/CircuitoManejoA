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
	
	public int generarClaveHash() {
		int suma = 0;
		for (int i=0; i<this.dni.length(); i++) {
			suma = suma + (int)this.dni.charAt(i);
		}
		return suma;
	}

	@Override
	public int compareTo(Cliente o) {
		return this.dni.compareTo(o.dni);
	}
}
