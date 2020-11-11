<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="user_id" value='<%=(String)session.getAttribute("login")%>' />
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../../assets/css/main.css?<%=System.currentTimeMillis()%>" />
<link rel="stylesheet" href="../CSS/list.css?<%=System.currentTimeMillis()%>" />
<link rel="stylesheet" href="../CSS/admin.css?<%=System.currentTimeMillis()%>" />
<link rel="stylesheet" href="../CSS/goodsAdd.css?<%=System.currentTimeMillis()%>" />
</head>

<body class="landing">
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
	
	<!-- main -->
	<section id="list_main" class="warpper">
		<div class="container list_background">
			<div class="wrap_div">
			<input type="hidden" name="session_chk" value="${user_id }" />
				<div>
					<h3>add goods</h3>
				</div>
				<form name="reg-frm" action="goodsAddOk.do" method="POST" enctype="multipart/form-data" onsubmit="return submitChk()">
					<input type="text" name="name" placeholder="상품명을 입력해 주세요." required/>
					<input type="text" name="price" placeholder="가격을 입력해 주세요." required/>
					<select name="category" required>
						<option value="0">- 분류 -</option>
						<option value="1">먹거리</option>
						<option value="2">미용용품</option>
						<option value="3">의류</option>
					</select>
					<input type="text" name="count" placeholder="개수을 입력해 주세요." required />
					<div class="thumbnail">
						<label>썸네일 이미지: </label><input type="file" name="file1" required  accept=".gif, .jpg, .png, .jpeg"/>
					</div>
					<div class="detail-img">
						<label>상세정보 이미지: </label><input type="file" name="file2" required  accept=".gif, .jpg, .png, .jpeg"/>
					</div>
					<button type="submit" class="button fit" name="add-btn">추가하기</button>
				</form>
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
	<script src="../JS/goodsAdd.js?<%= System.currentTimeMillis()%>"></script>

</body>

</html>
