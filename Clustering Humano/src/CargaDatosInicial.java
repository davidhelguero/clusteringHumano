import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CargaDatosInicial extends JDialog {
	private JTextField textField_nombre;
	Clustering clustering;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CargaDatosInicial dialog = new CargaDatosInicial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CargaDatosInicial() {
		
		clustering = new Clustering();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			
			//LABEL INFORMATIVO
			JLabel lblEntreY = new JLabel("Cargue las personas. Cuando termine, presione Finalizar");
			lblEntreY.setBounds(34, 11, 390, 46);
			getContentPane().add(lblEntreY);
			
			
			textField_nombre = new JTextField();
			textField_nombre.setToolTipText("No puede estar en blanco. No se aceptan nombres repetidos");
			textField_nombre.setColumns(10);
			textField_nombre.setBounds(204, 59, 98, 20);
			getContentPane().add(textField_nombre);
			
			
			JComboBox comboBox_deporte = new JComboBox();
			comboBox_deporte.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			comboBox_deporte.setToolTipText("1 = menor inter\u00E9s. \r\n5 = mayor inter\u00E9s");
			comboBox_deporte.setMaximumRowCount(5);
			comboBox_deporte.setBounds(204, 89, 98, 22);
			comboBox_deporte.setSelectedIndex(-1);
			getContentPane().add(comboBox_deporte);
			
			
			JComboBox comboBox_musica = new JComboBox();
			comboBox_musica.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			comboBox_musica.setToolTipText("1 = menor inter\u00E9s. 5 = mayor inter\u00E9s");
			comboBox_musica.setMaximumRowCount(5);
			comboBox_musica.setBounds(204, 120, 98, 22);
			comboBox_musica.setSelectedIndex(-1);
			getContentPane().add(comboBox_musica);
				
			
			JComboBox comboBox_espectaculo = new JComboBox();
			comboBox_espectaculo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			comboBox_espectaculo.setToolTipText("1 = menor inter\u00E9s. 5 = mayor inter\u00E9s");
			comboBox_espectaculo.setMaximumRowCount(5);
			comboBox_espectaculo.setBounds(204, 149, 98, 22);
			comboBox_espectaculo.setSelectedIndex(-1);
			getContentPane().add(comboBox_espectaculo);
			
			
			JComboBox comboBox_ciencia = new JComboBox();
			comboBox_ciencia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
			comboBox_ciencia.setToolTipText("1 = menor inter\u00E9s. 5 = mayor inter\u00E9s");
			comboBox_ciencia.setMaximumRowCount(5);
			comboBox_ciencia.setBounds(204, 180, 98, 22);
			comboBox_ciencia.setSelectedIndex(-1);
			getContentPane().add(comboBox_ciencia);
		
			
			JButton btn_finalizar = new JButton("Finalizar");;
			
			//BOTON CARGAR
			JButton btn_cargar = new JButton("Cargar");
			btn_cargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!textField_nombre.getText().isBlank() && !(comboBox_deporte.getSelectedIndex()<0) && !(comboBox_musica.getSelectedIndex()<0) && !(comboBox_espectaculo.getSelectedIndex()<0) && !(comboBox_ciencia.getSelectedIndex()<0)) {
						agregarPersona(comboBox_deporte, comboBox_musica, comboBox_espectaculo, comboBox_ciencia);
						vaciarCuadros(comboBox_deporte, comboBox_musica, comboBox_espectaculo, comboBox_ciencia);
						
						btn_finalizar.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(getContentPane(), "El nombre no puede estar vacío y debe seleccionar un número para cada tema");
					}
				}
			});
			buttonPane.add(btn_cargar);
			
			//BOTON FINALIZAR
			btn_finalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(clustering.getPersonas().size() < 2)
						JOptionPane.showMessageDialog(getContentPane(), "Ingrese más de una persona");
					else {
						VistaDatos vistaDatos = new VistaDatos();
						generarGrupos();
						enviarDatosA(vistaDatos);
						enviarGrupo1A(vistaDatos);
						enviarGrupo2A(vistaDatos);
						cerrarVentana();
						abrirVentanaSiguiente(vistaDatos);
					}
				}
			});
			btn_finalizar.setActionCommand("Ok");
			buttonPane.add(btn_finalizar);
			getRootPane().setDefaultButton(btn_finalizar);
			btn_finalizar.setEnabled(false);
			}
			
		}
		
		//LABELS INDICATIVOS
		{		
			JLabel lblNewLabel_1 = new JLabel("Deportes:");
			lblNewLabel_1.setBounds(134, 93, 64, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(130, 68, 64, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("M\u00FAsica:");
			lblNewLabel_1.setBounds(134, 118, 64, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Espect\u00E1culos:");
			lblNewLabel_1.setBounds(111, 143, 87, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Ciencia:");
			lblNewLabel_1.setBounds(134, 184, 64, 14);
			getContentPane().add(lblNewLabel_1);
		}
		
		//METODOS AUXILIARES
		private void vaciarCuadros(JComboBox comboBox_deporte, JComboBox comboBox_musica, JComboBox comboBox_espectaculo, JComboBox comboBox_ciencia) {
			comboBox_deporte.setSelectedIndex(-1);
			comboBox_musica.setSelectedIndex(-1);
			comboBox_espectaculo.setSelectedIndex(-1);
			comboBox_ciencia.setSelectedIndex(-1);
			textField_nombre.setText("");
		}

		private void agregarPersona(JComboBox comboBox_deporte, JComboBox comboBox_musica,
				JComboBox comboBox_espectaculo, JComboBox comboBox_ciencia) {
			clustering.agregarPersona(textField_nombre.getText(), comboBox_deporte.getSelectedIndex()+1, comboBox_musica.getSelectedIndex()+1, comboBox_espectaculo.getSelectedIndex()+1, comboBox_ciencia.getSelectedIndex()+1);
		}
		
		private void abrirVentanaSiguiente(VistaDatos vistaDatos) {
			vistaDatos.setVisible(true);
		}

		private void cerrarVentana() {
			dispose();
		}

		private void enviarGrupo2A(VistaDatos vistaDatos) {
			vistaDatos.recibirGrupo2(clustering.getGrupo2());
		}

		private void enviarGrupo1A(VistaDatos vistaDatos) {
			vistaDatos.recibirGrupo1(clustering.getGrupo1());
		}

		private void enviarDatosA(VistaDatos vistaDatos) {
			vistaDatos.recibirDatos(clustering.getPersonas());
		}

		private void generarGrupos() {
			clustering.generarGrupos();
		}
	}

