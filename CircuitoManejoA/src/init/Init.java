package init;

import java.awt.EventQueue;

import controllers.Facturacion;
import core.Database;

public class Init {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*
					Database db = new Database();
					db.dropTables();
					db.createTables();
					db.agregarAutosDefault();
					*/
					
					Facturacion facturacion = new Facturacion();
					facturacion.inicio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
