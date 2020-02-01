package relationship.pagination;



import java.util.ArrayList;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import relationship.singleton.SingletonDAO;
import relationship.dao.DAOFactory;
import relationship.dao.ChatDAO;
import relationship.dao.PublicDAO;


public class PublicUserPagination extends DispatchAction
{	
	public ActionForward PaginationFirst(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "PaginationFirst";
		
		HttpSession session=request.getSession();
		
		session.setAttribute("search",null);
		session.setAttribute("membershipaccountpaginationpropertywise",null);
		session.setAttribute("membershipaccountpaginationcheckInDate",null);
		
		String username=(String)session.getAttribute("username");
		
		if(username == null) 
		{
			target = "sessionError";
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
			int fixedpages = 10;
			int showentries = 10;
			
			String noshowentries = request.getParameter("showentries");				
			if(noshowentries != null)
			{
				session.setAttribute("utilitybillpaginationshowentries",noshowentries);				
				showentries = Integer.parseInt(noshowentries);
			}
			
			String search = request.getParameter("search");	
				
			String query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public order by public.public_oid desc ";
			
			if(search != null)
			{
				session.setAttribute("search",search);
				String accountsearch = search+"%";					
				query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public where (public.publicName like '%"+search+"%' or public.publicEmail like '%"+search+"%' or public.publicPhoneNo like '%"+search+"%') order by public.public_oid desc ";
			}
			getPaginationBill(request,fixedpages,showentries,query,p);
		}
		return mapping.findForward("");
	}
	
	
	public ActionForward PaginationNextButton(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "PaginationNextButton";
				
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		
		if(username == null) 
		{
			target = "sessionError";
		}
		else 
		{
			int maxpages = 10;
			int showentries = 10;

			String noshowentries=(String)session.getAttribute("utilitybillpaginationshowentries");			
			if(noshowentries != null)
			{
				session.setAttribute("utilitybillpaginationshowentries",noshowentries);				
				showentries = Integer.parseInt(noshowentries);
			}
			
			
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
				
			String query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public order by public.public_oid desc ";

			String search=(String)session.getAttribute("search");	
			if(search != null)
			{
				session.setAttribute("search",search);
				String accountsearch = search+"%";
				query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public where (public.publicName like '%"+search+"%' or public.publicEmail like '%"+search+"%' or public.publicPhoneNo like '%"+search+"%') order by public.public_oid desc ";
			}
			
			String result=request.getParameter("results");
			int resultrow = Integer.parseInt(result);
				
			String pagesno=request.getParameter("pageno");
			int pageno = Integer.parseInt(pagesno);
					
			getPaginationBillByNextButton(request,resultrow,pageno,showentries,maxpages,query,p);
		}
		return mapping.findForward("");
	}
		
	public ActionForward nextpages(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "nextpages";
		
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
			 
		if(username == null) 
		{
			target = "sessionError";
		}
		else 
		{
			int showentries = 10;
			
		
			String noshowentries=(String)session.getAttribute("utilitybillpaginationshowentries");			
			if(noshowentries != null)
			{
				session.setAttribute("utilitybillpaginationshowentries",noshowentries);				
				showentries = Integer.parseInt(noshowentries);
			}
			
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
				
			String query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public order by public.public_oid desc ";
			
			String search=(String)session.getAttribute("search");	
			if(search != null)
			{
				session.setAttribute("search",search);
				String accountsearch = search+"%";
				query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public where (public.publicName like '%"+search+"%' or public.publicEmail like '%"+search+"%' or public.publicPhoneNo like '%"+search+"%') order by public.public_oid desc ";
			}
			
			String results=request.getParameter("results");
			int result = Integer.parseInt(results);
					
			getNextpages(request,result,showentries,query,p);
		}			
		
	
		return mapping.findForward("");
	}
	
