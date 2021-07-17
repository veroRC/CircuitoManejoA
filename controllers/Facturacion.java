package controllers;

import clases.Producto;
import models.Producto_model;
import views.*;

public class Facturacion {
	
	public Producto_model producto_model;
	Facturacion_inicio vista_inicio;
	Facturacion_nuevoProducto vista_nuevoProducto;
	
	public Facturacion () {
		producto_model = new Producto_model();
	}
	
	public void inicio() {
		this.producto_model.cargarProductos();
		this.vista_inicio = new Facturacion_inicio(this);
		this.vista_inicio.load();
	}
	
	public void cargarProductos() {
		this.vista_inicio.close();
		this.vista_inicio.load();
	}
	
	public void nuevoProducto() {
		this.vista_nuevoProducto = new Facturacion_nuevoProducto(this);
		this.vista_nuevoProducto.load();
	}
	
	public void insertarProducto(Producto producto) {
		this.producto_model.insertarProducto(producto);
		this.vista_nuevoProducto.close();
		this.producto_model.cargarProductos();
		this.vista_inicio.actualizarProducto();
	}
}
