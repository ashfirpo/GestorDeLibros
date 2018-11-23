package baseDeDatos;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Restrictions;

import gestorDeLibros.Libro;

public class Datos {

	// Variables que se encargan de hacer la conexión con la base de datos
	private static SessionFactory factory;
	protected static Session session;

	// Constructor
	public Datos() {
		if (session == null) // Si todavía no se estableció la conexión, se establece
		{
			Configuration conf = new Configuration();
			conf.configure("baseDeDatos/hibernate.cfg.xml"); // Se indica el archivo de configuración
			factory = conf.buildSessionFactory(); // Se arman las sesiones
			session = factory.openSession();
		}
	}

	@SuppressWarnings("all")
	public boolean traerDatos(String user, String pass) {
		try {
			// Busca el nombre de usuario y la contraseña
			Criteria cb = session.createCriteria(Usuario.class)
					.add(Restrictions.and(Restrictions.eq("user", user.toLowerCase()), Restrictions.eq("pass", pass)));

			if (cb != null && cb.list() != null && !cb.list().isEmpty()) {
				return true; // Acepta el login
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean comprobarUser(String user) {
		try {
			// comprueba si existe el usuario
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class).add(Restrictions.eq("user", user.toLowerCase()));
			if (crit != null && crit.list() != null && !crit.list().isEmpty())
				return true; // Si existe, avisa
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean crearUsuario(String user, String pass) {
		Transaction tx = session.beginTransaction();
		try {
			Usuario res = new Usuario(user.toLowerCase(), pass);
			session.save(res);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public boolean borrarUsuario(String user) {

		Transaction tx = session.getTransaction(); // Verifica conexión con la base de datos
		if (tx == null || !tx.isActive())
			tx = session.beginTransaction();

		try {
			// Borra el libro y lo busca por ISBN
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class)
					.add(Restrictions.and(Restrictions.eq("user", user)));

			Usuario us = (Usuario) crit.uniqueResult(); // Toma al libro
			session.delete(us); // Y lo borra de la base de datos
			tx.commit();
			tx = null;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
public String modificarUsuario(String user, String pass) {
		
		Transaction tx = session.getTransaction(); //Verifica conexión con la base de datos
		if(tx==null || !tx.isActive())
			tx=session.beginTransaction();
		
		try 
		{
			//Busca al libro a modificar por ISBN
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class)
					.add(Restrictions.and(Restrictions.eq("user",user)));

			Usuario us= (Usuario) crit.uniqueResult();
			
			us = (Usuario) crit.uniqueResult(); //Toma al libro
			//Actualiza los datos
			us.setPass(pass);
			session.update(us); //Actualiza al registro en la base de datos
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

}
