<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />

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
<link rel="stylesheet" href="../CSS/upload.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<script>
	//form validation
	// 작성자(name)와 제목(subject)은 필수

	function chkSubmit() {
		frm = document.forms['frm'];

		var image = frm["image"].value.trim();

		if (image == "파일명") {
			alert("이미지를 첨부해주세요.");
			frm["image"].focus(); //focus()는 해당 form element에 입력 포커스 전환
			return false;
		}
		console.log("submit!")
		return true;

	}
</script>
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
	<form name="frm" action="uploadOk.do" method="post" onsubmit="return chkSubmit()" enctype="Multipart/form-data">
		<section id="main" class="wrapper">

			<div class="container">
				<h3>Upload</h3>
				<div id="feed-rapper">
					<input type="hidden" name="user_id" value="${user_id }" /><!-- 아이디박기 -->
					<div class="img_upload">
						<img id="upload_img" />
					</div>
					<textarea name="comment_rapper" id="comment_rapper"></textarea>
					<a id="upload_btn" href="#" class="button alt small"
						data-toggle="modal" data-target="#myModal">완료</a>
					<!-- The Modal -->
					<div class="filebox preview-image" style="margin-left: 290px;">
						<input class="upload-name" value="파일명" name="image" disabled="disabled"> 
						<label for="input-file">찾기</label> 
						<input type="file" id="input-file" name="upload_img" class="upload-hidden" accept=".gif, .jpg, .png, .jpeg">
						
					</div>
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Upload</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body" style="text-align: center;">게시물을
									업로드 하시겠습니까?</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
									<button type="submit" class="btn btn-danger" >업로드</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form>
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
	<script src="../JS/upload.js"></script>

</body>
</html>