	public ActionForward PaginationBackButton(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "PaginationBackButton";
		
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
			 
		if(username == null) 
		{
			target = "sessionError";
		}
		else 
		{
			int showentries = 10;
			int fixedpages = 10;
			
			
			String noshowentries=(String)session.getAttribute("utilitybillpaginationshowentries");			
			if(noshowentries != null)
			{
				session.setAttribute("utilitybillpaginationshowentries",noshowentries);				
				showentries = Integer.parseInt(noshowentries);
			}
			
			
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
			String query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public order by public.public_oid desc ";
			
			String search=(String)session.getAttribute("search");	
			if(search != null)
			{
				session.setAttribute("search",search);
				String accountsearch = search+"%";
				query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public where (public.publicName like '%"+search+"%' or public.publicEmail like '%"+search+"%' or public.publicPhoneNo like '%"+search+"%') order by public.public_oid desc ";
			}
				
			String results=request.getParameter("results");
			int result = Integer.parseInt(results);
				
			String pagesno=request.getParameter("pageno");
			int pageno = Integer.parseInt(pagesno);				
			
			PaginationBackButton(request,result,pageno,showentries,query,fixedpages,p);
		}
		
		return mapping.findForward("");	
	}
	
	public ActionForward PaginationLastButton(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target = "PaginationLastButton";		
		
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
			 
		if(username == null) 
		{
			target = "sessionError";
		}
		else 
		{
			int showentries = 10;
			int fixedpages = 10;			
			
			
			String noshowentries=(String)session.getAttribute("utilitybillpaginationshowentries");			
			if(noshowentries != null)
			{
				session.setAttribute("utilitybillpaginationshowentries",noshowentries);				
				showentries = Integer.parseInt(noshowentries);
			}
			
			
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
				
				
			String query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public order by public.public_oid desc ";
				
			String search=(String)session.getAttribute("search");	
			if(search != null)
			{
				session.setAttribute("search",search);
				String accountsearch = search+"%";
				query=" select public.public_oid, public.publicName,  public.publicEmail, public.publicPhoneNo, public.message, public.status   from PublicPersistence  as public where (public.publicName like '%"+search+"%' or public.publicEmail like '%"+search+"%' or public.publicPhoneNo like '%"+search+"%') order by public.public_oid desc ";
			}
			
			String results=request.getParameter("results");
			int result = Integer.parseInt(results);
				
			String pagesno=request.getParameter("pageno");
			int pageno = Integer.parseInt(pagesno);						
					
			PaginationLastButton(request,result,pageno,showentries,query,fixedpages,p);
			
		}			
		return mapping.findForward("");
	}
	
	
	/*public ActionForward userDeactive(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target="userDeactive";
		
		HttpSession session = request.getSession();
		String sessionusername = (String)session.getAttribute("username");
		
		if(sessionusername == null)
		{
			target = "sessionError";
			return mapping.findForward(target);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
			UserDaoImpl userDAO = new UserDaoImpl();
					
			String requestUserId = request.getParameter("requestUserId");			
			if(requestUserId != null)
			{
				int userId = Integer.parseInt(requestUserId);
				
				int update = userDAO.setpropertyUserDeActive(userId);
				if(update != 0){
					p.print(" User Account is Deactive ");
				}
				else{
					p.print(" User Account is Not Deactive ");
				}
			}			
		}		
		return mapping.findForward("");
	}*/
	
	public ActionForward publicChatMessageList(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target="publicChatMessageList";
		
		response.setContentType("text/html");
		PrintWriter p=response.getWriter();
				
		String public_Id = request.getParameter("public_Id");			
		if(public_Id != null)
		{	
			int uId = Integer.parseInt(public_Id);
			getChatMessageList(uId,p);
		}
		else{
			p.print(" Error Occured ");
		}
		
		return mapping.findForward("");
	}
	
	
	public ActionForward chatToPublicUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String target="chatToPublicUser";
		
		HttpSession session = request.getSession();
		String sessionusername = (String)session.getAttribute("username");
		
