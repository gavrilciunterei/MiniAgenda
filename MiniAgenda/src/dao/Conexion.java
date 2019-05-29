package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexión a la base de datos SQLite llamada Agenda
 * 
 * @author Amadeo
 * @version 0.1
 */
public class Conexion {

	/**
	 * Nombre de la base de datos
	 */
	final private String CONN_URI = "jdbc:sqlite:MiniAgenda.sqlite";
	
	/**
	 * Conector
	 */
	private static Connection conn = null;

	/**
	 * Constructor de la conexión
	 */
	public Conexion() {
		if (conn != null) return;

		try {
			conn = DriverManager.getConnection(CONN_URI);
			conn.setAutoCommit(true);
			createSchema();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creo el esquema de la base de datos
	 */
	private void createSchema() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS contacto (" +
					"id INTEGER PRIMARY KEY NOT NULL, " +
					"nombre VARCHAR(50) NOT NULL)";
			conn.createStatement().executeUpdate(sql);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve el conector de la base de datos
	 * 
	 * @return
	 */
	public static Connection get() { 
		return conn;
	}

	/**
	 * Cierra la conexión
	 */
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}