<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<% String id = (String)pageContext.getAttribute("login");  %>

<c:choose>
	<c:when test="${empty result || fn:length(result) == 0 }">
		<script>
			alert("해당 정보가 삭제되거나 없습니다");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/diary_update.css?ver=<%= System.currentTimeMillis()%>" />
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
	<title>다이어리 수정 ${result[0].title }</title>
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
		
			<form name="frm" action="diary_updateOk.do" method="post" onsubmit="return chkSubmit()" 
			enctype="Multipart/form-data">
			
			<input type="hidden" id="input_no" name="no" value="${result[0].no }">
			<input type="hidden" id="input_regdate" name="regdate" value="${result[0].regdate }">
			<input type="hidden" id="input_id" name="id" value="${result[0].id }">
			
			<label for="title"><b>제목</b></label>
            <input type="text" id="input_id" name="title" value="${result[0].title }" required>
            <p></p>
            
            <%-- 파일 삭제시 --%>
            <c:if test="${fn:length(fileList) > 0 }">
            
            	<div id="delFiles"></div> <%-- 삭제할 file 의 bf_uid 값(들)을 담기위한 div --%>
            
            
            </c:if>
            
            
            <label><b>사진</b></label>
         
          	<c:set var="image" value="${result[0].img}"/>
            
            	<c:choose>
					<c:when test="${empty image}">
						<div>
							<img id="uploadImg" style="width:100%" height="auto" src="../Image/noimg.gif"/>
						</div>
					</c:when>
				
					<c:otherwise>
						<div>
							<img id="uploadImg" style="width:100%" height="auto" src="../../upload/diary/${result[0].img }"/>
						</div>
					</c:otherwise>
				</c:choose>
             
            	<label class="btn btn-primary btn-file">
        			사진 <input id="up-file" type="file" name="upfile" onchange="upfiles(${fileList[0].no});" style="display: none; " accept=".gif, .jpg, .png, .jpeg">
    			</label>

            	<label class="btn btn-primary btn-file">  
            		삭제<input type="button" id="delete-file" name="deletefile" onclick="deleteFiles(${fileList[0].no});">
            	</label>
            
            <p></p>
            
            <label for="content"><b>내용</b></label>
            <div class="12u$">
				<textarea name="content" id="content" rows="6">${result[0].content }</textarea>
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
<script src="../JS/diary_update.js?ver=<%= System.currentTimeMillis()%>" type="text/javascript"></script>

</html>

	</c:otherwise>
</c:choose>
