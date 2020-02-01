package relationship.dao; 




import relationship.persistence.PublicPersistence;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import relationship.singleton.SingletonDAO;


public class UserDAOImpl implements  UserDAO
{

	private static final Logger logger = Logger.getLogger(PublicDAOImpl.class);
	
	public boolean checkUserLogin(String username, String password)
	{
		logger.setLevel(Level.ERROR);
		
		boolean login = false;
		
		Session session = SingletonDAO.openSession();
		try
		{
			Query query = session.createQuery("select user.username , user.password  from UserPersistence as user where user.username='"+username+"' and user.password='"+password+"'  ");
			List list = query.list();
			if(list.contains(null))
			{			
				login = false;
			}
			else
			{
				Iterator itr = list.iterator();
				while(itr.hasNext())
				{
					Object[] objArray = (Object[])itr.next();
					
					String databaseUsername = (String)objArray[0];
					String databasePassword = (String)objArray[1];
					
					if( (databaseUsername.equals(username)) && (databasePassword.equals(databasePassword)) ){
						login = true;
					}
					else{
						login = false;
					}
				}	
			}	
		}
		catch(Exception e)
		{
			logger.error(" Error Occured " , e.fillInStackTrace());
		}
		finally
		{
			if(session!=null)
			{
				SingletonDAO.closeSession(session);				
			}	
		}
		return login;	
	}
}
