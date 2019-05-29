import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Agenda Versión 4
 * 
 * Base de datos SQLite
 * 
 * Nota: los métodos que comienzan con _ no deben ser utilizados.
 * 
 * @author Amadeo
 */
public class Agenda {

	private Connection conn;

	/**
	 * Constructor
	 * 
	 * @throws SQLException 
	 */
	public Agenda() throws SQLException {
		conn = new Conexion().get();

		// Si no existe la tabla la crea
		String sql = String.format("CREATE TABLE IF NOT EXISTS agenda (" +
							"nombre VARCHAR(%d) PRIMARY KEY NOT NULL," + 
							"apellidos VARCHAR(%d) NOT NULL," +
							"nacido DATE NOT NULL," +
							"salario DECIMAL NOT NULL," +
							"telefono VARCHAR(%d) NOT NULL," +
							"email VARCHAR(%d) NOT NULL)", 
						Contacto.LEN_NOMBRE,
						Contacto.LEN_APELLIDOS,
						Contacto.LEN_TELEFONO,
						Contacto.LEN_EMAIL);
		conn.createStatement().executeUpdate(sql);
	}
	
	/**
	 * Añade un contacto a la agenda
	 * 
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public boolean anadir(Contacto c) throws SQLException {
		String sql = "INSERT INTO agenda"
				+ " (nombre, apellidos, nacido, salario, telefono, email)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getApellidos());
		ps.setDate(3, Date.valueOf(c.getNacido().toSQL()));
		ps.setDouble(4, c.getSalario());
		ps.setString(5, c.getTelefono());
		ps.setString(6, c.getEmail());
		try {
			ps.executeUpdate();
		} catch (Exception e) {
			// Esta excepción puede saltar cuando se añade una clave duplicada
			return false;
		}
		return true;
	}
	
	/**
	 * Busca un contacto mediante el nombre
	 * 
	 * @param buscar
	 * @return el contacto o null si no se encuentra
	 * @throws SQLException
	 */
	public Contacto _buscar(String buscar) throws SQLException {
		Contacto c = null;
		String sql = "SELECT * FROM agenda WHERE nombre = \"" + buscar + "\"";
		IO.println("SQL DEBUG: " + sql); // DEBUG
		ResultSet rs = conn.createStatement().executeQuery(sql);
		if (rs.next()) {
			c = new Contacto(
					rs.getString("nombre"), 
					rs.getString("apellidos"),
					new Fecha(rs.getDate("nacido").toString(), "yyyy-MM-dd"),
					rs.getDouble("salario"),
					rs.getString("telefono"),
					rs.getString("email")
				);
		}
		return c;
	}

	public Contacto buscar(String buscar) throws SQLException {
		Contacto c = null;
		String sql = "SELECT * FROM agenda WHERE nombre = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, buscar);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c = new Contacto(
					rs.getString("nombre"), 
					rs.getString("apellidos"),
					new Fecha(rs.getDate("nacido").toString(), "yyyy-MM-dd"),
					rs.getDouble("salario"),
					rs.getString("telefono"),
					rs.getString("email")
				);
		}
		return c;
	}

	/**
	 * Borrar un registro
	 * 
	 * @param buscar
	 * @return
	 * @throws SQLException
	 */
	public boolean eliminar(String buscar) throws SQLException {
		String sql = "DELETE FROM agenda WHERE nombre = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, buscar);
		return ps.executeUpdate() > 0;
	}

	/**
	 * Devuelve el número de registros almacenados
	 * 
	 * @return
	 * @throws SQLException
	 */
	public long registros() throws SQLException {
		String sql = "SELECT count(*) FROM agenda";
		ResultSet rs = conn.createStatement().executeQuery(sql);
		rs.next();
		return rs.getInt(1);
	}

	/**
	 * Cierra la agenda
	 * 
	 * @throws SQLException
	 */
	public void cerrar() throws SQLException {
		conn.close();
	}
	
	/**
	 * DEBUG: Borra todos los registros de la agenda
	 * 
	 * @throws SQLException
	 */
	public void reset() throws SQLException {	
		String sql = "DELETE FROM agenda";
		conn.createStatement().executeUpdate(sql);
	}
	
	/**
	 * DEBUG: Muestra toda la agenda ordenada por nombre y apellidos
	 * 
	 * @throws SQLException
	 */
	public void show() throws SQLException {
		String sql = "SELECT * FROM agenda ORDER BY nombre, apellidos";
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while (rs.next()) {
			Contacto c = new Contacto(
					rs.getString("nombre"), 
					rs.getString("apellidos"),
					new Fecha(rs.getDate("nacido").toString(), "yyyy-MM-dd"),
					rs.getDouble("salario"),
					rs.getString("telefono"),
					rs.getString("email")
				);	
			IO.println(c);
		}
	}

}
