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
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/myPet.css?ver=<%= System.currentTimeMillis()%>" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<title>마이 펫</title>
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
			
	<section id="main" class="wrapper">
		<div class="container">
			
			<form action="">
				<input id="id_wanted" type="hidden" value="${login }">
			</form>
			
			<section class="secA">
				<div class="pet_info">
					<div class="pet_img">
						<img id="p_img" class="pimg"/>
					</div>
					<div class="pet_intro">
						<div class="bin_space"></div>
						<h3 id="p_name"></h3>
						<div class="bin_space"></div>
						<p id="p_age"></p>
						<p id="p_kind"></p>
						<p id="p_weight"></p>
						
					</div>
				</div>

				<div class="today_work">
					<h2>오늘의 스케줄</h2>
					<ul id="today_input" class="actions vertical">
						
					</ul>
					
					<a id="work_list" class="button small">목록</a>
					
				</div>

			</section>
			
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
				          	<h4 class="modal-title">오늘의 스케줄</h4>
				        </div>
				        <div class="modal-body">
						  	<h5>미완료 스케줄</h5>
						  		<ul id="today_imperfect" class="actions vertical">
						
								</ul>
						  	<hr>
						  	<h5>완료 스케줄</h5>
						  		<ul id="today_perfect" class="actions vertical">
									
								</ul>
						</div>
				        <div class="modal-footer">
				          	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				        </div>
				    </div>
				</div>
			</div>
						
			<%
				
				String select = request.getParameter("pageChange");
			 	System.out.println(select);
				if (select == null) {
				    select = "weather.do";
				}
			%>

			<section class="secB">
			
				<div class="btn_box">
					
					<ul class="actions vertical small">
						<li><a id="weather_btn" href="?pageChange=weather.do" class="button special small">날씨</a></li>
						<li><a id="diet_btn" href="?pageChange=diet.do" class="button small">체중</a></li>
						<li><a id="info_btn" href="?pageChange=info.do" class="button alt small">목록</a></li>
					</ul>
				</div>
			
				<div class="mypet_main">
					<jsp:include page="<%=select%>" flush="false"/>
				</div>
			</section>
			
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
<script src="../JS/myPet.js?ver=<%= System.currentTimeMillis()%>"></script>
<script src="../../index.js"></script>
</html>