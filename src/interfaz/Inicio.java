package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import baseDeDatos.Datos;
import gestorDeLibros.Libro;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Inicio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JButton btnNewButton;
	private JButton btnModificar;
	private JButton btnBorrarLibro;
	@SuppressWarnings("unused")
	private Datos db = new Datos();
	private Inicio ventana;
	String[] columns = new String[]{"ISBN", "Autor", "T�tulo", "Editorial", "Edici�n", "A�o de publicaci�n"};
	String[][] data = new String[][]{};
	private JButton btnActualizarTabla;
	JOptionPane mensaje;
	JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); //Para que la ubicaci�n de la ventana de incio est� centrada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Inicio() {
		setMinimumSize(new Dimension(644, 450));
		setBackground(new Color(255, 255, 204));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Cuadro de di�logo para mensajes de error
		mensaje = new JOptionPane();
		lblMensaje = new JLabel();
		
		//Ventana actual
		ventana = this;
		this.setLocationRelativeTo(null);
		setTitle("Gestor de Libros | Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 450);
		ImageIcon img = new ImageIcon("./Libro.png");
		this.setIconImage(img.getImage());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{68, 114, 344, 0, 0};
		gbl_contentPane.rowHeights = new int[]{78, 60, 39, 41, 37, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		GridBagConstraints gbc_lblBuscarPor = new GridBagConstraints();
		gbc_lblBuscarPor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBuscarPor.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarPor.gridx = 0;
		gbc_lblBuscarPor.gridy = 0;
		contentPane.add(lblBuscarPor, gbc_lblBuscarPor);
		
		//Selector de filtros para buscar
		comboBox = new JComboBox();
		comboBox.setToolTipText("ISBN: coincidencia exacta");
		comboBox.setBackground(SystemColor.window);
		comboBox.setOpaque(false);
		comboBox.setMaximumRowCount(3);
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlShadow));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "Autor", "T\u00EDtulo"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);
		
		//Campo para buscar el filtro
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER)
				{
					buscar();
				}
			}
		});
		txtBuscar.setMinimumSize(new Dimension(8, 20));
		txtBuscar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlShadow));
		txtBuscar.setPreferredSize(new Dimension(8, 20));
		GridBagConstraints gbc_txtBuscar = new GridBagConstraints();
		gbc_txtBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_txtBuscar.gridx = 2;
		gbc_txtBuscar.gridy = 0;
		contentPane.add(txtBuscar, gbc_txtBuscar);
		txtBuscar.setColumns(10);
		
		//Bot�n de busscar
		btnBuscar = new JButton("Buscar");
		//Le agregamos un evento para que busque cuando se le hace click al bot�n
		btnBuscar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}
		});
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 0;
		contentPane.add(btnBuscar, gbc_btnBuscar);
	
		//Bot�n para limpiar los resultados de la grilla cuando se aplican filtros (muestra todos los libros)
		btnActualizarTabla = new JButton("Limpiar filtros");
		btnActualizarTabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarDatos(Libro.cargarLibros());
			}
		});
		GridBagConstraints gbc_btnActualizarTabla = new GridBagConstraints();
		gbc_btnActualizarTabla.anchor = GridBagConstraints.NORTH;
		gbc_btnActualizarTabla.insets = new Insets(0, 0, 5, 0);
		gbc_btnActualizarTabla.gridx = 3;
		gbc_btnActualizarTabla.gridy = 1;
		contentPane.add(btnActualizarTabla, gbc_btnActualizarTabla);
		
		//Bot�n para agregar un libro nuevo
		btnNewButton = new JButton("Agregar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				ABMLibro abm = new ABMLibro(null); //Muestra la ventana del ABM de libros
				abm.show(true);
				abm.setLocationRelativeTo(null);
				abm.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {}
					
					@Override
					public void windowIconified(WindowEvent e) {}
					
					@Override
					public void windowDeiconified(WindowEvent e) {}
					
					@Override
					public void windowDeactivated(WindowEvent e) {}
					
					@Override
					public void windowClosing(WindowEvent e) {}
					
					@Override
					public void windowClosed(WindowEvent e) {
						cargarDatos(Libro.cargarLibros()); //Cuando se cierra el ABM se actualiza la grilla de libros
					}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			}
		});		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		//Bot�n para modificar libros
		btnModificar = new JButton("Modificar");
		//Para modificar un libro, hay que seleccionar uno de la grilla primero
		btnModificar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "static-access", "deprecation" })
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow(); //Tomamos el registro de la grilla para modificar
				if(index==-1)
				{
					//Si no hay ning�n libro seleccionado, se informa
					lblMensaje.setText("Selecciona un libro de la grilla para modificar.");
					mensaje.showMessageDialog(ventana, lblMensaje, "Libro no seleccionado", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					//Se toman los valores del libro seleccionado desde la grilla
					Libro libro = new Libro(table.getValueAt(index, 0).toString(), table.getValueAt(index, 1).toString(), table.getValueAt(index, 2).toString(),
									table.getValueAt(index, 3).toString(), Integer.parseInt(table.getValueAt(index, 4).toString()),
									Integer.parseInt(table.getValueAt(index,5).toString()));
					ABMLibro abm = new ABMLibro(libro); //Se abre el ABM para modificar el libro
					abm.show(true);
					abm.setLocationRelativeTo(null);
					abm.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {}
						
						@Override
						public void windowIconified(WindowEvent e) {}
						
						@Override
						public void windowDeiconified(WindowEvent e) {}
						
						@Override
						public void windowDeactivated(WindowEvent e) {}
						
						@Override
						public void windowClosing(WindowEvent e) {}
						
						@Override
						public void windowClosed(WindowEvent e) {
							cargarDatos(Libro.cargarLibros()); //Cuando se cierra el ABM, se actualiza la grilla de libros
						}
						
						@Override
						public void windowActivated(WindowEvent e) {}
					});
				}
			}
		});
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 5, 0);
		gbc_btnModificar.gridx = 3;
		gbc_btnModificar.gridy = 3;
		contentPane.add(btnModificar, gbc_btnModificar);
		
		//Bot�n para borrar libros
		btnBorrarLibro = new JButton("Borrar Libro");
		btnBorrarLibro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarLibro();
			}
		});
		GridBagConstraints gbc_btnBorrarLibro = new GridBagConstraints();
		gbc_btnBorrarLibro.anchor = GridBagConstraints.SOUTH;
		gbc_btnBorrarLibro.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarLibro.gridx = 3;
		gbc_btnBorrarLibro.gridy = 4;
		contentPane.add(btnBorrarLibro, gbc_btnBorrarLibro);
		
		//Carga la grilla con todos los libros existentes
		cargarTabla();
	}
	
	@SuppressWarnings("serial")
	public void cargarTabla()
	{
		//Grilla de libros
		table = new JTable(data, columns){
			public boolean isCellEditable(int row, int column){  
	        return false;  
	    }};
		table.getTableHeader().setBackground(SystemColor.window);
		table.getTableHeader().setFont(new Font(table.getFont().getFontName(), Font.BOLD, table.getFont().getSize()));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 5;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.gridwidth = 3;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		JScrollPane scrollPane = new JScrollPane(table);
		table.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_DELETE) //Si estando sobre un registro de la grilla, se presiona la tecla Suprimir, elimina el libro
					eliminarLibro();
			}
		});
		scrollPane.setBackground(SystemColor.control);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlShadow));
		contentPane.add(scrollPane, gbc_table);
		cargarDatos(Libro.cargarLibros()); //Carga la grilla de libros
	}
	
	@SuppressWarnings("static-access")
	public void eliminarLibro()
	{
		int index = table.getSelectedRow(); //Se toma el �ndice del registro a borrar seleccionado en la grilla
		if(index==-1) //Si no hay nada seleccionado, lo informa
		{
			lblMensaje.setText("Selecciona un libro de la grilla para borrar.");
			mensaje.showMessageDialog(ventana, lblMensaje, "Libro no seleccionado", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//Toma los datos del libro desde la grilla
			Libro libro = new Libro(table.getValueAt(index, 0).toString(), table.getValueAt(index, 1).toString(), table.getValueAt(index, 2).toString(),
							table.getValueAt(index, 3).toString(), Integer.parseInt(table.getValueAt(index, 4).toString()),
							Integer.parseInt(table.getValueAt(index,5).toString()));
			//Pide confirmaci�n del usuario
			lblMensaje.setText("Se va a eliminar: " + libro.toString() + " �Continuar?");
			if(mensaje.showConfirmDialog(ventana, lblMensaje, "Eliminar libro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				libro.borrarLibro(libro); //Se elimina el libro
			cargarDatos(Libro.cargarLibros()); //Se actualiza la grilla de libros
		}
	}
	
	@SuppressWarnings("static-access")
	public void buscar()
	{
		String filtro = comboBox.getSelectedItem().toString(); //Obtenemos el filtro aplicado desde el selector
		if(txtBuscar.getText().isEmpty()) //Si el campo de b�squeda est� vac�o, lo informa
		{
			lblMensaje.setText("El campo de b�squeda est� vac�o.");
			mensaje.showMessageDialog(ventana, lblMensaje, "", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			cargarDatos(Libro.buscarFiltro(filtro, txtBuscar.getText())); //Busca el libro con el filtro aplicado
			txtBuscar.setText(""); //Limpia el campo de b�squeda
		}
	}
	
	@SuppressWarnings("static-access")
	public void cargarDatos(ArrayList<Libro> source)
	{
		if(source != null)
		{
			int i=0;
			data = new String[source.size()][6];
			for(Libro l : source) //Llena la grilla de libros
			{
				String[] libro = new String[]{l.getISBN(), l.getAutor(), l.getTitulo(), l.getEditorial(), String.valueOf(l.getEdicion()),
									String.valueOf(l.getFechaPublicacion())}; 
				for(int j=0;j<6;j++)
					data[i][j]= libro[j];
				
				i++;
			}
			table.setModel(new DefaultTableModel(data, columns));
		}
		else //Si no hay registros de libros, lo informa
		{
			lblMensaje.setText("No se encontraron registros.");
			mensaje.showMessageDialog(ventana, lblMensaje, "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
