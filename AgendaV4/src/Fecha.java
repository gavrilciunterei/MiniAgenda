/**
 * Fecha
 * 
 * @author Amadeo
 */
public class Fecha {
	private short dia;
	private short mes;
	private int ano;
	
	public Fecha(int dia, int mes, int ano) {
		this(dia + "-" + mes + "-" + ano);
	}

	public Fecha(String fecha) {
		this(fecha, "dd-MM-yyyy");
	}
	
	public Fecha(String fecha, String format) {
		String[] datos = fecha.split("-");
		switch (format) {
		case "yyyy-MM-dd":
			dia = Short.parseShort(datos[2]);
			mes = Short.parseShort(datos[1]);
			ano = Integer.parseInt(datos[0]);
			break;
		case "MM-dd-yyyy":
			dia = Short.parseShort(datos[1]);
			mes = Short.parseShort(datos[0]);
			ano = Integer.parseInt(datos[2]);
			break;			
		default:
			dia = Short.parseShort(datos[0]);
			mes = Short.parseShort(datos[1]);
			ano = Integer.parseInt(datos[2]);
			break;
		}
	}

	public short getDia() {
		return dia;
	}

	public short getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	@Override
	public String toString() {
		return String.format("%02d-%02d-%04d", dia, mes, ano);
	}	

	public String toSQL() {
		return String.format("%04d-%02d-%02d", ano, mes, dia);
	}	
}
