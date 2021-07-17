package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Factura;
import core.Model;

public class Factura_model extends Model {
	
	public Factura factura;
	
	public Factura_model () {
		super(); 
		this.factura = new Factura();
	}
	
	public void cargarNumFactura() {
		String sql = "SELECT MAX(1) FROM Factura";
		int num_factura = -1;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	num_factura  = rs.getInt(1);
		    	factura.setNumFactura(num_factura);
			}
		    rs.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
