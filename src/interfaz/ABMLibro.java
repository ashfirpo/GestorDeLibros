package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gestorDeLibros.Libro;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JButton btnGuardar;
	private JLabel lblFormatoDeIsbn;
	private boolean isbn = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// user = args[0];
				try {
					ABMLibro frame = new ABMLibro(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); // Para que la ubicación de la ventana de incio esté centrada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ABMLibro(Libro libro) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Inicializa interfaz
		setBackground(SystemColor.window);
		setMinimumSize(new Dimension(650, 200));
		setPreferredSize(new Dimension(650, 200));
		setResizable(false);
		ventana = this;
		this.setLocationRelativeTo(null);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		ImageIcon img = new ImageIcon("./Libro.png");
		this.setIconImage(img.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 85, 103, 103, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 36, 0, 0, 0, 0, 0, 32, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblIsbn = new JLabel("ISBN:");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		contentPane.add(lblIsbn, gbc_lblIsbn);

		// Campo para ISBN
		txtISBN = new JTextField();
		txtISBN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// Cuando se termina de escribir, verifica el formato del ISBN
				verificarFormato();
			}
		});
		GridBagConstraints gbc_txtISBN = new GridBagConstraints();
		gbc_txtISBN.anchor = GridBagConstraints.SOUTH;
		gbc_txtISBN.gridwidth = 3;
		gbc_txtISBN.insets = new Insets(0, 0, 5, 5);
		gbc_txtISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtISBN.gridx = 1;
		gbc_txtISBN.gridy = 0;
		txtISBN.setMinimumSize(new Dimension(8, txtISBN.getSize().width));
		// Mensaje de ayuda al usuario para que sepa que es un campo obligatorio
		txtISBN.setToolTipText("Campo obligatorio");
		if (libro != null) // Si se está modificando un libro, no le perimte modificar el ISBN, sugiere
							// borrar el libro y volver a crearlo
		{
			txtISBN.setEnabled(false);
			txtISBN.setToolTipText("Vas a tener que borrar este libro para reutilizar este ISBN.");
		}
		contentPane.add(txtISBN, gbc_txtISBN);
		txtISBN.setColumns(10);

		// Ayuda respecto al formato del ISBN
		lblFormatoDeIsbn = new JLabel("Formato de ISBN: xxx-xxx-xxxx-xx-x");
		GridBagConstraints gbc_lblFormatoDeIsbn = new GridBagConstraints();
		gbc_lblFormatoDeIsbn.anchor = GridBagConstraints.SOUTH;
		gbc_lblFormatoDeIsbn.gridwidth = 2;
		gbc_lblFormatoDeIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormatoDeIsbn.gridx = 4;
		gbc_lblFormatoDeIsbn.gridy = 0;
		contentPane.add(lblFormatoDeIsbn, gbc_lblFormatoDeIsbn);

		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 1;
		contentPane.add(lblTtulo, gbc_lblTtulo);

		// Campo de título
		txtTitulo = new JTextField();
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.gridwidth = 5;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 1;
		// Avisa al usuario que es un campo obligatorio
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
		contentPane.add(lblAutor, gbc_lblAutor);

		// Campo autor
		txtAutor = new JTextField();
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.gridwidth = 5;
		gbc_txtAutor.insets = new Insets(0, 0, 5, 0);
		gbc_txtAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAutor.gridx = 1;
		gbc_txtAutor.gridy = 2;
		// Avisa al usuario que es un campo obligatorio
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
		contentPane.add(lblEditorial, gbc_lblEditorial);

		// Campo editorial
		txtEditorial = new JTextField();
		GridBagConstraints gbc_txtEditorial = new GridBagConstraints();
		gbc_txtEditorial.gridwidth = 5;
		gbc_txtEditorial.insets = new Insets(0, 0, 5, 0);
		gbc_txtEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditorial.gridx = 1;
		gbc_txtEditorial.gridy = 3;
		txtEditorial.setMinimumSize(new Dimension(8, txtEditorial.getSize().width));
		contentPane.add(txtEditorial, gbc_txtEditorial);
		txtEditorial.setColumns(10);

		JLabel lblEdicin = new JLabel("Edici\u00F3n:");
		GridBagConstraints gbc_lblEdicin = new GridBagConstraints();
		gbc_lblEdicin.anchor = GridBagConstraints.EAST;
		gbc_lblEdicin.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicin.gridx = 0;
		gbc_lblEdicin.gridy = 4;
		contentPane.add(lblEdicin, gbc_lblEdicin);

		// Campo edición
		txtEdicion = new JTextField();
		GridBagConstraints gbc_txtEdicion = new GridBagConstraints();
		gbc_txtEdicion.gridwidth = 2;
		gbc_txtEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_txtEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEdicion.gridx = 1;
		gbc_txtEdicion.gridy = 4;
		txtEdicion.setToolTipText("En formato de numérico. Por ejemplo: 5");
		txtEdicion.setMinimumSize(new Dimension(8, txtEdicion.getSize().width));
		contentPane.add(txtEdicion, gbc_txtEdicion);
		txtEdicion.setColumns(10);

		JLabel lblAoDePublicacin = new JLabel("A\u00F1o de publicaci\u00F3n:");
		GridBagConstraints gbc_lblAoDePublicacin = new GridBagConstraints();
		gbc_lblAoDePublicacin.gridwidth = 2;
		gbc_lblAoDePublicacin.anchor = GridBagConstraints.EAST;
		gbc_lblAoDePublicacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblAoDePublicacin.gridx = 3;
		gbc_lblAoDePublicacin.gridy = 4;
		contentPane.add(lblAoDePublicacin, gbc_lblAoDePublicacin);

		// Campo año de publicación
		txtFecha = new JTextField();
		GridBagConstraints gbc_txtFecha = new GridBagConstraints();
		gbc_txtFecha.insets = new Insets(0, 0, 5, 0);
		gbc_txtFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFecha.gridx = 5;
		gbc_txtFecha.gridy = 4;
		// Le avisa al usuario que es en formato numérico
		txtFecha.setToolTipText("En formato de numérico. Por ejemplo: 2012");
		txtFecha.setMinimumSize(new Dimension(8, txtFecha.getSize().width));
		contentPane.add(txtFecha, gbc_txtFecha);
		txtFecha.setColumns(10);

		// Botón Guardar
		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					if (libro == null)
						guardarLibro(true); // Si el libro es nuevo
					else
						guardarLibro(false); // Si se está editando un libro existente
				}
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (libro == null)
					guardarLibro(true); // Si el libro es nuevo
				else
					guardarLibro(false); // Si se está editando un libro existente
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridwidth = 3;
		gbc_btnGuardar.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 6;
		contentPane.add(btnGuardar, gbc_btnGuardar);

		// Botón cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.SOUTH;
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 6;
		contentPane.add(btnCancelar, gbc_btnCancelar);

		if (libro == null) // Si el libro es nuevo
			setTitle("Nuevo Libro");
		else // Si se está editando un libro existente
		{
			setTitle("Modificando: " + libro.toString());
			setearDatos(libro);
		}
	}

	public void setearDatos(Libro l) {
		// Carga todos los campos con los datos del libro a modificar
		txtISBN.setText(l.getISBN());
		txtTitulo.setText(l.getTitulo());
		txtAutor.setText(l.getAutor());
		txtEditorial.setText(l.getEditorial());
		txtEdicion.setText(String.valueOf(l.getEdicion()));
		txtFecha.setText(String.valueOf(l.getFechaPublicacion()));
	}

	public void verificarFormato() {
		txtISBN.setToolTipText(null);
		txtISBN.setBackground(new Color(248, 248, 255));
		isbn = true;

		String isbntex = txtISBN.getText();
		Matcher m = Pattern.compile(
				"(?:(?=^[\\d-]{17}$)97[89]-\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d)|(?:(?=^[\\d-]{13}$)\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d)")
				.matcher(isbntex);
		isbntex = isbntex.replace("-", "");
		if (!m.find()) {
			// No respeta el formato del ISBN
			txtISBN.setToolTipText("El ISBN es incorrecto");
			txtISBN.setBackground(new Color(255, 102, 102));
			isbn = false;
		}

		int i = 1;
		int sum = 0;
		if (isbntex.length() == 10) {
			for (char c : isbntex.toCharArray()) {
				int num = Integer.parseInt(c + "");
				sum += num * i++;
			}
			sum %= 11;
		} else {
			for (char c : isbntex.toCharArray()) {
				int num = Integer.parseInt(c + "");
				sum += num * i;
				i = (i + 2) % 4;
			}
			sum %= 10;
		}
		if (sum != 0) {
			// No respeta el formato del ISBN
			txtISBN.setToolTipText("El ISBN es incorrecto");
			txtISBN.setBackground(new Color(255, 102, 102));
			isbn = false;
		}
	}

	@SuppressWarnings("static-access")
	public void guardarLibro(boolean esNuevo) {
		// Inicializa el cuadro de díalogo
		JOptionPane mensaje = new JOptionPane();
		JLabel lbl = new JLabel();

		try {
			if (isbn == true) {
				// Toma los datos del libro
				Libro libro = new Libro(txtISBN.getText(), txtAutor.getText(), txtTitulo.getText(),
						txtEditorial.getText(), Integer.parseInt(txtEdicion.getText()),
						Integer.parseInt(txtFecha.getText()));

				if (esNuevo) // Si es un libro nuevo, crea un registro nuevo
				{
					String res = libro.agregarLibro(libro);
					lbl.setText(res);
					if (res.contains("correctamente")) // Muestra los mensajes correspondientes
					{
						// Se creó el libro correctamente
						mensaje.showMessageDialog(ventana, lbl, "Libro creado", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					} else // No se pudo crear el libro
						mensaje.showMessageDialog(ventana, lbl, "Error en la creación del libro",
								JOptionPane.ERROR_MESSAGE);
				} else // Si es una modificación, lo actualiza
				{
					String res = libro.modificarLibro(libro);
					lbl.setText(res);
					if (res.contains("correctamente"))// Muestra los mensajes correspondientes
					{
						// Se modificó el libro correctamente
						mensaje.showMessageDialog(ventana, lbl, "Libro modificado", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					} else // No se pudo modificar el libro
						mensaje.showMessageDialog(ventana, lbl, "Error en la modificación del libro",
								JOptionPane.ERROR_MESSAGE);
				}
			} else // El campo de ISBN no tiene el formato correcto, y lo informa
			{
				lbl.setText("Revisa que el valor del ISBN sea correcto.");
				mensaje.showMessageDialog(ventana, lbl, "Error en los campos", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) // Alguno de los campos no es correcto o están vacíos
		{
			lbl.setText("Revisa que el valor de los campos sean correctos o no estén vacíos.");
			mensaje.showMessageDialog(ventana, lbl, "Error en los campos", JOptionPane.ERROR_MESSAGE);
		}
	}

}
