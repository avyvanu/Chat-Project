// JavaScript Document

var request = null;  
  
   function createRequestObject() 
   { 
		 try 
		 { 
			 request=new XMLHttpRequest();
		 } 
	     catch(othermicrosoft) 
    	 { 
			  try 
			  { 
				request=new ActiveXObject("Msxm12.XMLHTTP"); 
			  } 
			  catch(trymicrosoft) 
			  { 
				   try 
				   { 
						request=new ActiveXObject("Microsoft.XMLHTTP"); 
				   } 
				   catch(failed) 
				   { 
					   request = null; 
					   alert("Request object is null ..."); 
				   } 
			 } 
		} 
		if(request==null) 
		{ 
			  alert("Error Creating a request object ..."); 
		} 
  } 
  
  
  
  
	function displayTaxTypes(branchId)
	{
		var  branch_Id=document.getElementById(branchId).options[document.getElementById(branchId).selectedIndex].value;		
		createRequestObject(); 
		var url="taxtypescreation.do?parameter=getAjaxTaxTypesList&branchId="+branch_Id; 
		request.open("GET",url,true); 	
		request.onreadystatechange=updateTaxPage; 	
		request.send(null);
	}




