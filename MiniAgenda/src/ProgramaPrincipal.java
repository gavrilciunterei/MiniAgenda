import dao.Conexion;
import vista.VentanaPrincipal;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		new Conexion();
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
	}

}
