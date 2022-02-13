import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class VistaDatos extends JFrame {

	private JPanel contentPane;
	private JTable table_datos;
	private HashMap<String, int[]> personas;
	private Set<String> grupo1;
	private Set<String> grupo2;
	private JTable table_grupos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaDatos frame = new VistaDatos();
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
	public VistaDatos() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//SCROLLPANE QUE CONTIENE LA TABLA DE DATOS
		JScrollPane scrollPane_datos = new JScrollPane();
		scrollPane_datos.setBounds(22, 88, 277, 335);
		scrollPane_datos.setVisible(false);
		contentPane.add(scrollPane_datos);
		
		//TABLA DE DATOS
		table_datos = new JTable();
		scrollPane_datos.setViewportView(table_datos);
		table_datos.setEnabled(false);
		table_datos.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_datos.setBackground(Color.WHITE);
		table_datos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Deporte", "M\u00FAsica", "Espect\u00E1culo", "Ciencia"
			}
		));
		table_datos.setToolTipText("");
		
		
		//SCROLLPANE QUE CONTIENE LA TABLA DE GRUPOS
		JScrollPane scrollPane_grupos = new JScrollPane();
		scrollPane_grupos.setBounds(335, 88, 245, 335);
		scrollPane_grupos.setVisible(false);
		contentPane.add(scrollPane_grupos);
		
		//TABLA DE GRUPOS
		table_grupos = new JTable();
		table_grupos.setEnabled(false);
		scrollPane_grupos.setViewportView(table_grupos);
		
		
		//BOTON VER DATOS
		JButton btn_verDatos = new JButton("Ver datos");
		btn_verDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo_datos = (DefaultTableModel)table_datos.getModel();
				agregarDatosATabla(personas,modelo_datos);
				mostrarScrollPane(scrollPane_datos);
				deshabilitarBoton(btn_verDatos);
			}
		});
		btn_verDatos.setBounds(53, 26, 131, 23);
		contentPane.add(btn_verDatos);
		
		//BOTON VER GRUPOS
		JButton btn_mostrarGrupos = new JButton("Mostrar grupos");
		btn_mostrarGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo_grupos = (DefaultTableModel)table_grupos.getModel();
				agregarGrupoATabla(modelo_grupos, grupo1, "Grupo 1");
				agregarGrupoATabla(modelo_grupos, grupo2, "Grupo 2");
				mostrarScrollPane(scrollPane_grupos);
				deshabilitarBoton(btn_mostrarGrupos);
			}
		});
		btn_mostrarGrupos.setBounds(381, 26, 131, 23);
		contentPane.add(btn_mostrarGrupos);
		
	}
	
	//METODOS PRINCIPALES
	public void recibirDatos(HashMap<String, int[]> personas) {
		this.personas = personas;
	}

	public void recibirGrupo1(Set<String> grupo1) {
		this.grupo1 = grupo1;
	}
	
	public void recibirGrupo2(Set<String> grupo2) {
		this.grupo2 = grupo2;
	}
	
	//METODOS AUXILIARES
	private void agregarDatosATabla(HashMap<String, int[]> personas, DefaultTableModel modelo) {
		Set<String> nombres = personas.keySet();
		for(String nombre: nombres) {
			Object[] fila = {nombre,personas.get(nombre)[0],personas.get(nombre)[1],personas.get(nombre)[2],personas.get(nombre)[3]};
			modelo.addRow(fila);
		}
	}
	
	private void deshabilitarBoton(JButton btn_verDatos) {
		btn_verDatos.setEnabled(false);
	}

	private void mostrarScrollPane(JScrollPane scrollPane_datos) {
		scrollPane_datos.setVisible(true);
	}
	
	private void agregarGrupoATabla(DefaultTableModel modelo_grupos, Set<String> grupo, String columna) {
		modelo_grupos.addColumn(columna, grupo.toArray());
	}
	
}
