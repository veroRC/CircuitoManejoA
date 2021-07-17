package clases;

import java.util.ArrayList;

public class Factura implements Comparable<Factura> {
	private int numFactura;
	private Cliente cliente;
	private String fecha;
	private ArrayList<Producto> productos;
	
	public Factura(int numFactura, Cliente cliente, String fecha, ArrayList<Producto> productos){
		this.numFactura = numFactura;
		this.cliente = cliente;
		this.fecha = fecha;
		this.productos = productos;
	}
	
	public Factura(){
	}

	public int getNumFactura() {
		return numFactura;
	}
	
	public void setNumFactura(int setNumFactura) {
		this.numFactura = setNumFactura;
	}

	public String getFecha() {
		return fecha;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public ArrayList<Producto> getProductos() {
		return this.productos;
	}

	@Override
	public int compareTo(Factura o) {
		return this.numFactura - o.numFactura;
	}
	
	
}
