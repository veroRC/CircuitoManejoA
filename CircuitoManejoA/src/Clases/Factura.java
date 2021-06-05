package Clases;

public class Factura extends Producto{
	private int numFactura;
	private String fecha;
	private Double Precio;
	private int cantidad;
	
	public Factura(String codigo,String nombre,int nFact,String fecha,Double precio,int cantidad){
		super(codigo,nombre);
		this.numFactura=nFact;
		this.fecha=fecha;
		this.Precio=precio;
		this.cantidad=cantidad;
	}
	
	public Factura(){}

	public int getNumFactura() {
		return numFactura;
	}

	public String getFecha() {
		return fecha;
	}

	public Double getPrecio() {
		return Precio;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	
}
