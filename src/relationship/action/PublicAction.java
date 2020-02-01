package relationship.action;


import org.apache.struts.actions.DispatchAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import relationship.dao.DAOFactory;
import relationship.dao.PublicDAO;
import relationship.persistence.PublicPersistence;
import relationship.forms.PublicForm;
import relationship.socket.UdpClient;
import relationship.dao.ChatDAO;
import java.util.ArrayList;
import relationship.dao.DAOFactory;
import relationship.dao.ChatDAO;
import relationship.persistence.ChatMessagePersistence;
import relationship.persistence.ChatMessagePersistence;


public class PublicAction  extends DispatchAction 
{

	public ActionForward sendPublicRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "success";
		
		PublicDAO publicDAO = new DAOFactory().getPublicDAO();
		
		PublicForm publicForm  = (PublicForm)form;
		
		String publicName = publicForm.getCustomerName();
		String publicEmail = publicForm.getCustomerEmail();
		String publicPhoneNo = publicForm.getCustomerPhoneNo();
		String clientMessage = publicForm.getMessage();
		
		java.util.Date date = new java.util.Date();
		java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
		java.sql.Time  timeOut = new java.sql.Time(new java.util.Date().getTime());
		String status = "connected";
		
		PublicPersistence publicPersistence = new PublicPersistence(0,publicName,publicEmail,publicPhoneNo,clientMessage,date,timeIn,timeOut,status); 
		int public_Id = publicDAO.savePublicUserInformation(publicPersistence);
		
		if(public_Id == 0){
			target = "failure";
		}
		else{
			HttpSession session=request.getSession();
			session.setAttribute("public_Id",public_Id);
			session.setAttribute("publicName",publicName);			
			
			ChatDAO chatDAO = new DAOFactory().getChatDAO();			
			ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,publicName,toDate,timeIn,public_Id);			
			chatDAO.saveChatMessage(chatMessage);
		}
		return mapping.findForward(target);
	}
	
	public ActionForward publicLogoutRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "logout";		
		
		HttpSession session=request.getSession();
		int public_Id=(Integer)session.getAttribute("public_Id");
		if(public_Id != 0){
			
			session.invalidate();		
			PublicDAO publicDAO = new DAOFactory().getPublicDAO();
			int updatedStatus = publicDAO.updatePublicUserStatus(public_Id);
			
			
			String publicName = publicDAO.getPublicNameByPublicId(public_Id);
			String clientMessage = publicName+" is Logedout";
			java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
			ChatDAO chatDAO = new DAOFactory().getChatDAO();			
			ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,publicName,toDate,timeIn,public_Id);			
			chatDAO.saveChatMessage(chatMessage);			
		}
		return mapping.findForward(target);
	}
	
	
	public ActionForward publicChat(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "chat";		
		
		HttpSession session=request.getSession();
		int public_Id=(Integer)session.getAttribute("public_Id");
		
		if(public_Id != 0){
			
			String clientMessage = request.getParameter("message");			
			PublicDAO publicDAO = new DAOFactory().getPublicDAO();
			String publicName = publicDAO.getPublicNameByPublicId(public_Id);
			
			
			java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
			java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
			ChatDAO chatDAO = new DAOFactory().getChatDAO();			
			ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,publicName,toDate,timeIn,public_Id);			
			chatDAO.saveChatMessage(chatMessage);	
			
			
			/*UdpClient udpClient = new UdpClient();
			String output = udpClient.udpClientCall(message,publicName,public_Id);
			output = null;*/			
		}		
		return mapping.findForward(target);
	}
	
	
	public ActionForward userChat(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "userChat";
		
		HttpSession session=request.getSession();		
		String username=(String)session.getAttribute("username");		
		if(username != null){
			
			PublicForm publicForm  = (PublicForm)form;			
			int public_Id = publicForm.getPublic_oid();			
			String clientMessage = publicForm.getChatMessage();
			
			if(public_Id != 0){
				
				java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
				java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
				ChatDAO chatDAO = new DAOFactory().getChatDAO();			
				ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,username,toDate,timeIn,public_Id);			
				chatDAO.saveChatMessage(chatMessage);	
				
				
				/*UdpClient udpClient = new UdpClient();
				String output = udpClient.udpClientCall(chatMessage,username,public_Id);
				output = null;*/				
			}			
		}		
		return mapping.findForward(target);
	}
	
	
	public ActionForward userChatLogout(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "userChatLogout";		
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");		
		if(username != null){
			
			PublicForm publicForm  = (PublicForm)form;			
			int public_Id = publicForm.getPublic_oid();		
			
			String clientMessage = username+" is Logedout";
			
			java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
			ChatDAO chatDAO = new DAOFactory().getChatDAO();			
			ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,username,toDate,timeIn,public_Id);			
			chatDAO.saveChatMessage(chatMessage);
		}		
		return mapping.findForward(target);
	}
	
	
	
}
