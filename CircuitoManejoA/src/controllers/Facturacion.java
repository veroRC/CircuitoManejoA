package controllers;

import clases.Cliente;
import clases.Producto;
import models.Cliente_model;
import models.Factura_model;
import models.Producto_model;
import views.*;

public class Facturacion {
	
	public Producto_model producto_model;
	public Cliente_model cliente_model;
	public Factura_model factura_model;
	Facturacion_inicio vista_inicio;
	Facturacion_nuevoProducto vista_nuevoProducto;
	Facturacion_nuevoCliente vista_nuevoCliente;
	
	public Facturacion () {
		producto_model = new Producto_model();
		cliente_model = new Cliente_model();
		factura_model = new Factura_model();
	}
	
	public void inicio() {
		this.producto_model.cargarProductos();
		this.cliente_model.cargarClientes();
		this.factura_model.cargarNumFactura();
		this.vista_inicio = new Facturacion_inicio(this);
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
	
	public void nuevoCliente() {
		this.vista_nuevoCliente= new Facturacion_nuevoCliente(this);
		this.vista_nuevoCliente.load();
	}
	
	public void insertarCliente(Cliente cliente) {
		this.cliente_model.insertarCliente(cliente);
		this.vista_nuevoCliente.close();
		this.cliente_model.cargarClientes();
		this.vista_inicio.actualizarCliente();
	}
}
