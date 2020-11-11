<%@page import="beans.SNS.WriteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value='<%=(WriteDTO[])request.getAttribute("list") %>' />
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />

<!DOCTYPE HTML>
<html>
<head>
<title>Generic - Spatial by TEMPLATED</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../../assets/css/main.css" />
<link rel="stylesheet" href="../CSS/search.css" />

</head>
<body>

	<!-- Header -->
	<header id="header">
		<h1><a href="../../index.do"><img src="../../images/Logo_black.png"/></a></h1>
		<nav id="nav">
			<ul>

				<li><a href="following.do?user_id=${user_id }">following</a></li>
				<li><a href="upload.do?user_id=${user_id }">Upload</a></li>
				<li><a href="myfeed.do?user_id=${user_id }">My Feed</a></li>
				<li><a href="search.do?user_id=${user_id }">Search</a></li>
			</ul>
		</nav>
	</header>

	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- Main -->
	<!-- Table -->
	<section style="margin-left: 100px">
		<h3>Search</h3>

		
		<div class="4u 12u$(xsmall)">
			<!-- 아이디 검색 -->
			<input type="hidden" name="user_id" value="${user_id }" />
			<input id="search-text" type="text" name="name" value=""
				placeholder="ID 및 견종을 입력해주세요" />
		</div>
<br>
		<div class="table-wrapper">
			<table class="searchId_table"></table>
		</div>

	</section>
	<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<ul class="icons">
				<li><a href="#" class="icon fa-facebook"></a></li>
				<li><a href="#" class="icon fa-twitter"></a></li>
				<li><a href="#" class="icon fa-instagram"></a></li>
			</ul>
			<ul class="copyright">
				<li>&copy; Untitled</li>
				<li>Design: <a href="http://templated.co">TEMPLATED</a></li>
				<li>Images: <a href="http://unsplash.com">Unsplash</a></li>
			</ul>
		</div>
	</footer>

	<!-- Scripts -->
	<script src="../../assets/js/jquery.min.js"></script>
	<script src="../../assets/js/skel.min.js"></script>
	<script src="../../assets/js/util.js"></script>
	<script src="../../assets/js/main.js"></script>
	<script id="myfeed" data-id="${user_id }" src="../JS/popular.js"></script>


<!-- 버튼 색 변경  -->


</body>



</html>