/**
 * Contacto
 * 
 * nombre
 * apellidos
 * fecha de nacimiento
 * salario
 * telefono
 * correo electronico
 * 
 * @author Amadeo
 *
 */
public class Contacto {
	private String nombre;
	private String apellidos;
	private Fecha nacido;
	private double salario;
	private String telefono;
	private String email;
	
	public static final int LEN_NOMBRE = 25;
	public static final int LEN_APELLIDOS = 50;
	public static final int LEN_TELEFONO = 15;
	public static final int LEN_EMAIL = 255;
		
	public Contacto(String nombre, String apellidos, Fecha nacido, double salario, String telefono, String email) {
		this.nombre = nombre.trim();
		this.apellidos = apellidos.trim();
		this.nacido = nacido;
		this.salario = salario;
		this.telefono = telefono.trim();
		this.email = email.trim();
	}

	/**
	 * DEBUG
	 * 
	 * @param nombre
	 */
	public Contacto(String nombre) {
		this.nombre = nombre;
		this.apellidos = "";
		this.nacido = new Fecha("01-01-0001");
		this.salario = 0.00;
		this.telefono = "";
		this.email = "";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos.trim();
	}

	public Fecha getNacido() {
		return nacido;
	}

	public void setNacido(Fecha nacido) {
		this.nacido = nacido;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTelefono() {
		return telefono.trim();
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	@Override
	public String toString() {
		return "Contacto"
				+ " [ " + nombre
				+ " | " + apellidos 
				+ " | " + nacido
				+ " | " + salario
				+ " | " + telefono 
				+ " | " + email
				+ " ] ";
	}	
}
