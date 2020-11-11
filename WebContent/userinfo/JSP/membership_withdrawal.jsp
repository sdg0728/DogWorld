<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/ch_and_mw.css?ver=<%= System.currentTimeMillis()%>" />
	<title>회원탈퇴</title>
</head>
<body>

	<section id="logo_sec">
		<div class="container">
			<div id="logo-box"><a href="../../index.do"><img id="center-logo" src="../../images/Logo_black.png" alt="" /></a></div>
		</div>
	</section>
	
	<section id="sign_secA">
		<div class="container">
			
			<form method="post" action="user_deleteOk.do" name="delete" onsubmit="return check()">
			
			<input type="hidden" id="hidden_id" name="id" value="${login }">
			
            <label for="psw"><b>Password</b></label>
            <input type="password" id="input_pwd" placeholder="Enter Password..." name="psw" required>
            <p></p>
                          
            <button type="submit" id="btn_delete">회원 탈퇴</button>
            
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
<script src="../JS/membership_withdrawal.js?ver=<%= System.currentTimeMillis()%>" type="text/javascript"></script>


</html>