<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.purchase.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="gdto" value='<%=(GoodsDTO)request.getAttribute("GDTO") %>' />
<c:set var="rdto" value='<%=(ReviewDTO[])request.getAttribute("RDTO") %>' />
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
    <link rel="stylesheet" href="../../assets/css/main.css?<%= System.currentTimeMillis()%>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../CSS/goods-detail.css?<%= System.currentTimeMillis()%>" />
</head>
<script type="text/javascript">
	function chkDelete() {
		return confirm("삭제 하시겠습니까?");
	}
	
	function chkUpdate() {
		return confirm("수정 하시겠습니까?");
	}
	
	function chkCount() {
		let $count_val = $("#count").val();
		let countCheck = RegExp(/[^0-9]$/);
		
		if($count_val == null || $count_val == ""){
			alert("수량을 입력해 주세요.");
			return false;
		} else{
			$('#cart_count').val($count_val);
			
			if(countCheck.test($("#count").val())){
				alert("숫자만 입력해 주세요.");
				return false;
			} else{
				return true;
			}
		}
		
	}
	
	function chkPurchase() {
		if($("#count").val() == null || $("#count").val() == ""){
			alert("수량을 입력해 주세요.");
			return false;
		} else{
			if(countCheck.test($("#count").val())){
				alert("숫자만 입력해 주세요.");
				return false;
			} else{
				return true;
			}
		}
	}
</script>
<body style="font-family:'Raleway', sans-serif">
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
						<div class="user_id">${login }</div>
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

    <!-- Main -->
    <section id="list_main" class="warpper">
        <div class="container list_background">
        <input type="hidden" name="session_chk" value="${user_id }" />
            <div class="title">
                <h3>detail</h3>
            </div>
            <!-- 상품 설명 -->
            <div class="goods-detail">
                <div class="goods">

                    <!-- 상품 이미지 -->
                    <div class="goods-title">
                        <img
                            src="${gdto.img }" />
                    </div>

                    <!-- 상품 설명 -->
                    <div class="goods-content">
                        <h3>${gdto.name }</h3>
                        <p>가격 : ${gdto.price }원</p>
                        
	                    <div style="background-color: #eee"><p><b>무료배송</b><br>CJ대한통운</p></div>
                    </div>
                    

                    <!-- 버튼들 -->
                    <div class="buttons">
	                    <form name="pur_frm" action="purchaseGo.do" method="POST">
	                        <div class="count">
	                            <input type="text" name="cart_count" id="count" value="" placeholder="수량입력" />
	                            <input type="hidden" name="goods_id" value="${gdto.goods_id }" />
	                        	<input type="hidden" name="user_id" value="${user_id }" />
	                        </div>
	                        <button type="submit" class="button pur" onclick="return chkPurchase()">구매하기</button>
                        </form>
                        <form name="cart_frm" action="cartGo.do" method="POST">
                        	<input type="hidden" name="cart_count" id="cart_count" value="" placeholder="수량입력" />
                        	<input type="hidden" name="goods_id" value="${gdto.goods_id }" />
                        	<input type="hidden" name="user_id" value="${user_id }" />
	                        <button type="submit" class="button cart" onclick="return chkCount()">장바구니</button>
                        </form>
                    </div>
                </div>
            </div>

            <!-- 상품 상세 설명 -->
            <div class="detail">
                <div class="button alt fit view_detail">상세보기</div>
                <div class="details">
                    <div class="detail_img">
						<img src="${gdto.detail_img }" />
					</div>
                </div>
            </div>

            <div class="title">
                <h3>review</h3>
            </div>
            
            <!-- 리뷰 작성 -->
			<div class="write_wrap">
				<form name="frm" action="writeOk.do" method="POST" enctype="Multipart/form-data">
					<input type="hidden" id="user_id" name="user_id" value="${user_id }">
					<input type="hidden" id="goods_id" name="goods_id" value="${gdto.goods_id }">
					<div class="write">
						<textarea name="content" placeholder="리뷰를 작성해 주세요."></textarea>
					</div>
					<div class="write_button">
						<div class="image_button_wrap">
							<input type="file" id="image" name="image" class="file_upload"  accept=".gif, .jpg, .png, .jpeg"/>
							<button type="submit" class="button small submit_button">등록</button>
						</div>
					</div>
				</form>
			</div>
            
            <!-- 상품 리뷰 -->
			<div class="review-wrap">
				<c:if test="${fn:length(rdto) != 0 }">
					<c:forEach var="element" items="${rdto }">
						<div class="review">
							<div class="review_img">
								<c:if test="${element.image != null}">
									<img src="${element.image }" />
								</c:if>
								<c:if test="${element.image == null}">
									<img />
								</c:if>
							</div>
							<div class="review_content">
								<span class="review_id">${element.user_id }</span>
								<p>${element.content }</p>
							</div>
							<c:if test="${element.user_id == user_id }">
								<form action="reviewDeleteOk.do" method="POST"
									onsubmit="return chkDelete()">
									<div class="btns">
										<button type="submit" id="del_btn" name="del_btn"
											class="button small del_btn">삭제</button>
										<button type="button" data-toggle="modal"
											data-target="#myModal" class="button small update_btn"
											onclick="setdata('${element.content }', '${element.image }')">수정</button>
										<input type="hidden" name="review_id"
											value="${element.review_id }" /> <input type="hidden"
											name="goods_id" value="${gdto.goods_id }" />
									</div>
								</form>
							</c:if>
						</div>


						<!-- The Modal -->
						<div class="modal" id="myModal">
							<div class="modal-dialog">
								<div class="modal-content">

									<!-- Modal Header -->
									<div class="modal-header">
										<h4 class="modal-title">${user_id }</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<form name="updatefrm" action="updateOk.do" method="POST"
										onsubmit="return chkUpdate()" enctype="Multipart/form-data">
										<div class="modal-body">
											<input type="hidden" name="review_id" value="${element.review_id }" /> 
											<input type="hidden" name="goods_id" value="${gdto.goods_id }" />
											<textarea id="upContent" name="upContent"></textarea>
											<input type="file" id="upImage" name="upImage"  accept=".gif, .jpg, .png, .jpeg"/>
											<button type="submit" class="button alt">수정</button>
										</div>
									</form>
								</div>
							</div>
						</div>

					</c:forEach>
				</c:if>

				<script type="text/javascript">
					function setdata(content, image) {
						$('#upContent').val(content);
					}
				</script>
				<c:if test="${fn:length(rdto) == 0 }">
					<div class="review">
						<div class="no_review">리뷰가 없습니다. 첫번째 리뷰를 작성해 주세요!</div>
					</div>
				</c:if>
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
    <script src="../../index.js"></script>
	
</body>

</html>