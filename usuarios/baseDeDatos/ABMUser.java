package baseDeDatos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ABMUser {

	private JFrame frame;
	private JTextField pass;
	private JTextField user;
	private JComboBox<String> comboBox;
	private JLabel lblAcccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMUser window = new ABMUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ABMUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 329, 149);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{73, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		lblAcccion = new JLabel("Accion");
		GridBagConstraints gbc_lblAcccion = new GridBagConstraints();
		gbc_lblAcccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcccion.anchor = GridBagConstraints.EAST;
		gbc_lblAcccion.gridx = 0;
		gbc_lblAcccion.gridy = 0;
		frame.getContentPane().add(lblAcccion, gbc_lblAcccion);
		
		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		comboBox.addItem("Alta");
		comboBox.addItem("Baja");
		comboBox.addItem("Modificacion");
		
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 1;
		frame.getContentPane().add(lblUsuario, gbc_lblUsuario);
		
		user = new JTextField();
		GridBagConstraints gbc_user = new GridBagConstraints();
		gbc_user.insets = new Insets(0, 0, 5, 0);
		gbc_user.fill = GridBagConstraints.BOTH;
		gbc_user.gridx = 1;
		gbc_user.gridy = 1;
		frame.getContentPane().add(user, gbc_user);
		user.setColumns(10);
		
		JLabel lblContra = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_lblContra = new GridBagConstraints();
		gbc_lblContra.fill = GridBagConstraints.VERTICAL;
		gbc_lblContra.insets = new Insets(0, 0, 5, 5);
		gbc_lblContra.anchor = GridBagConstraints.EAST;
		gbc_lblContra.gridx = 0;
		gbc_lblContra.gridy = 2;
		frame.getContentPane().add(lblContra, gbc_lblContra);
		
		pass = new JTextField();
		GridBagConstraints gbc_pass = new GridBagConstraints();
		gbc_pass.insets = new Insets(0, 0, 5, 0);
		gbc_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass.gridx = 1;
		gbc_pass.gridy = 2;
		frame.getContentPane().add(pass, gbc_pass);
		pass.setColumns(10);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String us = user.getText().toLowerCase();
				switch ((String)comboBox.getSelectedItem()) {
				case "Alta":
					new Datos().crearUsuario(us, pass.getText());
					break;
				case "Baja":
					new Datos().borrarUsuario(us);
					break;
				case "Modificacion":
					new Datos().modificarUsuario(us, pass.getText());
					break;
				default:
					break;
				}
			}
		});
		GridBagConstraints gbc_ok = new GridBagConstraints();
		gbc_ok.gridwidth = 2;
		gbc_ok.gridx = 0;
		gbc_ok.gridy = 3;
		frame.getContentPane().add(ok, gbc_ok);
	}

}
