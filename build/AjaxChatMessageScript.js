// JavaScript Document
/* Pagination Starts  */

var chatMessage = "chatMessageList";
var p_Id = "public_Id";


function ajaxChatMessage()
{
    createRequestObject(); 
	var public_Id = document.getElementById(p_Id).value;
    var url="publicUserPagination.do?parameter=publicChatMessageList&public_Id="+public_Id
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null);
}

function updatePaginationPage() 
{ 
     if(request.readyState==4) 
     { 
     	if (request.status ==200) 
		{        
        	var html = request.responseText;			
			document.getElementById(chatMessage).innerHTML = html;
        }
		else 
		{
          alert('There was a problem with the request.'+request.readyState+","+request.status);
		}
     } 
}  

/* Pagination Ends  */