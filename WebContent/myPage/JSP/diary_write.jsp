<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<% 
	String id = request.getParameter("id");
	String regdate = request.getParameter("regdate");
	
	System.out.println("넘어오냐? id:" + id);
	System.out.println("넘어오냐? regdate:" + regdate);

%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/diary_write.css" />
	<title>다이어리 작성</title>
</head>
<body>

	<!-- Header -->
	<header id="header">
		<h1>
			<a href="../../index.do"><img src="../../images/Logo_black.png" /></a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="myPet.do">My Pet</a></li>
				<li><a href="schedule.do">Schedule</a></li>
				<li><a href="diary.do">Diary</a></li>
				<li>
					<div id="login_id">
						<div>${login }님</div>
						<div><a href="../../userinfo/JSP/logoutOk.do" style="color:#333;">LOGOUT</a></div>
						<form id="frm_mypage" method="post" action="userinfo/JSP/myaccount.do">
							<input type="hidden" name="id" value="${login }">
							<div><a id="mypage_btn" href="#" style="color:#333;">MYPAGE</a></div>
						</form>
					</div>
				</li>
			</ul>
		</nav>
	</header>
	
	<section id="sign_secA">
		<div class="container">
			
			<form method="post" action="diary_writeOk.do" name="diaryWrite" enctype="multipart/form-data">
			
			<input type="hidden" id="input_id" name="id" value="${param.id }">
			<input type="hidden" id="input_date" name="regdate" value="${param.regdate }">
			
			<label for="title"><b>제목</b></label>
            <input type="text" id="input_title" name="title" placeholder="제목 입력..." required>
            <p></p>
            
            <label><b>사진</b></label>
            <div class="filebox preview-image"> 
            	<input class="upload-name" value="파일선택" disabled="disabled" > 
            	<label for="input-file">사진</label> 
            	<input type="file" id="input-file" class="upload-hidden" name="upfile" accept=".gif, .jpg, .png, .jpeg"> 
            </div>
            <p></p>
            
            <label for="content"><b>내용</b></label>
            <div class="12u$">
				<textarea name="content" id="input_content" placeholder="Enter your message" rows="6"></textarea>
			</div>
          	<p></p>
          	
            <button type="submit" id="btn_login">작성</button>
            
            </form>
            
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

</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="../JS/diary_write.js" type="text/javascript"></script>

</html>