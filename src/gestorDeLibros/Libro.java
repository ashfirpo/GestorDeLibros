package gestorDeLibros;

import java.util.Comparator;

public class Libro implements Comparator<Libro> {
	
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int fechaPublicacion;
	
	public Libro()
	{}
	
	public void setISBN(String isbn)
	{
		this.isbn=isbn;
	}
	
	public void setTitulo(String titulo)
	{
		this.titulo=titulo;
	}
	
	public void setAutor(String autor)
	{
		this.autor = autor;
	}
	
	public void setEditorial(String editorial)
	{
		this.editorial = editorial;
	}
	
	public void setEdicion(int edicion)
	{
		this.edicion = edicion;
	}
	
	public void setAnno_de_publicacion(int fecha)
	{
		this.fechaPublicacion = fecha;
	}

	@Override
	public int compare(Libro o1, Libro o2) {
		return o1.isbn.compareTo(o2.isbn);
	}
	
	@Override
	public String toString()
	{
		return this.isbn + "\t" + this.titulo + "\t" + this.autor + "\t" + this.editorial + "\t" +
				this.edicion + "\t" + this.fechaPublicacion;
	}
	

}
