package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Cliente;
import core.HashC;
import core.Model;

public class Cliente_model extends Model{
	public ArrayList<Cliente> lista_clientes;
	public HashC<Cliente> hash_clientes;
	
	public Cliente_model () {
		super(); 
	}
	
	public void cargarClientes() {
		lista_clientes = new ArrayList<Cliente>();
		hash_clientes = new HashC<Cliente>(1000); 
		String sql = "SELECT * FROM Cliente";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
				Cliente cliente = new Cliente(rs.getString("dni"),rs.getString("nombre"),rs.getString("email"),rs.getString("telefono"));
				this.lista_clientes.add(cliente);
				this.hash_clientes.insert(cliente.generarClaveHash(), cliente);
			}
		    rs.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void insertarCliente(Cliente cliente) {
		String sql = "INSERT INTO Cliente VALUES('"+cliente.getDni()+"','"+cliente.getNombre()+"','"+cliente.getEmail()+"','"+cliente.getTelefono()+"')";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);
		    System.out.println("Cliente ingresado correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
