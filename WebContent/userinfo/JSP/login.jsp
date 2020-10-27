<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/login.css" />
	<title>로그인</title>
</head>
<body>

	<section id="logo_sec">
		<div class="container">
			<div id="logo-box"><a href="../../index.jsp"><img id="center-logo" src="../../images/logo.png" alt="" /></a></div>
		</div>
	</section>
	
	<section id="sign_secA">
		<div class="container">
			
			<label for="id"><b>ID</b></label>
            <input type="text" id="input_id" name="id" placeholder="Enter ID..." required>
            <p></p>
          
            <label for="psw"><b>Password</b></label>
            <input type="password" id="input_pwd" placeholder="Enter Password..." name="psw" required>
            <p></p>
            
            <div class="link_space">
            	<a class="find_link" href="#">ID/Password 찾기</a>
            </div>
            
            <button type="submit" id="btn_login">Login</button>
            
            
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
</html>