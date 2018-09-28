package interfaz;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gestorDeLibros.Libro;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.SystemColor;

public class ABMLibro extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtISBN;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtEditorial;
	private JTextField txtEdicion;
	private JTextField txtFecha;
	private ABMLibro ventana;
	private Font fuente = new Font("Tahoma", Font.PLAIN, 11);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ABMLibro frame = new ABMLibro();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public ABMLibro()
	{}
	public ABMLibro(Libro libro) {
		
		setBackground(SystemColor.window);
		setMinimumSize(new Dimension(650, 200));
		setPreferredSize(new Dimension(650, 200));
		setResizable(false);
		ventana = this;
		this.setLocationRelativeTo(null);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setFont(fuente);
		contentPane.setBackground(SystemColor.window);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 85, 103, 0, 0};
		gbl_contentPane.rowHeights = new int[]{36, 0, 0, 0, 0, 0, 32, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		lblIsbn.setFont(fuente);
		contentPane.add(lblIsbn, gbc_lblIsbn);
		
		txtISBN = new JTextField();
		GridBagConstraints gbc_txtISBN = new GridBagConstraints();
		gbc_txtISBN.anchor = GridBagConstraints.SOUTH;
		gbc_txtISBN.gridwidth = 4;
		gbc_txtISBN.insets = new Insets(0, 0, 5, 0);
		gbc_txtISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtISBN.gridx = 1;
		gbc_txtISBN.gridy = 0;
		txtISBN.setFont(fuente);
		txtISBN.setMinimumSize(new Dimension(8, txtISBN.getSize().width));
		txtISBN.setToolTipText("Campo obligatorio");
		if(libro!=null)
		{
			txtISBN.setEnabled(false);
			txtISBN.setToolTipText("Vas a tener que borrar este libro para reutilizar este ISBN.");
		}
		contentPane.add(txtISBN, gbc_txtISBN);
		txtISBN.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 1;
		lblTtulo.setFont(fuente);
		contentPane.add(lblTtulo, gbc_lblTtulo);
		
		txtTitulo = new JTextField();
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.gridwidth = 4;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		txtTitulo.setFont(fuente);
		txtTitulo.setToolTipText("Campo obligatorio");
		txtTitulo.setMinimumSize(new Dimension(8, txtTitulo.getSize().width));
		contentPane.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.anchor = GridBagConstraints.EAST;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 2;
		lblAutor.setFont(fuente);
		contentPane.add(lblAutor, gbc_lblAutor);
		
		txtAutor = new JTextField();
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.gridwidth = 4;
		gbc_txtAutor.insets = new Insets(0, 0, 5, 0);
		gbc_txtAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAutor.gridx = 1;
		gbc_txtAutor.gridy = 2;
		txtAutor.setFont(fuente);
		txtAutor.setToolTipText("Campo obligatorio");
		txtAutor.setMinimumSize(new Dimension(8, txtAutor.getSize().width));
		contentPane.add(txtAutor, gbc_txtAutor);
		txtAutor.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial:");
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.anchor = GridBagConstraints.EAST;
		gbc_lblEditorial.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditorial.gridx = 0;
		gbc_lblEditorial.gridy = 3;
		lblEditorial.setFont(fuente);
		contentPane.add(lblEditorial, gbc_lblEditorial);
		
		txtEditorial = new JTextField();
		GridBagConstraints gbc_txtEditorial = new GridBagConstraints();
		gbc_txtEditorial.gridwidth = 4;
		gbc_txtEditorial.insets = new Insets(0, 0, 5, 0);
		gbc_txtEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditorial.gridx = 1;
		gbc_txtEditorial.gridy = 3;
		txtEditorial.setFont(fuente);
		txtEditorial.setMinimumSize(new Dimension(8, txtEditorial.getSize().width));
		contentPane.add(txtEditorial, gbc_txtEditorial);
		txtEditorial.setColumns(10);
		
		JLabel lblEdicin = new JLabel("Edici\u00F3n:");
		GridBagConstraints gbc_lblEdicin = new GridBagConstraints();
		gbc_lblEdicin.anchor = GridBagConstraints.EAST;
		gbc_lblEdicin.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicin.gridx = 0;
		gbc_lblEdicin.gridy = 4;
		lblEdicin.setFont(fuente);
		contentPane.add(lblEdicin, gbc_lblEdicin);
		
		txtEdicion = new JTextField();
		GridBagConstraints gbc_txtEdicion = new GridBagConstraints();
		gbc_txtEdicion.gridwidth = 2;
		gbc_txtEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_txtEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEdicion.gridx = 1;
		gbc_txtEdicion.gridy = 4;
		txtEdicion.setFont(fuente);
		txtEdicion.setToolTipText("En formato de num�rico. Por ejemplo: 5");
		txtEdicion.setMinimumSize(new Dimension(8, txtEdicion.getSize().width));
		contentPane.add(txtEdicion, gbc_txtEdicion);
		txtEdicion.setColumns(10);
		
		JLabel lblAoDePublicacin = new JLabel("A\u00F1o de publicaci\u00F3n:");
		GridBagConstraints gbc_lblAoDePublicacin = new GridBagConstraints();
		gbc_lblAoDePublicacin.anchor = GridBagConstraints.EAST;
		gbc_lblAoDePublicacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblAoDePublicacin.gridx = 3;
		gbc_lblAoDePublicacin.gridy = 4;
		lblAoDePublicacin.setFont(fuente);
		contentPane.add(lblAoDePublicacin, gbc_lblAoDePublicacin);
		
		txtFecha = new JTextField();
		GridBagConstraints gbc_txtFecha = new GridBagConstraints();
		gbc_txtFecha.insets = new Insets(0, 0, 5, 0);
		gbc_txtFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFecha.gridx = 4;
		gbc_txtFecha.gridy = 4;
		txtFecha.setFont(fuente);
		txtFecha.setToolTipText("En formato de num�rico. Por ejemplo: 2012");
		txtFecha.setMinimumSize(new Dimension(8, txtFecha.getSize().width));
		contentPane.add(txtFecha, gbc_txtFecha);
		txtFecha.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(libro==null)
					guardarLibro(true);
				else
					guardarLibro(false);
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridwidth = 2;
		gbc_btnGuardar.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 6;
		btnGuardar.setFont(fuente);
		contentPane.add(btnGuardar, gbc_btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 6;
		btnCancelar.setFont(fuente);
		contentPane.add(btnCancelar, gbc_btnCancelar);
		
		if(libro==null)
			setTitle("Nuevo Libro");
		else
		{
			setTitle("Modificando: " + libro.toString());
			setearDatos(libro);
		}
	}
	
	public void setearDatos(Libro l)
	{
		txtISBN.setText(l.getISBN());
		txtTitulo.setText(l.getTitulo());
		txtAutor.setText(l.getAutor());
		txtEditorial.setText(l.getEditorial());
		txtEdicion.setText(String.valueOf(l.getEdicion()));
		txtFecha.setText(String.valueOf(l.getFechaPublicacion()));
	}
	
	@SuppressWarnings("static-access")
	public void guardarLibro(boolean esNuevo)
	{
		JOptionPane mensaje = new JOptionPane();
		JLabel lbl = new JLabel();
		lbl.setFont(fuente);
		mensaje.setFont(fuente);
		
		try
		{
			Libro libro = new Libro(txtISBN.getText(), txtAutor.getText(), txtTitulo.getText(), txtEditorial.getText(), Integer.parseInt(txtEdicion.getText()),
				Integer.parseInt(txtFecha.getText()));
		
			if(esNuevo)
			{
				String res = libro.agregarLibro(libro);
				lbl.setText(res);
				if(res.contains("correctamente"))
				{
					mensaje.showMessageDialog(ventana, lbl, "Libro creado", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				else
					mensaje.showMessageDialog(ventana, lbl, "Error en la creaci�n del libro", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				String res = libro.modificarLibro(libro);
				lbl.setText(res);
				if(res.contains("correctamente"))
				{
					mensaje.showMessageDialog(ventana, lbl, "Libro modificado", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				else
					mensaje.showMessageDialog(ventana, lbl, "Error en la modificaci�n del libro", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{
			lbl.setText("Revisa que el valor de los campos sean correctos o no est�n vac�os.");
			mensaje.showMessageDialog(ventana, lbl, "Error en los campos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}