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


public class PublicDAOImpl implements PublicDAO 
{
	private static final Logger logger = Logger.getLogger(PublicDAOImpl.class);
	
	public int savePublicUserInformation(PublicPersistence publicPersistence)
	{
		logger.setLevel(Level.ERROR);
		int public_Id = 0;
		Session session = SingletonDAO.openSession();		
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();			
			public_Id = (Integer)session.save(publicPersistence);
			tx.commit();	
			SingletonDAO.closeSession(session);
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
		return public_Id;
	}
	
	
	public int updatePublicUserStatus(int publicId)
	{
		logger.setLevel(Level.ALL);	
		int updated = 0;
		
		Session session = SingletonDAO.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			String hql5 = "update PublicPersistence set status = :status  where public_oid = :public_oid";
			Query query5 = session.createQuery(hql5);
			query5.setString("status","close");			
			query5.setInteger("public_oid",publicId);
			
			updated = query5.executeUpdate();
			tx.commit();
			SingletonDAO.closeSession(session);	
		}
		catch(Exception e)
		{
			logger.error(" Error Occured ", e.fillInStackTrace());
			if(tx != null){tx.rollback();}
		}
		finally
		{
			if(session.isOpen())
			{
				SingletonDAO.closeSession(session);
			}
		}		
		return updated;
	}
	
	
	public String getPublicNameByPublicId(int publicId)
	{
		logger.setLevel(Level.ALL);	
		
		String publicName = "";
		
		Session session = SingletonDAO.openSession();
		try
		{
			String sql_query="select u.publicName  from PublicPersistence as u where u.public_oid='"+publicId+"' ";
			Query quer = session.createQuery(sql_query);	
			List list=quer.list();	
			if(list.contains(null))
			{				
			}
			else
			{
				Iterator itr=list.iterator();		
				while(itr.hasNext())
				{
					publicName = (String)itr.next();			
				}	
			}			 
		}
		catch(Exception e)
		{
			logger.error(" Error Occured ", e.fillInStackTrace());
		}
		finally
		{
			if(session != null)
			{
				SingletonDAO.closeSession(session);	
			}
		}
		return publicName;		
	}
	
	
	

}
