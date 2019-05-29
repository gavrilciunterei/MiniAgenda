package clases;

public class Contacto implements Comparable<Contacto> {

	private int id;
	private String nombre;
	
	public Contacto(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Contacto(String nombre) {
		this(0, nombre);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public int compareTo(Contacto c) {
		return nombre.compareTo(c.getNombre());
	}
	
	
}
