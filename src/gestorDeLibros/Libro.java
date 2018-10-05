package gestorDeLibros;

import java.util.ArrayList;
import java.util.Comparator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import baseDeDatos.Datos;

public class Libro extends Datos implements Comparator<Libro> {
	
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int fechaPublicacion;
	
	public Libro()
	{}
	
	public Libro(String isbn, String autor, String titulo, String editorial, int edicion, int fechaPublicacion)
	{
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.edicion = edicion;
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public String getISBN()
	{
		return this.isbn;
	}
	
	public String getTitulo()
	{
		return this.titulo;
	}
	
	public String getAutor()
	{
		return this.autor;
	}
	
	public String getEditorial()
	{
		return this.editorial;
	}
	
	public int getEdicion()
	{
		return this.edicion;
	}
	
	public int getFechaPublicacion()
	{
		return this.fechaPublicacion;
	}
	
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
	
	public void setFechaPublicacion(int fecha)
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
		return this.titulo + " de " + this.autor + " (" + this.fechaPublicacion + ")";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Libro> cargarLibros() {
		
		try
		{
			@SuppressWarnings("deprecation")
			Criteria cb = session.createCriteria(Libro.class).addOrder(Order.asc("autor")).addOrder(Order.asc("titulo"));
			if (!cb.list().isEmpty())
				return (ArrayList<Libro>) cb.list();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		return new ArrayList<Libro>();
	}
	
	
	public String agregarLibro(Libro libro) {
		
		Transaction tx = session.getTransaction();
		if(tx==null || !tx.isActive())
			tx=session.beginTransaction();

		try
		{
			session.save(libro);
			tx.commit();
			tx = null;
			return "Se creó el libro correctamente.";
		}
		catch (HibernateException e)
		{
			if (tx != null)
			{
				tx.rollback();
				tx = null;
			}
			return "Ya existe un libro con el mismo ISBN.";
		}
	}
	

	public boolean borrarLibro(Libro libro) {

		Transaction tx = session.getTransaction();
		if(tx==null || !tx.isActive())
			tx=session.beginTransaction();
		
		try
		{
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Libro.class).add(Restrictions.and(Restrictions.eq("ISBN", libro.getISBN())));

			Libro lib= (Libro) crit.uniqueResult();
			session.delete(lib);
			tx.commit();
			tx = null;
			return true;
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String modificarLibro(Libro libro) {
		
		Transaction tx = session.getTransaction();
		if(tx==null || !tx.isActive())
			tx=session.beginTransaction();
		
		try 
		{
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Libro.class)
					.add(Restrictions.and(Restrictions.eq("ISBN", libro.getISBN())));

			Libro lib= (Libro) crit.uniqueResult();
			
			lib = (Libro) crit.uniqueResult();
			lib.setAutor(libro.getAutor());
			lib.setEdicion(libro.getEdicion());
			lib.setEditorial(libro.getEditorial());
			lib.setFechaPublicacion(libro.getFechaPublicacion());
			lib.setTitulo(libro.getTitulo());
			session.update(lib);
			tx.commit();
			tx = null;
			return "Se modificó el libro correctamente.";
		}
		catch (HibernateException e) 
		{
			if(tx != null)
			{
				tx.rollback();
				tx=null;
			}
			return e.getMessage();	
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Libro> buscarFiltro(String filtro, String valor) {
		
		if(filtro.equals("Título"))
			filtro = "titulo";
		else if(filtro.equals("Autor"))
			filtro = filtro.toLowerCase();
		
		try
		{
			Criteria crit = session.createCriteria(Libro.class);
			if(filtro.equals("ISBN"))
				crit.add(Restrictions.and(Restrictions.eq(filtro, valor))).addOrder(Order.asc("autor")).addOrder(Order.asc("titulo"));
			else
				crit.add(Restrictions.and(Restrictions.ilike(filtro, valor, MatchMode.ANYWHERE))).addOrder(Order.asc("autor")).addOrder(Order.asc("titulo"));

			if (crit != null && crit.list() != null && !crit.list().isEmpty())
				return (ArrayList<Libro>)crit.list();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

}
