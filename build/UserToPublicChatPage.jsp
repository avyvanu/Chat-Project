<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<html>
<head>
<link rel="stylesheet" href="tableborder.css" type="text/css" media="screen, projection" />
<script language="javascript">
function logout()
{
window.document.PublicForm.action="publicRequest.do?parameter=userChatLogout";
window.document.PublicForm.submit();
	
}
</script>

<style type="text/css">
body {
    background-image:url(portalimages/Crunchify.bg_.300.png);
}
</style>
<script language="javascript" src="AjaxChatMessageScript.js"></script>
<script type="text/javascript" src="jquery-1.10.1.min.js"></script>
<script language="javascript" src="AjaxScript.js"></script>
<script>
    $(document).ready(
            function() {
                setInterval(function() {
					ajaxChatMessage()
                    var randomnumber = Math.floor(Math.random() * 100);
                    $('#show').text('');
                }, 3000);
            });
</script>

</head>

<body onLoad="ajaxChatMessage()">



<html:form  action="publicRequest.do?parameter=userChat" name="PublicForm"  type="relationship.forms.PublicForm"> 


<div id="show" align="center"></div>
  <p>&nbsp;</p>
  <div align="left">
    <table width="504" border="1"  class="altrowstable">
      <tr>
        <td width="131">Name</td>
        <td width="357"><%if(session.getAttribute("publicName")==null){%><%}else{%><%=session.getAttribute("publicName")%><%}%></td>
      </tr>
       
      <tr>
        <td  colspan="2"><div id="chatMessageList"></div></td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>Message</td>
        <td><textarea name="chatMessage" id="message" cols="45" rows="5"></textarea></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" name="send" id="send" value=" Send ">
         <input type="button" name="LogOut" id="LogOut" value=" LogOut  " onClick="logout()"></td>
      </tr>
    </table>
    
    <input type="hidden" name="public_oid" id="public_Id" value="<%if(session.getAttribute("public_Id")==null){%><%}else{%><%=session.getAttribute("public_Id")%><%}%>">
    
</html:form>


</body>
</html>