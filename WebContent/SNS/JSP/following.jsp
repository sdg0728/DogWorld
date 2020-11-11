<%@page import="beans.SNS.CommentDTO"%>
<%@page import="beans.SNS.WriteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:set var="arr" value='<%=(WriteDTO[]) request.getAttribute("followingFeed")%>' />
<c:set var="comArr" value='<%=(CommentDTO[]) request.getAttribute("comList")%>' />
<c:set var="login" value='<%=session.getAttribute("login") %>' />


<!DOCTYPE HTML>
<!--
	Spatial by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Generic - Spatial by TEMPLATED</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="../../assets/css/main.css" />
		<link rel="stylesheet" href="../CSS/following.css"/>
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<h1><a href="../../index.do"><img src="../../images/Logo_black.png"/></a></h1>
				<nav id="nav">
					<ul> 
						
						<li><a href="following.do?user_id=${login }">following</a></li>
						<li><a href="upload.do?user_id=${login }">Upload</a></li>
						<li><a href="myfeed.do?user_id=${login }">My Feed</a></li>
						<li><a href="search.do?user_id=${login }">search</a></li>
					</ul>
				</nav>
			</header>

			<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

		<!-- Main -->
				<section id="main" class="wrapper">
			
				<div class="container">
						<h3>following</h3>
		<c:forEach var="feed"  items="${arr }" varStatus="status"><!-- 피드 배열 for문 -->
						<div id="feed-rapper">
							
							<div id="profile">
								<div class="pro_img">
								</div>
							<div id="pro_id">
								<a class="profile_id" href="#">${feed.user_id}</a>
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
						
						<div id="likes${status.index }" class="like">
							<div id="box${status.index }" class="icon fa-heart"></div>	
							<div id="likes_cnt${status.index }" class="like_cnt">${like } likes</div> <!-- 좋아요 개수 -->
						</div>
						
					</div>

					
					<div class="comment_rapper">
					<form action="feedcommentOk.do">
							<div class="comment">
								<c:forEach var="com" items="${comArr }">
									<c:if test="${com.post_id == feed.post_id }">
										<div class='com'>
											<img src='' />
											<p class='userId'>${com.user_id }</p>
											<div>${com.content }</div>
										</div>
										<hr>
									</c:if>
								</c:forEach>
							</div>

							<input type="hidden" id="postId${status.index }" name="post_id" value="${feed.post_id }" />
							<input type="hidden" name="user_id" value="${login }" /> 
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
			<script id="myfeed" data-id="${login }" src="../JS/myfeed.js"></script> 
				
			
	</body>
</html>