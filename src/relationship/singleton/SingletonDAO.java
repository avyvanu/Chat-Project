package relationship.singleton;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


public class SingletonDAO {

	private static SessionFactory sessionFactory;

    static 
    {
        Configuration cfg = new Configuration();
        sessionFactory = cfg.configure().buildSessionFactory();         
    }
    
    public static SessionFactory getSessionFactory()
    {	
       return sessionFactory;
    }

    public static Session openSession()
    {
    	return sessionFactory.openSession();
    }

    public static void closeSession(Session session)
    {
        if ( session != null ) 
        {
            session.close();
        }
    }
    public static void closeSessionFactory()
    {
    	if ( sessionFactory != null )
            sessionFactory.close();
        	sessionFactory = null;       
    } 
}
