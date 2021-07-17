package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	static Connection con = null;
	
	public Database() {
		if (con == null) {
			Database.conectar();
		}
	}
	
	public static void conectar(){
		try{
			con = DriverManager.getConnection("jdbc:sqlite:circuito.db");
			System.out.println("Conexion exitosa");
		}catch(SQLException ex){
			System.out.println("No se ha podido establecer la conexion "+ex);
		}
	}
	
	public Connection getConexion() {
		return con;
	}
	
	public void cerrarConexion(){
		try{
			con.close();
		}catch(SQLException ex){
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void dropTables() {
		String sql = "DROP TABLE Factura_det";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Factura_det eliminadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		sql = "DROP TABLE Factura;";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Factura eliminadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		sql = "DROP TABLE Producto;";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Producto eliminadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		sql = "DROP TABLE Cliente;";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Cliente eliminadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void createTables() {
		String sql = "CREATE TABLE Cliente ("
				+ "dni	TEXT NOT NULL,"
				+ "nombre	TEXT NOT NULL,"
				+ "email	TEXT NOT NULL,"
				+ "telefono	INTEGER NOT NULL,"
				+ "PRIMARY KEY(dni)"
				+ ");";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Cliente creadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		
		sql = "CREATE TABLE Producto ("
				+ "codigo	TEXT NOT NULL,"
				+ "nombre	TEXT NOT NULL,"
				+ "precio	NUMERIC NOT NULL,"
				+ "PRIMARY KEY(codigo)"
				+ ");";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Producto creadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		
		sql = "CREATE TABLE Factura ("
				+ "num_factura	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "dni TEXT NOT NULL,"
				+ "fecha TEXT,"
				+ "FOREIGN KEY(dni) REFERENCES Cliente(dni)"
				+ ");";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Factura creadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
		
		sql = "CREATE TABLE Factura_det ("
				+ "n_fact	INTEGER NOT NULL,"
				+ "cantidad	INTEGER NOT NULL,"
				+ "cod_prod	TEXT NOT NULL,"
				+ "FOREIGN KEY(n_fact) REFERENCES Factura(num_factura),"
				+ "PRIMARY KEY(n_fact,cod_prod),"
				+ "FOREIGN KEY(cod_prod) REFERENCES Producto(codigo)"
				+ ");";
		try {
			Statement stmt = con.createStatement();
		    stmt.execute(sql);
		    System.out.println("Tabla Factura_det creadas correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void agregarAutosDefault() {
		String sql;
		try {
			Statement stmt = con.createStatement();
			sql = "INSERT INTO Producto VALUES ('AB1-951','Chevrolet Rojo',30.00);";
		    stmt.execute(sql);
		    sql = "INSERT INTO Producto VALUES ('V5T-541','mazdacx30',25.00);";
		    stmt.execute(sql);
		    sql = "INSERT INTO Producto VALUES ('V7P-541','kia picanto',42.00);";
		    stmt.execute(sql);
		    sql = "INSERT INTO Producto VALUES ('G43-852','chrevrolet 30',60.00);";
		    stmt.execute(sql);
		    System.out.println("Autos agregados correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	public void agregarClientesDefault() {
		String sql;
		try {
			Statement stmt = con.createStatement();
			sql = "INSERT INTO Cliente VALUES ('46859236','Eduardo Gallegos','egalllgos@gmail.com','985632145');";
		    stmt.execute(sql);
		    sql = "INSERT INTO Cliente VALUES ('46864236','Valentina Calapuja','vcala@gmail.com','988762325');";
		    stmt.execute(sql);
		    sql = "INSERT INTO Cliente VALUES ('76829236','Dionicio Beltran','beltrans@gmail.com','962632145');";
		    stmt.execute(sql);
		    System.out.println("Autos agregados correctamente");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
