<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="user_id" value='<%=session.getAttribute("login")%>' />

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
<link rel="stylesheet"
	href="../CSS/purchase.css?<%=System.currentTimeMillis()%>" />
</head>
<script language="javascript">
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}

	function jusoCallBack(roadFullAddr, zipNo) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$("input:text[name=roadFullAddr]").val(roadFullAddr);
		$("input:text[name=zipNo]").val(zipNo);
	}
</script>
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
			<div class="basket-wrap">
				<h3>purchase</h3>

				<!-- 상품 보여주기 -->
				<form name="user_info" action="purchaseOk.do" method="POST">
				<div class="goods_wrap"></div>
				<input type="hidden" name="session_chk" value="${user_id }" />
				
				<!-- 유저 정보 입력하기 -->
					<div class="input_info">
						<div class="inputs">
							<input type="hidden" name="user_id" value="${user_id }" /> 
							<input type="text" name="name" id="name" value="" placeholder="Name" />
							<input type="text" name="phone" id="phone" value="" placeholder="phone" />
							<div class="address">
								<input type="text" id="zipNo" name="zipNo" placeholder="zip code" readonly /> 
								<input type="text" id="roadFullAddr" name="roadFullAddr" value="" placeholder="address" readonly /> 
								<input type="button" onClick="goPopup();" value="검색하기" class="button"></input>
							</div>
						</div>
					</div>

					<!-- 카드 정보 입력하기 -->
					<div class="card">
						<div class="card_wrap">
							<div class="card_radio">
								<input type="radio" id="priority-normal" name="card_select" value="new_card" checked /> 
								<label for="priority-normal">새로운 카드사용</label> 
								<input type="radio" id="priority-low" name="card_select" value="user_card" /> 
								<label for="priority-low">저장된 카드사용</label>
							</div>
							<div class="card-infomation">
								<div class="card_select">
									<select name="category" id="category">
										<option value="0">- 카드사 -</option>
										<option value="1">국민</option>
										<option value="2">신한</option>
										<option value="3">농협</option>
										<option value="4">하나</option>
										<option value="5">기업</option>
									</select>
								</div>
								<div class="card_number">
									<input type="text" name="num1" id="num1" value="" placeholder="카드번호" /> 
									<input type="text" name="num2" id="num2" value="" placeholder="" /> 
									<input type="text" name="num3" id="num3" value="" placeholder="" /> 
									<input type="text" name="num4" id="num4" value="" placeholder="" />
								</div>
								<div class="card_info">
									<input type="text" name="year" id="year" value="" placeholder="YY" /> 
									<input type="text" name="month" id="month" value="" placeholder="MM" /> 
									<input type="password" name="cvc" id="cvc" value="" placeholder="cvc" />
								</div>
								<div class="card_passowrd">
									<input type="password" name="card_passowrd" id="card_passowrd" value="" placeholder="passoword" />
								</div>

								<!-- 카드 등록 버튼 -->
								<div class="card-button button">카드등록하기</div>
							</div>
						</div>
					</div>
				</form>
				<!-- 총 가격 -->
				<div class="total-price"></div>

				<!-- 버튼들 -->
				<div class="buttons">
					<div class="purchase-btn button" onclick="pur_submit()">결제하기</div>
					<div class="button">
						<a href="goods_list.do?cate=1">돌아가기</a>
					</div>
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
	<script id="purchase" data-id="${user_id }"
		src="../JS/purchase.js?<%=System.currentTimeMillis() %>"></script>
	<script src="../../index.js"></script>
</body>

</html>