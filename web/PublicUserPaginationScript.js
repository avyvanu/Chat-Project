// JavaScript Document
/* Pagination Starts  */

var ajaxpaginationlist = "ajaxpaginationlist";
var showEntriesId = "ShowEntries";
var searchItemId = "searchItem";
var branchIdWiseId = "branchIdWise";
var actionId = "action";
var startBookingDateId = "startBookingDate";
var endBookingDateId = "endBookingDate";




/*
setInterval(function(){ 
alert("set Interval")
    //code goes here that will be run every 5 seconds.   
	 ajaxPagination()
}, 5000);
*/

function refreshPagination()
{
	var searchitem = document.getElementById(searchItemId).value;
	alert(searchitem)
	if(searchitem == ""){
		  
		  ajaxPagination()
	}
}

function ajaxPagination()
{
	
	document.getElementById(ajaxpaginationlist).innerHTML='<p><img src="images/loading.gif"/></p>';
    createRequestObject(); 
    var url="publicUserPagination.do?parameter=PaginationFirst"; 
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
			document.getElementById(ajaxpaginationlist).innerHTML = html;
        }
		else 
		{
          alert('There was a problem with the request.'+request.readyState+","+request.status);
		}
     } 
}  


function paginationnextpages(results,pageno)
{
	document.getElementById(ajaxpaginationlist).innerHTML='<p><img src="images/loading.gif"/></p>';
	createRequestObject(); 
    var url="publicUserPagination.do?parameter=nextpages&results="+results+"&pageno="+pageno 
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
	   
}


function paginationNextButton(results,pageno)
{
	document.getElementById(ajaxpaginationlist).innerHTML='<p><img src="images/loading.gif"/></p>';
	createRequestObject(); 
    var url="publicUserPagination.do?parameter=PaginationNextButton&results="+results+"&pageno="+pageno
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
	   
}

function paginationPreviousButton(results,pageno)
{
	document.getElementById(ajaxpaginationlist).innerHTML = '<p><img src="images/loading.gif"/></p>';
	createRequestObject(); 
    var url="publicUserPagination.do?parameter=PaginationBackButton&results="+results+"&pageno="+pageno
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
	   
}

function paginationLastButton(results,pageno)
{
	document.getElementById(ajaxpaginationlist).innerHTML = '<p><img src="images/loading.gif"/></p>';

	createRequestObject(); 
    var url="publicUserPagination.do?parameter=PaginationLastButton&results="+results+"&pageno="+pageno
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
}

function showEntries()
{
	document.getElementById(ajaxpaginationlist).innerHTML = '<p><img src="images/loading.gif"/></p>';
	var showentries = document.getElementById(showEntriesId).options[document.getElementById(showEntriesId).selectedIndex].value;		
	
	createRequestObject();	
    var url="publicUserPagination.do?parameter=PaginationFirst&showentries="+showentries
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage;
    request.send(null); 
}

function paginationBySearch()
{
	document.getElementById(ajaxpaginationlist).innerHTML='<p><img src="images/loading.gif"/></p>';
	var searchitem = document.getElementById(searchItemId).value;
	
	createRequestObject(); 
    var url="publicUserPagination.do?parameter=PaginationFirst&search="+searchitem
    request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
}



function getBranchWisePagination()
{
	document.getElementById(ajaxpaginationlist).innerHTML='<p><img src="images/loading.gif"/></p>';
	var branchId=document.getElementById(branchIdWiseId).options[document.getElementById(branchIdWiseId).selectedIndex].value;
	
	createRequestObject();
	var url="publicUserPagination.do?parameter=PaginationFirst&propertywise="+branchId
 	request.open("GET",url,true); 
    request.onreadystatechange=updatePaginationPage; 
    request.send(null); 
}






function getActions()
{
	var action = document.getElementById(actionId).options[document.getElementById(actionId).selectedIndex].value;
	
	if(action == "RefreshPage")
	{
		ajaxPagination()
	}
	if(action == "AddUser")
	{
		createWindowWithBranchUserCreation()
	}
}


function chatToPublicUser(publicId)
{
		var conf = confirm("Are you sure you want to make chat with user ");
	if(conf == true){
	createRequestObject();
	var url="publicUserPagination.do?parameter=chatToPublicUser&requestPublicId="+publicId
 	request.open("GET",url,true); 
    request.onreadystatechange=updateUserEditPage; 
    request.send(null);	
	}
	
}
 
 
function updateUserEditPage() 
{ 
     if(request.readyState==4) 
     { 
     	if (request.status ==200) 
		{        
        	var html = request.responseText;
			
			var WindowObjectReference = window.open("UserToPublicChatPage.jsp",
	"DescriptiveWindowName",	"width=900,height=500,resizable,scrollbars=yes,status=1","location=yes");
	
        }
		else 
		{
          alert('There was a problem with the request.'+request.readyState+","+request.status);
		}
     } 
}


function userActive(userId)
{
	var conf = confirm("Are you sure you want to make user account as active");
	if(conf == true){
	createRequestObject();
	var url="publicUserPagination.do?parameter=userActive&requestUserId="+userId
 	request.open("GET",url,true); 
    request.onreadystatechange=updateActivePage; 
    request.send(null);	
	}
	
}
 
 
function updateActivePage() 
{ 
     if(request.readyState==4) 
     { 
     	if (request.status ==200) 
		{        
        	var html = request.responseText;
			alert(html)
			ajaxPagination()
        }
		else 
		{
          alert('There was a problem with the request.'+request.readyState+","+request.status);
		}
     } 
}


function emaiAdminUser(username)
{
	var conf = confirm("Are you sure you want to send welcome message to user ");
	if(conf == true){
	createRequestObject();
	var url="publicUserPagination.do?parameter=emailAdminUser&requestUserId="+username
 	request.open("GET",url,true); 
    request.onreadystatechange=updateUserSettingPage; 
    request.send(null);	
	}
	
}
 
 
function updateUserSettingPage() 
{ 
     if(request.readyState==4) 
     { 
     	if (request.status ==200) 
		{        
        	var html = request.responseText;
			alert(html)
			ajaxPagination()
        }
		else 
		{
          alert('There was a problem with the request.'+request.readyState+","+request.status);
		}
     } 
}


/* Pagination Ends  */