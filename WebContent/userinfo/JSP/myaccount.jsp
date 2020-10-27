<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/myaccount.css" />
	<title>계정관리</title>
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
            <input type="text" id="input_id" name="id" placeholder="아이디는 못바꾸지" disabled>
            <p></p>
            
            <label for="psw"><b>Password</b></label>
            <a href="#" class="button fit">Password 변경</a>
            <p></p>

            <label for="name"><b>Name</b></label>
            <input type="text" id="input_name" placeholder="Enter Name..." name="name" required>
            <p id="name_check">이름을 입력하세요.</p>
            
            <label for="email"><b>Email</b></label>
            <input type="email" id="input_email" name="email" placeholder="Enter email..." disabled>
            <p></p>
            
            <label for="mPhone"><b>Tel</b></label>
            <input type="text" id="input_phone" name="phone" placeholder="Enter phone number..." required>
            <p id="mPhone_check">전화번호를 입력하세요.</p>
            
            <label for="petName"><b>Pet Name</b></label>
            <input type="text" id="input_petName" placeholder="Enter PetName..." name="petName" required>
            <p id="petName_check">펫 이름을 입력하세요.</p>
            
            <label for="petKand"><b>Dog breed</b></label>
            <div class="select-wrapper sign_space">
				<select name="PetKind" id="category">
					<option value="">- 직접입력 -</option>
					<option value="1">진돗개</option>
					<option value="1">삽살개</option>
					<option value="1">불독</option>
					<option value="1">푸들</option>
				</select>
			</div>
			<input type="text" id="input_petKind" placeholder="Enter PetKind..." name="petKind" disabled>
			<p id="petName_check">펫 종류 선택</p>
			
			<label for="petAge"><b>Pet Age</b></label>
            <input type="text" id="input_petAge" placeholder="Enter PetAge..." name="petAge" required>
            <p id="petAge_check">펫 이름을 입력하세요.</p>
            
            <label for="petImage"><b>Pet Image</b></label>
            <div class="filebox preview-image"> 
            	<input class="upload-name" value="파일선택" disabled="disabled" > 
            	<label for="input-file">사진</label> 
            	<input type="file" id="input-file" class="upload-hidden"> 
            </div>
            <p></p>

            <button type="submit" id="btn_sign">Sign</button>
            <div class="link_space">
            	<a class="go_dropOut" href="#">회원탈퇴</a>
            </div>
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
<script src="../JS/myaccount.js" type="text/javascript"></script>

</html>