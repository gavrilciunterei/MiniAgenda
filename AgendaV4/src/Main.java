import java.sql.SQLException;

public class Main {
	
	private static final boolean DEBUG = true; // DEBUG 
	
	/**
	 * DEBUG
	 * 
	 * @param a
	 * @throws SQLException
	 */
	public static void inicializar(Agenda a) throws SQLException {
		a.reset();
		
		a.anadir(new Contacto("An\"a", "Mota", new Fecha(1, 1, 2000), 100.00, "915432145", "ana@espanya.es"));
		a.anadir(new Contacto("Luis", "Rato", new Fecha(1, 12, 1998), 250.00, "+34612345678", "luis@espanha.es"));
		
		IO.println("** Mostramos todo");
		a.show();
		
		IO.println("** Buscamos a Ana");
		IO.println(a.buscar("Ana"));
		
		IO.println("** Eliminamos a Ana");
		IO.println(a.eliminar("Ana"));

		IO.println("** Buscamos a Ana");
		IO.println(a.buscar("Ana"));

		IO.println("** Mostramos todo");
		a.show();
	}

	/**
	 * Menú de la agenda
	 * 
	 * @param a
	 * @return
	 * @throws SQLException
	 */
	public static boolean menu(Agenda a) throws SQLException {
		boolean continuar = true;
		
		if (DEBUG) {
			IO.print("i|m|");
		}
		IO.print("Añadir|Buscar|Eliminar|Salir");
		switch (IO.readString().toLowerCase().charAt(0)) {
		case 'a':			
			IO.print("Nombre ? ");
			String nombre = IO.readString();
			IO.print("Apellidos ? ");
			String apellidos = IO.readString();
			IO.print("Nacido año  ? ");
			int ano = IO.readInt();
			IO.print("Nacido mes  ? ");
			short mes = IO.readShort();
			IO.print("Nacido día  ? ");
			short dia = IO.readShort();
			Fecha nacido = new Fecha(dia, mes, ano);
			IO.print("Salario ? ");
			double salario = IO.readDouble();
			IO.print("Teléfono ? ");
			String telefono = IO.readString();
			IO.print("Correo electrónico ? ");
			String email = IO.readString();
			if (a.anadir(new Contacto(nombre, apellidos, nacido, salario, telefono, email))) {
				IO.println("Añadido");
			} else {
				IO.println("No añadido");
			}
			break;
		case 'b':
			IO.print("Nombre ? ");
			Contacto c = a.buscar(IO.readString());
			if (c == null) {
				IO.println("No encontrado");
			} else {
				IO.println(c);
			}
			break;
		case 'e':
			IO.print("Nombre ? ");
			if (a.eliminar(IO.readString())) {
				IO.println("Eliminado");
			} else {
				IO.println("No eliminado");
			}
			break;
		case 'm':
			if (DEBUG) {
				a.show(); // Muestra toda la agenda
			}
			break;
		case 'i':
			if (DEBUG) {
				inicializar(a); // Inicia el ejemplo
			}
			break;
		case 's':
			continuar = false;
			break;
		default:
			IO.println("Seleccione una opcion correcta");
		}
								
		return continuar;
	}

	/**
	 * Programa principal
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		//Locale.setDefault(Locale.US);
		
		Agenda a = new Agenda();	
		while (menu(a));
		a.cerrar();	
	}

}
