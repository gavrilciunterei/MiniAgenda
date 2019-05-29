import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiVentana extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JEditorPane editorPane;
	private JButton btnSalir;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiVentana frame = new MiVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MiVentana() {
		setTitle("Mis ventanitas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(5, 42, 70, 15);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(72, 40, 227, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtNombre.getText();
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe introducir algún nombre");
				} else if (editorPane.getText().isEmpty()) {
					editorPane.setText(nombre);
				} else {
					editorPane.setText(editorPane.getText() + "\n" + nombre);
				}
				txtNombre.setText("");
			}
		});
		btnEnviar.setBounds(311, 37, 117, 25);
		contentPane.add(btnEnviar);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBounds(5, 78, 423, 181);
		contentPane.add(editorPane);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(311, 12, 117, 25);
		contentPane.add(btnSalir);
	}
}
