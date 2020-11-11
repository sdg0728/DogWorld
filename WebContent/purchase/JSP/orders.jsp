<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:set var="user_id" value='<%=(String)session.getAttribute("login") %>' />   
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/css/main.css?<%=System.currentTimeMillis()%>" />
    <link rel="stylesheet" href="../CSS/orders.css?<%=System.currentTimeMillis() %>" />
    <script src="../../assets/js/jquery.min.js"></script>
</head>

<body>
    <!-- Header -->
    <header id="header">
        <h1><a href="../../index.do"><img src="../../images/Logo_black.png" /></a></h1>
        <nav id="nav" class="actions">
            <ul>
                <li class="goods_list"><a href="goods_list.do?cate=1">list</a></li>
                <li><a href="basket.do">cart</a></li>
                <li><a href="orders.do">orders</a></li>
                <li>
                	<div id="login_id">
						<div class="user_id">${user_id }</div>
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
        <input type="hidden" name="session_chk" value="${user_id }" />
            <div class="title">
                <h3>orders</h3>
            </div>
            <div class="goods_wrap">
                <!-- 구매한 상품 리스트 -->
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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="../../assets/js/skel.min.js"></script>
    <script src="../../assets/js/util.js"></script>
    <script src="../../assets/js/main.js"></script>
    <script src="../JS/orders.js?<%=System.currentTimeMillis()%>"></script>
	<script src="../../index.js"></script>
</body>

</html>