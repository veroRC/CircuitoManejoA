package core;

import java.sql.Connection;

public class Model {
	protected Database db;
	protected Connection con;
	
	public Model () {
		this.db = new Database();
		this.con = db.getConexion();
	}
}
