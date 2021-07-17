package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.Model;
import clases.Producto;

public class Producto_model extends Model {
	
	public ArrayList<Producto> lista_productos;
	
	public Producto_model () {
		super();
		this.lista_productos = new ArrayList<Producto>(); 
	}
	
	public void cargarProductos() {
		String sql = "SELECT * FROM Producto";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
				Producto producto = new Producto(rs.getString("codigo"),rs.getString("nombre"),rs.getDouble("precio"));
				this.lista_productos.add(producto);
			}
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void insertarProducto(Producto producto) {
		String sql = "INSERT INTO Producto(codigo,nombre,precio) VALUES(?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, producto.getCodigo());
			stmt.setString(2, producto.getNombre());
			stmt.setDouble(3, producto.getPrecio());
		    stmt.execute(sql);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
