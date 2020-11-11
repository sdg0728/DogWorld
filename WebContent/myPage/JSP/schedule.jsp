<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<% String id = (String)pageContext.getAttribute("login");  %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../Fullcalendar/lib/main.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
	<link rel="stylesheet" href="../CSS/bootstrap-datepicker.css">
	<link rel="stylesheet" href="../CSS/schedule.css?ver=<%= System.currentTimeMillis()%>" />
	<link rel="stylesheet" type="text/css" href="../CSS/timepicki.css">
	<title>스케줄</title>
</head>

<body>

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
						<form id="frm_mypage" method="post" action="../../userinfo/JSP/myaccount.do">
							<input type="hidden" name="id" value="${login }">
							<div><a id="mypage_btn" href="#" style="color:#333;">MYPAGE</a></div>
						</form>
					</div>
				</li>
			</ul>
		</nav>
	</header>
	
	<section>
	
		<form action="">
				<input id="id_wanted" type="hidden" value="${login }">
		</form>
	
		<div class="calendar_box"> 
			<div id='calendar'></div>
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
	
	
   <div id="myModal" class="modal">
   		   	  
      <!-- Modal content -->
      <div class="modal-content">
        <span class="close">&times;</span>
        
        <div class="btn_class">
        	<button type="button" id="button1" class="button">month</button>
        	<button type="button" id="button2" class="button">day</button>
        </div>
        
        <div class="bin_space"></div>
        
        <div class="text_class">
        	        	
        	<div id="change_input">
        	
        		<div id="day_form">
        			<label for="datePicker">날짜 선택 </label>
        			<input type="text" id="datePicker" class="form-control">
        		</div>
        		<div class="boxClear"></div>
        			
        		<div id="time_form">
        			<label for="startTime">시작 시간 </label>
        			<input type="text" id="startTime" name="timepicker" class="time_element"/>
        			<div class="boxClear"></div>
        			<label for="endTime">종료 시간 </label>
        			<input type="text" id="endTime" name="timepicker" class="time_element"/>
        		</div>
        		
        	</div>
        	
        	<div class="boxClear"></div>
        	        	
	        <label for="title"><b>일정 입력 </b></label>
	        <input type="text" id="input_title" name="title">
	        <div class="boxClear"></div>
	        
	        <button type="submit" id="submit" class="button">등록</button>
	        
        </div>
        
      </div>
 
   </div>	
   
   <%-- 내용 확인용 모달 --%>
   <div id="contentModal" class="modal">
   		   	  
      <!-- Modal content -->
      <div class="modal-content">
        <span class="close">&times;</span>
        
        <div class="text_class">
        	        	        	
        	<h4 id="schedule_regdate"></h4>
            <div class="bin_space"></div>
        	<h2 id="schedule_title"></h2>
            
            
            
	        <div class="boxClear"></div>
	       
        </div>
        
        <div class="bin_space"></div>
        
        <div class="btn_class">
        	<button type="button" class="button" id="btnConfirm">확인</button>
			<button type="button" class="button" id="btnDelete">삭제</button>
        </div>
        
      </div>
 
   </div>

</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src='../Fullcalendar/lib/main.js'></script>
<script src="../JS/schedule.js?ver=<%= System.currentTimeMillis()%>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="../JS/bootstrap-datepicker.js"></script>
<script src="../JS/timepicki.js"></script>
<script src="../../index.js"></script>

</html>