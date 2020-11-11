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
			<div id="logo-box">
				<a href="../../index.jsp"><img id="center-logo"
					src="../../images/Logo_black.png" alt="" /></a>
			</div>
		</div>
	</section>

	<section id="sign_secA">
		<div class="container">
			<form name="frm" action="loginOk.do" method="post">
				<label for="id"><b>ID</b></label> <input type="text" id="input_id"
					name="id" placeholder="Enter ID..." required>
				<p></p>

				<label for="pw"><b>Password</b></label> <input type="password"
					id="input_pwd" placeholder="Enter Password..." name="pw" required>
				<p></p>

				<div class="link_space button alt fit">
					<a class="find_link" href="id_search.do">ID/Password 찾기</a>
				</div>

				<button type="submit" id="btn_login" class="fit">Login</button>
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
</html>