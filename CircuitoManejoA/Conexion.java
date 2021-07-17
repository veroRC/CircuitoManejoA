package clases;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conexion {
	static final String Controlador="org.sqlite.JDBC";
	static Connection con=null;
	
	public static Connection Conectar() throws ClassNotFoundException{
		
		try{
			Class.forName(Controlador);
			//con=DriverManager.getConnection("jdbc:sqlite:C:\Users\Rodrigo\Documents\NetBeansProjects\FacturacionTestingFinal\facturadatabase.db"");
			con=DriverManager.getConnection("jdbc:sqlite:facturadatabase.db");
			System.out.println("Conexion exitosa");
		}catch(SQLException ex){
			System.out.println("No se ha podido establecer la conexion"+ex);
		}
		return con;
	}
	
	public void CerrarConexion(){
		try{
			con.close();
		}catch(SQLException ex){
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