		if(sessionusername == null)
		{
			target = "sessionError";
			return mapping.findForward(target);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter p=response.getWriter();
			
			String publicId = request.getParameter("requestPublicId");
			
			int public_Id = Integer.parseInt(publicId);
			
			PublicDAO publicDAO = new DAOFactory().getPublicDAO();
			String publicName = publicDAO.getPublicNameByPublicId(public_Id);
			
			session.setAttribute("public_Id",public_Id);
			session.setAttribute("publicName",publicName);
		}		
		return mapping.findForward("");
	}
	
	
	public void getPaginationBill(HttpServletRequest request,int maxpages,int showentries,String query,PrintWriter p)
	{	
		String sessionUser = "";
		
		Session ses=SingletonDAO.openSession();		
		try
		{   
			String trappend = "", lastbutton = "";
			int lastpageno = 0, pageno = 1,  pagelimit = 1,  results = 0,  count = 0 , showresultsto = 0,  lastresults = 0;
			
			String href = "<li><a href=javascript:paginationnextpages("+0+","+1+")>"+pageno+"</a></li>";
			p.print("<table class='newTableStyle'>");p.print("<thead><tr>");
			p.print("<td>SlNo</td>");p.print("<td>Name</td>");
			p.print("<td>Email Address</td>");p.print("<td>PhoneNo</td>");p.print("<td>Message</td>");p.print("<td>Status</td>");
			p.print("</tr></thead>");
			
			Query quer=ses.createQuery(query);
			List list=quer.list();
			if(list.contains(null))
			{
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					count++;
					results++;
						
					Object[] objectArray = (Object[])itr.next();
					
					int userId = (Integer)objectArray[0];
					String status = (String)objectArray[5];
					if(status.equals("connected")){
						sessionUser = "<input type='radio' class='color-radio' checked/>";
					}
					else{
						sessionUser = "<input type='radio' class='nocolor-radio' checked/>";
					}
					String publicMessage = (String)objectArray[4];
					if(publicMessage.length() >= 50){
						publicMessage = publicMessage.substring(0,30);
					}
					
					if( (pagelimit == 1 ) && (count <= showentries))
					{
						String tr = "<tbody><tr>";
						String slno = "<td>"+count+"</td>";						
						String name="<td><a href=javascript:chatToPublicUser("+userId+")>"+(String)objectArray[1]+"</a></td>";
						String emaiAddress = "<td>"+(String)objectArray[2]+"</td>";
						String phoneNo = "<td>"+(String)objectArray[3]+"</td>";	
						String message = "<td>"+publicMessage+"</td>";
						String publicStatus = "<td>"+sessionUser+"</td>";						
						String trend="</tr></tbody>";
						
						
						
						String t= tr+slno+name+emaiAddress+phoneNo+message+publicStatus+trend;
						p.print(t);
						showresultsto++;
					}						
					
					if( (pagelimit <= (maxpages-1) ) && (count > showentries))
					{
						count = 1;
						pageno++;
						//href = href+" "+"<a href=PropertyBillPdf.do?parameter=nextpages&results="+results+"&pageno="+pageno+" id='second'>"+pageno+"</a>";
						href = href+"<li><a href=javascript:paginationnextpages("+(results-1)+","+pageno+")>"+pageno+"</a></li>";
						pagelimit++;
					}
					else if((pagelimit == maxpages) && (count > showentries)) 
					{
						//href = href+" "+"<a href=PropertyBillPdf.do?parameter=PaginationNextButton&results="+results+"&pageno="+pageno+" id='second'>Next</a>";
						href = href+"<li class='next'><a href=javascript:paginationNextButton("+(results-1)+","+pageno+")>Next</a></li>";
						//break;
						lastpageno=pageno;
						count = 1;
						pagelimit++;
					}
					else if((pagelimit > maxpages) && (count > showentries))
					{	
						lastbutton  = "<li class='last'><a href=javascript:paginationLastButton("+(results-1)+","+lastpageno+")>Last</a></li>"; 
						lastpageno++;
						count = 1;
						lastresults = results;
					}
				}
			}
			
			if( (lastresults != 0)&& (lastresults != results) && (results > lastresults) )
			{
				lastbutton  = "<li class='last'><a href=javascript:paginationLastButton("+(results)+","+lastpageno+")>Last</a></li>";
			}
			href = href + lastbutton;
			
			HttpSession session =request.getSession();			
			session.setAttribute("pages",href);
			session.setAttribute("totalentries",results);
			// If No Records Found 
			if(results == 0)
			{
				p.print("<tfoot><tr><td colspan='6'>No Records Found</td></tr></tfoot>");
			}
			else
			{
				p.print("<tfoot><tr><td colspan='6'><ul id='pagination-flickr'>"+(String)session.getAttribute("pages")+"</ul>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Showing 1 to "+showresultsto+" of "+results+"</td></tr></tfoot>");	
			}
			p.print("</table>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(ses);	
			
		}
	}
		
	
	public void getNextpages(HttpServletRequest request,int results,int showentries,String query,PrintWriter p)
	{
		String sessionUser = "";
		
		Session ses=SingletonDAO.openSession();
		
		try
		{
			int showresultsto = results;
			int showresultsfrom = results+1;
			
			p.print("<table class='newTableStyle'>");p.print("<thead><tr>");
			p.print("<td>SlNo</td>");p.print("<td>Name</td>");
			p.print("<td>Email Address</td>");p.print("<td>PhoneNo</td>");p.print("<td>Message</td>");p.print("<td>Status</td>");
			p.print("</tr></thead>");
			
			String trappend = "";		
			int count = 0;
			int pages = 1;			
							
			Query quer=ses.createQuery(query);
			quer.setFirstResult(results);
			quer.setMaxResults(showentries);
			List list=quer.list();
			if(list.contains(null))
			{
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					count++;
					
					Object[] objectArray = (Object[])itr.next();
					
					int userId = (Integer)objectArray[0];
					String status = (String)objectArray[5];
					if(status.equals("connected")){
						sessionUser = "<input type='radio' class='color-radio' checked/>";
					}
					else{
						sessionUser = "<input type='radio' class='nocolor-radio' checked/>";
					}
					String publicMessage = (String)objectArray[4];
					if(publicMessage.length() >= 50){
						publicMessage = publicMessage.substring(0,30);
					}
					
					String tr = "<tbody><tr>";
					String slno = "<td>"+count+"</td>";						
					String name="<td>"+(String)objectArray[1]+"</td>";
					String emaiAddress = "<td>"+(String)objectArray[2]+"</td>";
					String phoneNo = "<td>"+(String)objectArray[3]+"</td>";	
					String message = "<td>"+publicMessage+"</td>";
					String publicStatus = "<td>"+sessionUser+"</td>";						
					String trend="</tr></tbody>";
					
					String t= tr+slno+name+emaiAddress+phoneNo+message+publicStatus+trend;
					
					p.print(t);
					showresultsto++;
				}				
			}
			
			HttpSession session =request.getSession();
			p.print("<tfoot><tr><td  colspan='6'><ul id='pagination-flickr'>"+(String)session.getAttribute("pages")+"</li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Showing "+showresultsfrom+" to "+showresultsto+" of"+(Integer)session.getAttribute("totalentries")+"</td></tr></tfoot>");
			p.print("</table>");
		//	tx.commit();
			//session.setAttribute("rows",trappend);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(ses);	
			
		}
			
		//return al;
	}
	
	
	public void getPaginationBillByNextButton(HttpServletRequest request,int results,int pageno,int showentries,int maxpages,String query,PrintWriter p)
	{	
		HttpSession session =request.getSession();
		//ArrayList al=new ArrayList();
		String sessionUser = "";
		
		Session ses=SingletonDAO.openSession();
		
		try
		{
			int showresultsfrom = results+1;
			int showresultsto = results;	
			
			String firstbutton = "<li class='first'><a href=javascript:ajaxPagination()>First</a><li>";
			//String href=firstbutton+" "+"<a href=PropertyBillPdf.do?parameter=PaginationBackButton&results="+results+"&pageno="+pageno+">Back</a>";			
			String href=firstbutton+" "+"<li class='previous'><a href=javascript:paginationPreviousButton("+(results)+","+pageno+")>Previous</a>";
			//href = href+" "+"<a href=javascript:paginationnextpages("+results+","+(pageno++)+")>"+(pageno++)+"</a>";
			p.print("<table class='newTableStyle'>");p.print("<thead><tr>");
			p.print("<td>SlNo</td>");p.print("<td>Name</td>");
			p.print("<td>Email Address</td>");p.print("<td>PhoneNo</td>");p.print("<td>Message</td>");p.print("<td>Status</td>");
			p.print("</tr></thead>");
			
			int pagelimit =1;
			int count = 0;				
			
			Query quer=ses.createQuery(query);
			quer.setFirstResult(results);
			//quer.setMaxResults(2);
			List list=quer.list();
			if(list.contains(null))
			{
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					count++;
					results++;
					
					Object[] objectArray = (Object[])itr.next();
					
					int userId = (Integer)objectArray[0];
					String status = (String)objectArray[5];
					if(status.equals("connected")){
						sessionUser = "<input type='radio' class='color-radio' checked/>";
					}
					else{
						sessionUser = "<input type='radio' class='nocolor-radio' checked/>";
					}
					String publicMessage = (String)objectArray[4];
					if(publicMessage.length() >= 50){
						publicMessage = publicMessage.substring(0,30);
					}
					
					if( (pagelimit == 1) && (count <= showentries))
					{
						String tr = "<tbody><tr>";
						String slno = "<td>"+count+"</td>";						
						String name="<td>"+(String)objectArray[1]+"</td>";
						String emaiAddress = "<td>"+(String)objectArray[2]+"</td>";
						String phoneNo = "<td>"+(String)objectArray[3]+"</td>";	
						String message = "<td>"+publicMessage+"</td>";
						String publicStatus = "<td>"+sessionUser+"</td>";						
						String trend="</tr></tbody>";
						
						String t= tr+slno+name+emaiAddress+phoneNo+message+publicStatus+trend;
						
						p.print(t);
						showresultsto++;
					}
					
					
					if(( pagelimit <= maxpages) && (count == showentries))
					{
						count = 0;
						pageno++;
						//href = href+" "+"<a href=PropertyBillPdf.do?parameter=nextpages&results="+results+"&pageno="+pageno+" >"+pageno+"</a>";
						//ajaxhref = ajaxhref+" "+"<a href=javascript:ajaxlist("+results+")>"+pages+"</a>";
						href = href+" "+"<li><a href=javascript:paginationnextpages("+(results-showentries)+","+pageno+")>"+pageno+"</a></li>";
						
						pagelimit++;
					}
					
					else if( (pagelimit > maxpages)  && (count == showentries)) 
					{
						//href = href+" "+"<a href=PropertyBillPdf.do?parameter=PaginationNextButton&results="+results+"&pageno="+pageno+">Next</a>";
						href = href+" "+"<li class='next'><a href=javascript:paginationNextButton("+results+","+pageno+")>Next</a></li>";
						
						break;
					}	
				}				
			}
			
			if( (count == 0) || (pagelimit < maxpages) )
			{
				href=href+"<li><a href=#>Last</a></li>";
			}
			session.removeAttribute("pages");
			session.setAttribute("pages",href);
			
			p.print("<tfoot><tr><td  colspan='6'><ul id='pagination-flickr'>"+(String)session.getAttribute("pages")+"</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Showing "+showresultsfrom+" to "+showresultsto+" of"+(Integer)session.getAttribute("totalentries")+"</td></tr></tfoot>");
			p.print("</table>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(ses);	
			
		}
			
		//return al;
	}
	
		
	
	public ArrayList PaginationBackButton(HttpServletRequest request,int results,int pageno,int showentries,String query,int maxpages,PrintWriter p)
	{
		ArrayList al=new ArrayList();
		ArrayList listpagination=new ArrayList();
		String sessionUser = "";
		
		Session ses=SingletonDAO.openSession();
		
		try
		{
			int showresultsfrom = (results-showentries)+1;
			int showresultsto = (results-showentries);
			
			p.print("<table class='newTableStyle'>");p.print("<thead><tr>");
			p.print("<td>SlNo</td>");p.print("<td>Name</td>");
			p.print("<td>Email Address</td>");p.print("<td>PhoneNo</td>");p.print("<td>Message</td>");p.print("<td>Status</td>");
			p.print("</tr></thead>");
			
			StringBuffer sb = new StringBuffer();
			String firstbutton = "<li class='first'><a href=javascript:ajaxPagination()>First</a>";
			String href=" ";			
			int count = 0;
			int pages = 1;			
			
			Query quer=ses.createQuery(query);
			quer.setFirstResult(results-showentries);
			quer.setMaxResults(showentries);
			List list=quer.list();
			if(list.contains(null))
			{
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					count++;
					
					Object[] objectArray = (Object[])itr.next();
					
					int userId = (Integer)objectArray[0];
					String status = (String)objectArray[5];
					if(status.equals("connected")){
						sessionUser = "<input type='radio' class='color-radio' checked/>";
					}
					else{
						sessionUser = "<input type='radio' class='nocolor-radio' checked/>";
					}
					String publicMessage = (String)objectArray[4];
					if(publicMessage.length() >= 50){
						publicMessage = publicMessage.substring(0,30);
					}
					
					if(count <= showentries)
					{
						String tr = "<tbody><tr>";
						String slno = "<td>"+count+"</td>";						
						String name="<td>"+(String)objectArray[1]+"</td>";
						String emaiAddress = "<td>"+(String)objectArray[2]+"</td>";
						String phoneNo = "<td>"+(String)objectArray[3]+"</td>";	
						String message = "<td>"+publicMessage+"</td>";
						String publicStatus = "<td>"+sessionUser+"</td>";						
						String trend="</tr></tbody>";
						
						String t= tr+slno+name+emaiAddress+phoneNo+message+publicStatus+trend;					
						
						p.print(t);
						
						showresultsto++;
					}
						
				}
			}
						
			//String nextbutton ="<a href=PropertyBillPdf.do?parameter=PaginationNextButton&results="+results+"&pageno="+pageno+" id='second'>Next</a>";
			String nextbutton ="<li class='next'><a href=javascript:paginationNextButton("+results+","+pageno+")>Next</a></i>";
			
			results = results-showentries;
			
			
			for(int i=1;i<=maxpages;i++)
			{	// commented pagination variable
				//String pagination =" "+"<a href=PropertyBillPdf.do?parameter=nextpages&results="+results+"&pageno="+pageno+">"+pageno+"</a>"; 
				String pagination = "<li><a href=javascript:paginationnextpages("+results+","+pageno+")>"+pageno+"</a><li>";
				
				listpagination.add(pagination);
				pageno--;
				results = results-showentries;				
			}
			
			href = reversePagination(listpagination,href);
			
			href = href+" "+nextbutton;
			if(pageno != 0)
			{
				//String backbutton = "<a href=PropertyBillPdf.do?parameter=PaginationBackButton&results="+results+"&pageno="+pageno+">Back</a>";
				String backbutton = "<li class='previous'><a href=javascript:paginationPreviousButton("+results+","+pageno+")>Previous</a></li>";
				href =firstbutton+" "+backbutton+" "+href;
			}
			
			HttpSession session =request.getSession();			
			session.setAttribute("pages",href);
			
			p.print("<tfoot><tr><td  colspan='6'><ul id='pagination-flickr'>"+(String)session.getAttribute("pages")+"</li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Showing "+showresultsfrom+" to "+showresultsto+" of"+(Integer)session.getAttribute("totalentries")+"</td></tr></foot>");
			p.print("</table>");
			
		//	tx.commit();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(ses);
			
		}
			
		return al;
	}
	
	public String reversePagination(ArrayList listpagination, String href)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(href);
		
		ListIterator<String> listIterator = listpagination.listIterator();
		while(listIterator.hasNext())
			listIterator.next();
		
		while(listIterator.hasPrevious())
		{
			String p = listIterator.previous();
			//href = href+" "+p;
			sb.append(" ");
			sb.append(p);
		}
		href = sb.toString();
		
		return href;
	}
	
	public ArrayList PaginationLastButton(HttpServletRequest request,int results,int pageno,int showentries,String query,int maxpages,PrintWriter p)
	{
		
		ArrayList al=new ArrayList();
		ArrayList listpagination=new ArrayList();
		String sessionUser = "";
		
		Session ses=SingletonDAO.openSession();
		try
		{
			int showresultsfrom = (results-showentries);
			int showresultsto = results;
			
			p.print("<table class='newTableStyle'>");p.print("<thead><tr>");
			p.print("<td>SlNo</td>");p.print("<td>Name</td>");
			p.print("<td>Email Address</td>");p.print("<td>PhoneNo</td>");p.print("<td>Message</td>");p.print("<td>Status</td>");
			p.print("</tr></thead>");	
			
			StringBuffer sb = new StringBuffer();
			String firstbutton = "<li class='first'><a href=javascript:ajaxPagination()>First</a></li>";
			String href=" ";			
			int count = 0;
			int pages = 1;			
						
			Query quer=ses.createQuery(query);
			quer.setFirstResult(results-showentries);
			quer.setMaxResults(showentries);
			List list=quer.list();
			if(list.contains(null))
			{
			}
			else
			{
				Iterator itr=list.iterator();
				while(itr.hasNext())
				{
					count++;
					
					Object[] objectArray = (Object[])itr.next();	
					
					int userId = (Integer)objectArray[0];
					String status = (String)objectArray[5];
					if(status.equals("connected")){
						sessionUser = "<input type='radio' class='color-radio' checked/>";
					}
					else{
						sessionUser = "<input type='radio' class='nocolor-radio' checked/>";
					}
					String publicMessage = (String)objectArray[4];
					if(publicMessage.length() >= 50){
						publicMessage = publicMessage.substring(0,30);
					}
					
					if(count <= showentries)
					{
						String tr = "<tbody><tr>";
						String slno = "<td>"+count+"</td>";						
						String name="<td>"+(String)objectArray[1]+"</td>";
						String emaiAddress = "<td>"+(String)objectArray[2]+"</td>";
						String phoneNo = "<td>"+(String)objectArray[3]+"</td>";	
						String message = "<td>"+publicMessage+"</td>";
						String publicStatus = "<td>"+sessionUser+"</td>";						
						String trend="</tr></tbody>";
						
						String t= tr+slno+name+emaiAddress+phoneNo+message+publicStatus+trend;
						
						p.print(t);
					}	
				}
			}
						
			
			//String nextbutton ="<a href=PropertyBillPdf.do?parameter=PaginationNextButton&results="+results+"&pageno="+pageno+" id='second'>Next</a>";
			String nextbutton = "";//"<a href=javascript:paginationNextButton("+results+","+pageno+")>Next</a>";
			
			results = results-showentries;
			
			
			for(int i=1;i<=maxpages;i++)
			{	// commented pagination variable
				//String pagination =" "+"<a href=PropertyBillPdf.do?parameter=nextpages&results="+results+"&pageno="+pageno+">"+pageno+"</a>"; 
				String pagination = "<li><a href=javascript:paginationnextpages("+results+","+pageno+")>"+pageno+"</a></li>";
				
				listpagination.add(pagination);
				pageno--;
				results = results-showentries;
			}
			
			href = reversePagination(listpagination,href);
			
			href = href+" "+nextbutton;
			if(pageno != 0)
			{
				//String backbutton = "<a href=PropertyBillPdf.do?parameter=PaginationBackButton&results="+results+"&pageno="+pageno+">Back</a>";
				String backbutton = "<li class='previous'><a href=javascript:paginationPreviousButton("+results+","+pageno+")>Previous</a></li>";
				href =firstbutton+" "+backbutton+" "+href;
			}
			
			HttpSession session =request.getSession();			
			session.setAttribute("pages",href);
			
			p.print("<tfoot><tr><td colspan='6'><ul id='pagination-flickr'>"+(String)session.getAttribute("pages")+"</li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Showing "+showresultsfrom+" to "+(showresultsto)+" of"+(Integer)session.getAttribute("totalentries")+"</td></tr></tfoot>");
			p.print("</table>");
			
		//	tx.commit();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(ses);
		}
			
		return al;
	}
	
	
	public void getChatMessageList(int public_Id, PrintWriter p)
	{	
		StringBuffer sb = new StringBuffer();
		Session session = SingletonDAO.openSession();		
		try
		{
			String query_execute="select u.chatMessage, u.chatBy from ChatMessagePersistence as u where u.publicId='"+public_Id+"' ";
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
					Object[] objectArray = (Object[])itr.next();	
					
					String chatMessage = (String)objectArray[0];
					String chatBy = (String)objectArray[1];
					
					sb.append(chatBy+":       "+chatMessage);
					sb.append("<br>");sb.append("<br>");
				}				
				p.print(sb.toString());
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			p.print(" Error Occurred ");
		}
		finally
		{
			SingletonDAO.closeSession(session);	
			sb = null;
		}
	}

}

