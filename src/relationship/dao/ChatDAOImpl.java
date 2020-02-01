package relationship.dao;


import relationship.persistence.ChatMessagePersistence;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import relationship.singleton.SingletonDAO;
import relationship.forms.PublicForm;

public class ChatDAOImpl implements  ChatDAO
{
	private static final Logger logger = Logger.getLogger(ChatDAOImpl.class);
	
	public int saveChatMessage(ChatMessagePersistence chatPersistence){
		logger.setLevel(Level.ERROR);
		
		int public_Id = 0;
		Session session = SingletonDAO.openSession();		
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();			
			public_Id = (Integer)session.save(chatPersistence);
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
	
	
	public ArrayList getChatMessageListByPublicId(int publicId)
	{
		logger.setLevel(Level.ALL);
		ArrayList chatMessageList = new ArrayList();
		Session session=SingletonDAO.openSession();
		try
		{
			String query_execute="select u.chatMessage from ChatMessagePersistence as u where u.publicId='"+publicId+"' ";
			Query query=session.createQuery(query_execute);
			List list=query.list();
			if(list.contains(null))
			{				
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					String chatMessage = (String)itr.next();
					
					PublicForm form = new PublicForm();
					
					form.setChatMessage(chatMessage);
					
					chatMessageList.add(form);
				}
			}	
		}		
		catch(Exception e)
		{
			logger.error(" Error Occured ", e.fillInStackTrace());
		}
		finally
		{
			if(session!=null)
			{
				SingletonDAO.closeSession(session);	
			}
		}
		return chatMessageList;
	}
	
	
}
