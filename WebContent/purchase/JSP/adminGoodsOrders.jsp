<%@page import="beans.purchase.AdminPurchaseDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:set var="user_id" value='<%=(String)session.getAttribute("login") %>' />   
<c:set var="purchase" value='<%=(AdminPurchaseDTO[])request.getAttribute("all-purchase-list") %>' />   
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
    <link rel="stylesheet" href="../CSS/adminGoodsOrders.css?<%=System.currentTimeMillis() %>" />
    <script src="../../assets/js/jquery.min.js"></script>
</head>

<body>
    <!-- Header -->
    <header id="header">
        <h1><a href="admin.do"><img src="../../images/Logo_black.png" /></a></h1>
        <nav id="nav" class="actions">
            <ul>
                <li class="goods_list"><a href="admin.do">Goods</a></li>
                <li><a href="adminGoodsOrders.do">orders</a></li>
				<li>
					<div id="login_id">
						<div>${login }</div>
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

    <!-- Main -->
    <section id="list_main" class="warpper">
        <div class="container list_background">
        <input type="hidden" name="session_chk" value="${user_id }" />
            <div class="title">
                <h3>orders</h3>
            </div>
            <div class="goods_wrap">
                <!-- 구매한 상품 리스트 -->
                <c:forEach var="p" items="${purchase }">
                <c:choose>
                	<c:when test="${p.p_check == 0 }">
	                	<c:set var="chk" value="장바구니" />
                	</c:when>
                	<c:when test="${p.p_check == 1 }">
	                	<c:set var="chk" value="결제완료" />
                	</c:when>
                	<c:when test="${p.p_check == 2 }">
	                	<c:set var="chk" value="배송준비" />
                	</c:when>
                	<c:when test="${p.p_check == 3 }">
	                	<c:set var="chk" value="배송중" />
                	</c:when>
                	<c:when test="${p.p_check == 4 }">
	                	<c:set var="chk" value="배송완료" />
                	</c:when>
                </c:choose>
	                <div class='goods' data-chk='${p.purchase_id }' style="height: 180px; padding: 5px;">
		                <h3 style="margin-bottom: 0;">${p.goods_name }</h3>
		                <span>구매자 : ${p.name}</span>
		                <div class='date'>구매일자: ${p.buydate }</div>
		                <span>결제 금액: ${p.price * p.count }원 (수량 : ${p.count }개)</span><br>
		                <span class="chk" onclick="change(this, ${p.purchase_id })">배송상태 : ${chk }</span>
	                </div>
                </c:forEach>
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

</body>
<script type="text/javascript">
	$(window).load(() => {
		if($("input:hidden[name=session_chk]").val() == ""){
			location.href = "../../index.do";
		}
	});

	function change($this, chk){
		var text = $($this).parent().append("<select name='category' style='width: 100px; float: left;'>"
		+"<option value='1'>결제완료</option>"
		+"<option value='2'>배송준비</option>"
		+"<option value='3'>배송중</option>"
		+"<option value='4'>배송완료</option></select>");
		
		var p = $($this).parent();
		var sel = p.children("select[name=category]");
		
		p.append("<button type='button' class='button' name='btn' style='margin-left: 10px;'>수정하기</button>");
		
		p.children(".button")[0].addEventListener("click", function(){
			location.href = "updateChkOk.do?purchase_id="+chk+"&p_check="+sel.val();
		});
		
		$($this).remove();
	}
</script>
</html>