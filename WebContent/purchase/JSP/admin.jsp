<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.purchase.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="arr" value='<%=(GoodsDTO[]) request.getAttribute("GoodsList")%>' />
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
<link rel="stylesheet" href="../CSS/admin.css?<%=System.currentTimeMillis()%>" />

</head>

<body>
	<!-- Header -->
	<header id="header">
		<h1>
			<a href="admin.do"><img src="../../images/Logo_black.png" /></a>
		</h1>
		<nav id="nav" class="actions">
			<ul>
				<li class="goods_list"><a href="admin.do">goods</a></li>
				<li><a href="adminGoodsOrders.do">orders</a></li>
				<li>
					<div id="login_id">
						<div>${login }님</div>
						<div>
							<a href="../../userinfo/JSP/logoutOk.do" style="color: #333;">LOGOUT</a>
						</div>
						<div>
							<a href="" style="color: #333;">MYPAGE</a>
						</div>
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
				<!-- 상품 리스트 -->
				<div class="goods_wrap">
					<div>
						<h3>goods</h3>
						<button type="button" class="button addGoods" onclick="location.href='GoodsAdd.do'">추가하기</button>
					</div>
					<table class="goods-list-table">
					<tr>
						<td>상품ID</td><td>상품명</td><td>가격</td><td>재고</td><td>분류</td>
					</tr>
					<c:forEach var="element" items="${arr }" varStatus="status">
						<tr>
							<td>${element.goods_id }</td><td><a href="adminGoodsDetail.do?goods_id=${element.goods_id }">${element.name }</a></td>
							<td>${element.price }</td><td>${element.count }</td><td>${element.category }</td>
						</tr>
					</c:forEach>
					</table>
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
	<script src="../JS/list.js"></script>

</body>

</html>