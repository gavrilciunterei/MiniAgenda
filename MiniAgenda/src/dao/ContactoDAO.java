package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import clases.Contacto;

public class ContactoDAO {
	
	private static Connection conn = Conexion.get();
	private Contacto c;
	
	/**
	 * Constructor
	 */
	public ContactoDAO() {
		c = null;
	}
	
	/**
	 * Obtengo un contacto conociendo su id
	 * 
	 * @param id
	 * @return null si no existe el contacto
	 */
	public Contacto get(int id) {
		c = null;
		try {
			String sql = "SELECT * FROM contacto WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Contacto(id, rs.getString("nombre"));
			}
		} catch (Exception e) {
		}
		return c;
	}
	
	/**
	 * Obtengo una lista de contactos que tienen el mismo nombre
	 * 
	 * @param nombre
	 * @return null si no hay ninguno coincidente
	 */
	public ArrayList<Contacto> get(String nombre) {
		ArrayList<Contacto> al = new ArrayList<>();
		try {
			String sql = "SELECT * FROM contacto WHERE nombre = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				al.add(new Contacto(rs.getInt("id"), rs.getString("nombre")));
			}
		} catch (Exception e) {
		}
		return al;
	}

	/**
	 * Obtengo una lista con todos los contactos
	 * 
	 * @return null si no hay ningún contacto
	 */
	public ArrayList<Contacto> getAll() {
		ArrayList<Contacto> al = new ArrayList<>();
		try {
			String sql = "SELECT * FROM contacto ORDER BY nombre";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				al.add(new Contacto(rs.getInt("id"), rs.getString("nombre")));
			}
		} catch (Exception e) {
		}
		return al;
	}

	/**
	 * Obtengo el mayor id de contacto
	 * 
	 * @return 0 si no se ha introducido ningún contacto aún
	 */
	public int getMaxId() {
		try {
			String sql = "SELECT max(id) FROM contacto";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Añado un nuevo contacto
	 * 
	 * @param c
	 * @return false si no se ha podido añadir el nuevo contacto
	 */
	public boolean add(Contacto c) {
		try {
			// Creo el nuevo identificador del contacto
			c.setId(getMaxId() + 1);
			
			// Añado el registro
			String sql = "INSERT INTO contacto"
					+ " (id, nombre)"
					+ " VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNombre());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// Esta excepción puede saltar cuando se añade una clave duplicada
			return false;
		}
	}
	
	/**
	 * Modifico un contacto. Si su id es 0 lo añado
	 * 
	 * @param c
	 * @return false si no he podido añadir/modificar el contacto
	 */
	public boolean set(Contacto c) {
		// Si el id es 0 entonces es un nuevo contacto
		if (c.getId() == 0) {
			return add(c);
		}
		
		// Modifico el registro
		try {
			String sql = "UPDATE contacto SET nombre = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			ps.setInt(2, c.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Elimino el contacto sabiendo su id
	 * 
	 * @param id
	 * @return false si no se ha podido eliminar
	 */
	public boolean delete(int id) {
		try {
			String sql = "DELETE FROM contacto WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Elimino el contacto
	 * 
	 * @param c
	 * @return false si no se ha podido eliminar
	 */
	public boolean delete(Contacto c) {
		return delete(c.getId());
	}

}
