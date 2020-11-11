<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css?<%=System.currentTimeMillis() %>" />
<link rel="stylesheet" href="index.css?<%=System.currentTimeMillis() %>" />
</head>

<body class="landing">
	<!-- Header -->
	<header id="header" class="alt">
		<h1>
			<a href="index.do"><img src="images/Logo_white.png" /></a>
		</h1>
		<nav id="nav" class="actions">
			<ul>
				<c:if test="${login != null }">
					<li><a href="myPage/JSP/myPet.do">MY PAGE</a></li>
					<li><a href="SNS/JSP/following.do?user_id=${login }">SNS</a></li>
					<li><a href="purchase/JSP/goods_list.do?cate=1">SHOP</a></li>
					<li>
					<div id="login_id">
						<div class="user_id">${login }</div>
						<div>
							<a href="userinfo/JSP/logoutOk.do" style="color: #333;">LOGOUT</a>
						</div>
						<form id="frm_mypage" method="post" action="userinfo/JSP/myaccount.do">
							<input type="hidden" name="id" value="${login }">
							<div><a id="mypage_btn" href="#" style="color:#333;">MYPAGE</a></div>
						</form>
					</div>
				</li>
				</c:if>
				<c:if test="${login == null }">
					<li style="float:right; margin-left: 20px; margin-right: 20px;"><a href="userinfo/JSP/login.do" class="button special big">LOGIN</a></li>
					<li style="float:right;" ><a href="userinfo/JSP/sign.do" class="button special big">SIGN UP</a></li>
				</c:if>
			</ul>
		</nav>
	</header>

	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

	<!-- Banner -->
	<section id="banner">
		<div id="banner_txt_wrap">
			<h2>Dog World</h2>
			<p>
				우리가 꿈꾸는 편안한 세상 Dog World! <br /> 당신의 반려견을 초대합니다!
			</p>
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
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js?<%=System.currentTimeMillis() %>"></script>
	<script src="index.js?<%=System.currentTimeMillis() %>"></script>

</body>

</html>