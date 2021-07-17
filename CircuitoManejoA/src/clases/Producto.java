package clases;

public class Producto implements Comparable<Producto> {
	private String codigo;
	private String nombre;
	private Double precio;
	
	public Producto(String codigo, String nombre, Double precio){
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Producto(String codigo){
		this.codigo = codigo;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Double getPrecio(){
		return this.precio;
	}
	
	public int generarClaveHash() {
		int suma = 0;
		for (int i=0; i<this.codigo.length(); i++) {
			suma = suma + (int)this.codigo.charAt(i);
		}
		return suma;
	}

	@Override
	public int compareTo(Producto o) {
		return this.codigo.compareTo(o.codigo);
	}
}
