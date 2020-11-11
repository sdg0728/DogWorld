<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.purchase.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="arr" value='<%=(GoodsDTO[]) request.getAttribute("list")%>' />
<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<!DOCTYPE HTML>
<!--
        Spatial by TEMPLATED
        templated.co @templatedco
        Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
    -->
<html>

<head>
<title>Dog World</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../../assets/css/main.css?<%=System.currentTimeMillis()%>" />
<link rel="stylesheet" href="../CSS/list.css?<%=System.currentTimeMillis()%>" />

</head>

<body>
	<!-- Header -->
	<header id="header">
		<h1>
			<a href="../../index.do"><img src="../../images/Logo_black.png" /></a>
		</h1>
		<nav id="nav" class="actions">
			<ul>
				<li class="goods_list"><a href="goods_list.do?cate=1">list</a></li>
				<li><a href="basket.do">cart</a></li>
				<li><a href="orders.do">orders</a></li>
				<li>
					<div id="login_id">
						<div>${login }님</div>
						<div>
							<a href="../../userinfo/JSP/logoutOk.do" style="color: #333;">LOGOUT</a>
						</div>
						<form id="frm_mypage" method="post" action="../../userinfo/JSP/myaccount.do">
							<input type="hidden" name="id" value="${login }">
							<div><a id="mypage_btn" href="#" style="color:#333;">MYPAGE</a></div>
						</form>
					</div>
				</li>
			</ul>
		</nav>
	</header>

	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- Main -->
	<section id="list_main" class="warpper">
		<div class="container list_background">
			<div class="wrap_div">
				<input type="hidden" name="session_chk" value="${login }" />
				<!-- 상품 분류 메뉴바 -->
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<span class="navbar-brand">상품리스트</span>
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item">
								<div class="nav-link" cate="1">
									<a href="?cate=1">먹거리</a>
								</div>
							</li>
							<li class="nav-item">
								<div class="nav-link" cate="2">
									<a href="?cate=2">미용용품</a>
								</div>
							</li>
							<li class="nav-item">
								<div class="nav-link" cate="3">
									<a href="?cate=3">의류</a>
								</div>
							</li>
						</ul>
					</div>
				</nav>

				<!-- 상품 리스트 -->
				<div class="goods_wrap">
					<c:forEach var="element" items="${arr }" varStatus="status">
						<div class="goods" cate="${element.category }">
							<div>
								<img src="${element.img }" />
							</div>
							<div>
								<h3>
									<a href="goods_detail.do?goods_id=${element.goods_id }">${element.name }</a>
								</h3>
								<p class="price">${element.price } 원</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="../../assets/js/skel.min.js"></script>
	<script src="../../assets/js/util.js"></script>
	<script src="../../assets/js/main.js"></script>
	<script src="../JS/list.js?<%= System.currentTimeMillis()%>"></script>
	<script src="../../index.js"></script>
</body>

</html>