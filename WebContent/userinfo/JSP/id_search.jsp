<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/id_search.css" />
	<title>아이디 찾기</title>
</head>
<body>

	<section id="logo_sec">
		<div class="container">
			<div id="logo-box"><a href="../../index.jsp"><img id="center-logo" src="../../images/logo.png" alt="" /></a></div>
		</div>
	</section>
	
	<section id="sign_secA">
		<div class="container">
			
			<label for="email"><b>Email</b></label>
            <input type="email" id="input_email" name="email" placeholder="Enter email..." required>
            <p></p>
            
            <div class="row">
            	<div>
					<ul class="actions fit small">
						<li><a href="id_search.jsp" class="button alt fit">아이디 찾기</a></li>
						<li><a href="pw_search.jsp" class="button alt fit">비밀번호 찾기</a></li>
					</ul>
				</div>
            </div>
            
            <button type="submit" id="btn_id_search">찾기</button>
            
            
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