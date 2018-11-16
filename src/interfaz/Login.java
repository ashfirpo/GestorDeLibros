package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import baseDeDatos.Datos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frmIniciarSesin;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	JOptionPane mensaje;
	JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				new Datos();
			}
		}.start();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Para que tome la apariencia del sistema operativo
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmIniciarSesin.setVisible(true);
					window.frmIniciarSesin.setLocationRelativeTo(null); //Para que la ubicación de la ventana esté centrada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Seteamos las propiedades del frame principal de Login
		frmIniciarSesin = new JFrame();
		ImageIcon img = new ImageIcon("./Login.png");
		frmIniciarSesin.setIconImage(img.getImage());
		frmIniciarSesin.setBackground(new Color(248, 248, 255));
		frmIniciarSesin.setType(Type.POPUP);
		frmIniciarSesin.setResizable(false);
		frmIniciarSesin.setTitle("Gestor de Libros");
		frmIniciarSesin.setBounds(100, 100, 284, 157);
		frmIniciarSesin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Cuadro de diálogo para mensajes de error
		mensaje = new JOptionPane();
		lblMensaje = new JLabel();
		
		//Panel contenedor
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(160, 160, 160)), "Iniciar sesi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		frmIniciarSesin.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{69, 23, 36, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{34, 36, 32, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		//Propiedades de la etiqueta "Usuario"
		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 0;
		panel.add(lblUsuario, gbc_lblUsuario);
		
		//Propiedades del campo de texto para el nombre de usuario
		txtUsuario = new JTextField();
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.anchor = GridBagConstraints.SOUTH;
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridwidth = 5;
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsuario.gridx = 1;
		gbc_txtUsuario.gridy = 0;
		panel.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		//Propiedades de la etiqueta "Contraseña"
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 1;
		panel.add(lblContrasea, gbc_lblContrasea);
		
		//Propiedades del campo de texto para la contraseña
		txtPass = new JPasswordField();
		//Agregamos un evento al campo de contraseña para que cuando se aprete Enter intente iniciar sesión
		txtPass.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER)
				{
					login();
				}
			}
		});
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.anchor = GridBagConstraints.SOUTH;
		gbc_txtPass.gridwidth = 5;
		gbc_txtPass.insets = new Insets(0, 0, 5, 0);
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.gridx = 1;
		gbc_txtPass.gridy = 1;
		panel.add(txtPass, gbc_txtPass);
		
		JButton btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
		//Agregamos un evento en el botón de iniciar sesión para que cuando se haga click intente iniciar sesión
		btnIniciarSesin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				login();
			}});

		GridBagConstraints gbc_btnIniciarSesin = new GridBagConstraints();
		gbc_btnIniciarSesin.gridwidth = 3;
		gbc_btnIniciarSesin.anchor = GridBagConstraints.SOUTH;
		gbc_btnIniciarSesin.insets = new Insets(0, 0, 0, 5);
		gbc_btnIniciarSesin.gridx = 1;
		gbc_btnIniciarSesin.gridy = 2;
		panel.add(btnIniciarSesin, gbc_btnIniciarSesin);
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	private void login()
	{
		if(!new Datos().comprobarUser(txtUsuario.getText())) //Si el nombre de usuario no existe en la base de datos, lo informa
		{
			lblMensaje.setText("El usuario no existe. Intenta de nuevo.");
			mensaje.showMessageDialog(frmIniciarSesin, lblMensaje, "Error", JOptionPane.ERROR_MESSAGE);
			txtPass.setText("");
		}
		else 
		{
			if (new Datos().traerDatos(txtUsuario.getText(), txtPass.getText().toString())) //Si el usuario existe, verifica la contraseña
			{
				//Si la contraseña es correcta, inicia sesión
				Inicio.main(null);
				frmIniciarSesin.hide();
			}
			else 
			{
				//Si la contraseña no es correcta, lo informa
				lblMensaje.setText("Contraseña incorrecta.");
				mensaje.showMessageDialog(frmIniciarSesin, lblMensaje, "Error", JOptionPane.ERROR_MESSAGE);
				txtPass.setText("");
			}
		}
	}
}
