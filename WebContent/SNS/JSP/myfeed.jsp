<%@page import="beans.SNS.FriendsDTO"%>
<%@page import="beans.SNS.CommentDTO"%>
<%@page import="beans.SNS.WriteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:set var="arr" value='<%=(WriteDTO[]) request.getAttribute("list")%>' />
<c:set var="comArr" value='<%=(CommentDTO[]) request.getAttribute("comList")%>' />
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />



<!DOCTYPE HTML>
<html>
<head>
<title>Generic - Spatial by TEMPLATED</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../../assets/css/main.css" />
<link rel="stylesheet" href="../CSS/myfeed.css" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
	<section id="main" class="wrapper">

		<div class="container">
			<h3>My Feed</h3>
			<div class="friend">

				<!-- Button to Open the Modal -->

				<button class="friends_btn" type="button" data-toggle="modal" data-target="#follower">팔로잉</button>
				<button class="following_btn" type="button" data-toggle="modal" data-target="#myModal">팔로워</button>

				<!-- The Modal -->
				<div class="modal" id="follower">
					<div class="modal-dialog modal-dialog-scrollable">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">following</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<table class="follower-table"></table>
							</div>
							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>

				<!-- The Modal -->
				<div class="modal" id="myModal">
					<div class="modal-dialog modal-dialog-scrollable">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">follower</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<table class="following-table"></table>
							</div>
							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
				</div>
			</div>
		<c:forEach var="feed"  items="${arr }" varStatus="status">
				<!-- 피드 배열 for문 -->
				<div id="feed-rapper">

					<div id="profile">
						<div class="user_info">	
						<div class="pro_img">
							<!-- 펫정보에서 이미지 가져오기 -->
						</div>
						<div id="pro_id">
							<a class="profile_id" href="Popular.html">${user_id }</a>
							<!-- 회원정보에서 아이디 가져오기 -->
						</div>
						</div>
						<div class="edit_btn">
							<a href="#" class="button alt small modify_btn" data-toggle="modal" data-target="#mod_text">수정</a><!-- 수정 -->
							<a href="deleteOk.do?post_id=${feed.post_id }" class="button alt small del_btn">삭제</a>
							<!-- 삭제 -->
						</div>
					</div>
					<!-- The Modal -->
					<div class="modal" id="mod_text">
						<div class="modal-dialog modal-dialog-scrollable">
							<div class="modal-content">

								<!-- Modal Header -->
								<form action="modifyOk.do" method="post">
									<div class="modal-header">
										<h4 class="modal-title">edit</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<textarea class="modal-body" name="content">${feed.content }</textarea>
									<!-- 피드 내용   수정한거 돌려줘야됨-->
									<!-- 수정할 글 가져오기 -->

									<!-- Modal footer -->
									<div class="modal-footer">
										<input type="hidden" name="post_id" value="${feed.post_id }" />
										<button type="submit" class="btn btn-danger">완료</button>
									</div>
								</form>
							</div>
						</div>
					</div>



					<div class="content_rapper">
						<div class="feed_img">
							<img src="${feed.img }">
							<!-- 업로드된 이미지 가져오기 -->
						</div>
						<div class="following-text">
							<div class="post_date">${feed.postdate }</div>
							<span>${feed.content }</span>
							<!-- 업로드한 글 가져오기 -->
						</div>
						<br>
						<div class="like">

							<div id="likes${status.index }" class="like">
							<div id="box${status.index }" class="icon fa-heart"></div>	
							<div id="likes_cnt${status.index }" class="like_cnt">${like } likes</div> <!-- 좋아요 개수 -->
						</div>
						</div>

					</div>

					<div class="comment_rapper">
						<form action="commentOk.do">
							<div class="comment">
								<c:forEach var="com" items="${comArr }">
									<c:if test="${com.post_id == feed.post_id }">
										<div class='com' onclick="deleteCom(${com.comment_id})">
											<img src='' />
											<p class='userId'>${com.user_id }</p>
											<div>${com.content }</div>
										</div>
										<hr>
									</c:if>
								</c:forEach>
							</div>

							<!-- <input type="hidden" name="post_id" value="${feed.post_id }" /> -->
							<input type="hidden" id="postId${status.index }" name="post_id" value="${feed.post_id }" />
							<input type="hidden" name="user_id" value="${user_id }" /> 
							<input id="comment-text" type="text" name="content" value=""
								placeholder="댓글을 입력해주세요." onkeypress="enterSubmit()" />
						</form>
					</div>
					<br>




				</div>
				<hr>
			</c:forEach>
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
	<script id="myfeed" data-id="${user_id }" src="../JS/myfeed.js"></script>
</body>
</html>