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
	final private String CONN_URI = "jdbc:sqlite:Agenda.db";
	
	/**
	 * Conector
	 */
	private Connection conn = null;

	/**
	 * Constructor de la conexión
	 */
	public Conexion() {
		try {
			conn = DriverManager.getConnection(CONN_URI);
			conn.setAutoCommit(true);
			/**
			 * En SQLite existe la posibilidad de activar el control de integridad referencial
			 * 
			 *	Statement stmt = conn.createStatement();
			 *	String sql = "PRAGMA foreign_keys=ON";
			 *  stmt.updateQuery(sql); 
			 */ 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve el conector de la base de datos
	 * 
	 * @return
	 */
	public Connection get() { 
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
