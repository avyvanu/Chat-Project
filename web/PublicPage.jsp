<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<html>
<head>
<link rel="stylesheet" href="tableborder.css" type="text/css" media="screen, projection" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<html:form  action="publicRequest.do?parameter=sendPublicRequest" name="PublicForm"  type="relationship.forms.PublicForm"> 

  <p>&nbsp;</p>
  <div align="left">
    <table width="504" border="1" class="altrowstable">
	<tr>
        <td colspan="2">Welcome To Chat</td>
      </tr>

      <tr>
        <td width="69">Name</td>
        <td width="419"><input type="text" name="customerName" id="customerName"></td>
      </tr>
      <tr>
        <td>Email</td>
        <td><input type="text" name="customerEmail" id="customerEmail"></td>
      </tr>
      <tr>
        <td>Phone No</td>
        <td><input type="text" name="customerPhoneNo" id="customerPhoneNo"></td>
      </tr>
      <tr>
        <td>Message</td>
        <td><textarea name="message" id="message" cols="45" rows="5"></textarea></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" name="button" id="button" value="Submit"></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  <a href="login.jsp">Login</a>  </div>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</html:form>


</body>
</html>