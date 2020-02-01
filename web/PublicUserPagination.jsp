<head>

<link rel="stylesheet" type="text/css" href="PortalForm.css"/>
<script type="text/javascript" src="AjaxScript.js"></script>
<script type="text/javascript" src="PublicUserPaginationScript.js"></script>	
<!--<meta http-equiv="refresh" content="5;url=javascript:refreshPagination();"> -->


<style type="text/css"> 
.listwrapper 
{ 
  overflow: auto; 
  width: 100%; 
} 

.color-radio {
    -webkit-appearance: none;
    background-color: #0F0;
    border: 1px solid #cacece;
    box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
    padding: 9px;
    border-radius: 50px;
    display: inline-block;
    position: relative;	
}


.nocolor-radio {
    -webkit-appearance: none;
    background-color: #F00;
    border: 1px solid #cacece;
    box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
    padding: 9px;
    border-radius: 50px;
    display: inline-block;
    position: relative;
}

</style> 
   
   <script type="text/javascript" src="jquery-1.10.1.min.js"></script>
<script>
    $(document).ready(
            function() {
                setInterval(function() {
					ajaxPagination()
                    var randomnumber = Math.floor(Math.random() * 100);
                    $('#show').text('');
                }, 30000);
            });
</script>

   
</head>
Online Users:   <%if(session.getAttribute("onlineUserCount")==null){%><%}else{%><%=session.getAttribute("onlineUserCount")%><%}%>
<br><br>
<body onLoad="ajaxPagination()">

 <div id="show" align="center"></div>

<div style="float:left">Show Entries
<select name="showentries" onChange="showEntries()" id="ShowEntries">
<option>25</option>
<option>10</option>
<option>50</option>
<option>75</option>
<option>100</option>
<option>200</option>
</select>
</div>

<div style="float:right">Search
<input name="searchitem" type="text" onKeyDown="paginationBySearch()" id="searchItem" />
</div>



<div class="listwrapper" style="padding:0px;float:left">
<span id="ajaxpaginationlist"></span>
</div>



