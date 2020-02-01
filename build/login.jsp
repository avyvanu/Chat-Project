<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%   	  	
String sessionusername=(String)session.getAttribute("username");		
if(sessionusername != null)
{
	RequestDispatcher rd=request.getRequestDispatcher("/userTiles.do");
	rd.forward(request,response);
}
%>

<html>
<head>
<link rel="stylesheet" href="tableborder.css" type="text/css" media="screen, projection" />
<title>Insert title here</title>
</head>
<body>


<html:form  action="userLogin.do?parameter=userLoginRequest" name="PublicForm"  type="relationship.forms.PublicForm"> 

  <p>&nbsp;</p>
  <div align="left">
    <table width="504" border="1"  class="altrowstable">
      <tr>
        <td width="69">Username</td>
        <td width="419"><input type="text" name="username" id="username"></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="text" name="password" id="password"></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" name="button" id="button" value=" Login "><html:errors/></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</html:form>
</body>
</html>