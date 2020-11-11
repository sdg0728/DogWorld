<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.purchase.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="gdto" value='<%=(GoodsDTO)request.getAttribute("GDTO") %>' />
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />

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
    <link rel="stylesheet" type="text/css"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets/css/main.css?<%=System.currentTimeMillis()%>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="../JS/adminGoodsDetail.js?<%= System.currentTimeMillis()%>"></script>
    <link rel="stylesheet" href="../CSS/goods-detail.css?<%= System.currentTimeMillis()%>" />
    <link rel="stylesheet" href="../CSS/admin.css?<%= System.currentTimeMillis()%>" />
    <link rel="stylesheet" href="../CSS/adminGoodsDetail.css?<%= System.currentTimeMillis()%>" />
</head>
<script type="text/javascript">
	$(window).load(() => {
		if($("input:hidden[name=session_chk]").val() == ""){
			location.href = "../../index.do";
		}
	});
	function chkDelete() {
		return confirm("삭제 하시겠습니까?");
	}
	
	function chkUpdate() {
		return confirm("수정 하시겠습니까?");
	}
	
	function chkCount() {
		let $count_val = $("#count").val();
		if($count_val == null || $count_val == ""){
			alert("수량을 입력해 주세요.");
			return false;
		} else{
			$('#cart_count').val($count_val);
			return true;
		}
	}
	
	function chkPurchase() {
		if($("#count").val() == null || $("#count").val() == ""){
			alert("수량을 입력해 주세요.");
			return false;
		} else{
			return true;
		}
	}
</script>
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
                <h3>detail</h3>
            </div>
            
            <!-- 상품 설명 -->
            <form name="update-frm" action="GoodsUpdateOk.do" method="post" enctype="multipart/form-data">
            <div class="goods-detail">
            <input type="hidden" name="session_chk" value="${user_id }" />
                	<div class="goods">
                    <!-- 상품 이미지 -->
	                    <div class="goods-title">
	                        <img class="ori-img" src="${gdto.img }" />
	                    </div>
	
	                    <!-- 상품 설명 -->
	                    <div class="goods-content">
							<div>
								<h3 class="g_name">${gdto.name }</h3>
							</div>
							<div>
								<span>가격 : </span><span class="g_price">${gdto.price }</span><span>원</span>
							</div>
							<div>
								<span>현재수량: </span><span class="g_count">${gdto.count }</span><span>개</span>
							</div>
						</div>
						
	                    <!-- 버튼들 -->
	                    <div class="buttons">
	                        <div class="input-wrap hidden">
	                        	<input type="text" name="cart_count" id="count" value="" placeholder="수량입력" />
	                        </div>	
                        	<input type="hidden" name="cart_count" id="cart_count" value="" placeholder="${gdto.count }" />
                        	<input type="hidden" name="goods_id" value="${gdto.goods_id }" />
                        	<div class="btn-wrap btm">
	                        	<button type="button" class="button cart" onclick="location.href='goodsDeleteOk.do?goods_id=${gdto.goods_id}'">삭제하기</button>
		                        <button type="button" name="replace" class="button pur" onclick="replaceDetail(this)">수정하기</button>
		                        <button type="submit" name="register" class="button pur hidden" onclick="">등록하기</button>
	                        </div>
	                    </div>
                	</div>
                    <!-- 수정 버튼 -->
                    <div class="img_inputs">
                        <div class="img hidden">
                        	<span>썸네일 이미지: </span><input type="file" name="file1" required  accept=".gif, .jpg, .png, .jpeg"/>
                        </div>
                        <div class="img_detail hidden">
                        	<span>상세정보 이미지: </span><input type="file" name="file2" required accept=".gif, .jpg, .png, .jpeg"/>
                        </div>
                       </div>
            </div>
			</form>
            <!-- 상품 상세 설명 -->
            <div class="detail">
                <div class="button alt fit view_detail">상세보기</div>
                <div class="details">
                    <div class="detail_img">
						<img class="ori-de-img" src="${gdto.detail_img }" />
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
    <script src="https://kit.fontawesome.com/3fc65ab30c.js" crossorigin="3fc65ab30c"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="../../assets/js/skel.min.js"></script>
    <script src="../../assets/js/util.js"></script>
    <script src="../../assets/js/main.js"></script>
    <script src="../JS/goods_detail.js?<%= System.currentTimeMillis()%>"></script>
    
    
	
</body>

</html>