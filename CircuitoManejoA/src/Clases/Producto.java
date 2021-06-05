package Clases;

public class Producto {
	private String cod;
	private String nombre;
	
	public Producto(String cod,String nombre){
		this.cod=cod;
		this.nombre=nombre;
	}
	
	public Producto(){
	}
	
	public String getCod(){
		return cod;
	}
	
	public String getNombre(){
		return nombre;
	}
	
}
