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
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/weather.css?ver=<%= System.currentTimeMillis()%>" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
	<title>날씨</title>
</head>
<body>

	<form action="">
		<input id="weather_id" type="hidden" value="${login }">
	</form>

	<div class="weahter_view">
		<h3 id="today"></h3>
		<div class="d-flex justify-content-center weather_box">
		
		</div>
	</div>

</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="../JS/weather.js?ver=<%= System.currentTimeMillis()%>" type="text/javascript"></script>
</html>