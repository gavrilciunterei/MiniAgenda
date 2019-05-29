package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Contacto;
import dao.ContactoDAO;

import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private JPanel contentPane;
	private JTextField textNombreAnadir;
	private JTextField textNombreBuscar;
	private JPanel panelBuscar;
	private JPanel panelAnadir;
	private JTable tableResultados;

	private DefaultTableModel dtm;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JMenuItem mntmBuscar;
	private JMenuItem mntmAnadir;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		design();
		events();
	}
	
	private void design() {
		setTitle("MiniAgenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmBuscar = new JMenuItem("Buscar");
		mnArchivo.add(mntmBuscar);
		
		mntmAnadir = new JMenuItem("Añadir");
		mnArchivo.add(mntmAnadir);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});		
		mnArchivo.add(mntmSalir);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panelBuscar = new JPanel();
		contentPane.add(panelBuscar, "name_3121156496016");
		GridBagLayout gbl_panelBuscar = new GridBagLayout();
		gbl_panelBuscar.columnWidths = new int[]{0, 0, 0};
		gbl_panelBuscar.rowHeights = new int[]{0, 0, 0, 300, 0, 0};
		gbl_panelBuscar.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBuscar.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelBuscar.setLayout(gbl_panelBuscar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelBuscar.add(lblNombre, gbc_lblNombre);
		
		textNombreBuscar = new JTextField();
		GridBagConstraints gbc_textNombreBuscar = new GridBagConstraints();
		gbc_textNombreBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_textNombreBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombreBuscar.gridx = 1;
		gbc_textNombreBuscar.gridy = 0;
		panelBuscar.add(textNombreBuscar, gbc_textNombreBuscar);
		textNombreBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.EAST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 1;
		gbc_btnBuscar.gridy = 1;
		panelBuscar.add(btnBuscar, gbc_btnBuscar);
		
		JLabel lblResultados = new JLabel("Resultados:");
		GridBagConstraints gbc_lblResultados = new GridBagConstraints();
		gbc_lblResultados.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultados.gridx = 0;
		gbc_lblResultados.gridy = 2;
		panelBuscar.add(lblResultados, gbc_lblResultados);
		
		/*
		 * Defino el modelo de datos y se lo asigno a la tabla de resultados
		 */
		dtm = new DefaultTableModel();
		dtm.addColumn("Id");
		dtm.addColumn("Nombre");
		tableResultados = new JTable(dtm);
		tableResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(tableResultados);
		scrollPane.setViewportView(tableResultados);
		panelBuscar.add(scrollPane);
		
		GridBagConstraints gbc_tableResultados = new GridBagConstraints();
		gbc_tableResultados.insets = new Insets(0, 0, 5, 0);
		gbc_tableResultados.gridwidth = 2;
		gbc_tableResultados.fill = GridBagConstraints.BOTH;
		gbc_tableResultados.gridx = 0;
		gbc_tableResultados.gridy = 3;
		panelBuscar.add(scrollPane, gbc_tableResultados);
		
		panelAnadir = new JPanel();
		contentPane.add(panelAnadir, "name_3183139916358");
		GridBagLayout gbl_panelAnadir = new GridBagLayout();
		gbl_panelAnadir.columnWidths = new int[]{0, 0, 0};
		gbl_panelAnadir.rowHeights = new int[]{0, 0, 0};
		gbl_panelAnadir.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAnadir.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelAnadir.setLayout(gbl_panelAnadir);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre_1 = new GridBagConstraints();
		gbc_lblNombre_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre_1.anchor = GridBagConstraints.EAST;
		gbc_lblNombre_1.gridx = 0;
		gbc_lblNombre_1.gridy = 0;
		panelAnadir.add(lblNombre_1, gbc_lblNombre_1);
		
		textNombreAnadir = new JTextField();
		GridBagConstraints gbc_textNombreAnadir = new GridBagConstraints();
		gbc_textNombreAnadir.insets = new Insets(0, 0, 5, 0);
		gbc_textNombreAnadir.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombreAnadir.gridx = 1;
		gbc_textNombreAnadir.gridy = 0;
		panelAnadir.add(textNombreAnadir, gbc_textNombreAnadir);
		textNombreAnadir.setColumns(10);
		
		btnAnadir = new JButton("Añadir");
		GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
		gbc_btnAnadir.anchor = GridBagConstraints.EAST;
		gbc_btnAnadir.gridx = 1;
		gbc_btnAnadir.gridy = 1;
		panelAnadir.add(btnAnadir, gbc_btnAnadir);

	}

	private void events() {
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAnadir.setVisible(false);
				resetBuscar();
				panelBuscar.setVisible(true);
			}
		});

		mntmAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAnadir.setVisible(true);
				panelBuscar.setVisible(false);
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombreBuscar.getText();
				updateBuscar(nombre);
			}
		});		

		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombreAnadir.getText();
				if (nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe teclear un nombre");
				} else {
					ContactoDAO cd = new ContactoDAO();
					if (cd.add(new Contacto(nombre))) {
						JOptionPane.showMessageDialog(null, "Añadido: " + nombre);
					} else {
						JOptionPane.showMessageDialog(null, "No se ha añadido: " + nombre);
					}
				}
			}
		});	
		
		tableResultados.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	int id = (int) tableResultados.getValueAt(tableResultados.getSelectedRow(), 0);
				ContactoDAO cd = new ContactoDAO();
				Contacto c = cd.get(id);
				JOptionPane.showMessageDialog(null, "Seleccionado: [" + c.getId() + "] " + c.getNombre());
	        }
	    });
	}

	private void updateBuscar(String nombre) {
		ContactoDAO cd = new ContactoDAO();
		ArrayList<Contacto> lc = nombre.isEmpty() ? cd.getAll() : cd.get(nombre);
		dtm.setRowCount(0);
		for (Contacto c : lc) {
			dtm.addRow(new Object[] {c.getId(), c.getNombre()});
		}		
	}
	
	private void resetBuscar() {
		textNombreBuscar.setText("");
		updateBuscar("");
	}
	
}
