package baseDeDatos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

public class Datos {
	
	private static SessionFactory factory;
	protected static Session session;
	
	public Datos()
	{
		if(session == null)
		{
			Configuration conf = new Configuration();
			conf.configure("baseDeDatos/hibernate.cfg.xml");
			factory = conf.buildSessionFactory();
			session = factory.openSession();
		}
	}

}
