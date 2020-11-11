<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<% String userId = (String)pageContext.getAttribute("login");%> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../CSS/diet.css?ver=<%= System.currentTimeMillis()%>" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<title>체중관리</title>
		
</head>
<body>

	<form action="">
		<input id="diet_id" type="hidden" value="${login }">
	</form>
	
	
	<div class="inbody">
	
		<div class="view_weight">
			<div class="now_weight">
				<a id="input_weight" class="button small">입력</a>
				<a id="delete_weight" class="button small">삭제</a>
			</div>
		</div>
	
		<div class="diet_view">
			<div class="view_graph">
				<div id="chart_div"></div>
			</div>
		</div>
	
	</div>
	
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<form id="frm_id" name="frm" action="" method="get" onsubmit="return chkSubmit()">
	      			<div class="modal-body">
	          				<div class="form-group">
	            				<label for="recipient-name" class="col-form-label">체중 입력:</label>
      							<input id="petId" type="hidden" value="" name="petId">
	            				<input type="text" class="form-control" id="recipient-name" name="weight">
	          				</div>
	      			</div>
	      			<div class="modal-footer">
	        			<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	        			<button type="submit" class="btn btn-primary">입력</button>
	      			</div>
        		</form>
    		</div>
  		</div>
	</div>
	
	<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<form id="frm_delete" name="frm_delete" action="" method="get" onsubmit="return deleteSubmit()">
	      			<div class="modal-body">
	          				<div class="form-group">
	          					<input id="petId2" type="hidden" value="" name="petId2">
	            				<p id="delete_val"></p>
	          				</div>
	      			</div>
	      			<div class="modal-footer">
	        			<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	        			<button type="submit" class="btn btn-primary">삭제</button>
	      			</div>
        		</form>
    		</div>
  		</div>
	</div>


</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="../JS/diet.js" type="text/javascript"></script>
</html>