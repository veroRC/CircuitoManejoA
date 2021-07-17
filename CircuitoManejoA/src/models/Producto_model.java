package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.HashC;
import core.Model;
import clases.Producto;

public class Producto_model extends Model {
	
	public ArrayList<Producto> lista_productos;
	public HashC<Producto> hash_productos;
	
	public Producto_model () {
		super(); 
	}
	
	public void cargarProductos() {
		lista_productos = new ArrayList<Producto>();
		hash_productos = new HashC<Producto>(1000); 
		String sql = "SELECT * FROM Producto";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
				Producto producto = new Producto(rs.getString("codigo"),rs.getString("nombre"),rs.getDouble("precio"));
				this.lista_productos.add(producto);
				this.hash_productos.insert(producto.generarClaveHash(), producto);
			}
		    rs.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void insertarProducto(Producto producto) {
		String sql = "INSERT INTO Producto(codigo,nombre,precio) VALUES('"+producto.getCodigo()+"','"+producto.getNombre()+"',"+producto.getPrecio()+")";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);
		    System.out.println("Producto ingresado correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
