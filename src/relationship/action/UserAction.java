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
import relationship.dao.UserDAO;
import relationship.persistence.PublicPersistence;
import relationship.forms.PublicForm;


public class UserAction extends DispatchAction 
{
	
	public ActionForward userLoginRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "success";
		
		UserDAO userDAO = new DAOFactory().getUserDAO();
		
		PublicForm publicForm  = (PublicForm)form;
		
		String username = publicForm.getUsername();
		String password = publicForm.getPassword();
		 
		boolean loginStatus = userDAO.checkUserLogin(username,password);
		
		if(loginStatus == false){
			target = "failure";
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("userLoginFailure", username));
			saveErrors(request, errors);			
		}
		else{
			HttpSession session=request.getSession();
			session.setAttribute("username",username);				
		}
		
		return mapping.findForward(target);
	}

	
	public ActionForward userLogoutRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "logout";
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");

		session.invalidate();
		
		return mapping.findForward(target);
	}
	
	
}
