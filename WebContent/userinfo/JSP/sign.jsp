<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="../../assets/css/main.css" />
	<link rel="stylesheet" href="../CSS/sign.css?ver=<%= System.currentTimeMillis()%>" />
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
	<title>회원가입</title>
</head>

<script language="javascript">
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
	//document.domain = "abc.go.kr";

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}

	function jusoCallBack(roadFullAddr, zipNo, jibunAddr) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$("input:text[name=roadFullAddr]").val(roadFullAddr);
		$("input:hidden[name=jibunAddr]").val(jibunAddr);
	}
</script>

<body>
	<section id="logo_sec">
		<div class="container">
			<div id="logo-box"><a href="../../index.do"><img id="center-logo" src="../../images/Logo_black.png" alt="" /></a></div>
		</div>
	</section>
	
	<section id="sign_secA">

        <div class="container">
        
        	<form method="post" action="signOk.do" name="userInfo" enctype="multipart/form-data" onsubmit="return onSign()">
             
            <label for="id"><b>ID</b></label>
            <div class="id_box">
            <input type="text" id="input_id" name="id" placeholder="Enter id..." required>
            <button type="button" id="id_load">아이디 중복체크</button>
            </div>
            <p id="id_check"></p>
            
    
            <label for="psw"><b>Password</b></label>
            <input type="password" id="input_pwd" placeholder="Enter Password..." name="psw" required>
            <p id="pwd_check"></p>

            <label for="psw_chk"><b>Password Check</b></label>
            <input type="password" id="input_pwd_chk" placeholder="Enter Password..." name="psw_chk" required>
            <p id="pwd_chk_check"></p>

            <label for="name"><b>Name</b></label>
            <input type="text" id="input_name" placeholder="Enter Name..." name="name" required>
            <p id="name_check"></p>
            
            <label for="roadFullAddr"><b>Address</b></label>
            <div class="address">
            	<input type="hidden" id="jibunAddr" name="jibunAddr" value="">
				<input type="text" id="roadFullAddr" name="roadFullAddr" value="" placeholder="address" readonly /> 
				<input id="addr_btn" type="button" onClick="goPopup();" value="검색하기" class="button"></input>
			</div>
            <p id="address_check"></p>
            
            <label for="email"><b>Email</b></label>
            <div class="email_box">
            <input type="email" id="input_email" name="email" placeholder="Enter email..." required>
            <button type="button" id="email_load">이메일 중복체크</button>
            </div>
            <p id="email_check"></p>
            
            <label for="phone"><b>Tel</b></label>
            <input type="text" id="input_phone" name="phone" placeholder="Enter phone number..." required>
            <p id="phone_check"></p>
            
            <label for="petName"><b>Pet Name</b></label>
            <input type="text" id="input_petName" placeholder="Enter PetName..." name="petName" required>
            <p id="petName_check"></p>
            
            <label for="petKand"><b>Dog breed</b></label>
            <div class="select-wrapper sign_space">
				<select name="petKind" id="category" onchange="selectCate()">
					<option value="write" selected="selected">- 직접입력 -</option>
					<option value="진돗개">진돗개</option>
					<option value="불독">불독</option>
					<option value="푸들">푸들</option>
					<option value="레브라도리트리버">레브라도 리트리버</option>
					<option value="골든레트리버">골든 레트리버</option>
					<option value="요크서테리어">요크서 테리어</option>
					<option value="비글">비글</option>
					<option value="프렌치불도그">프렌치 불도그</option>
					<option value="로트와일러">로트와일러</option>
					<option value="비숑">비숑</option>
					<option value="시바견">시바견</option>
					<option value="포메라니안">포메라니안</option>
					<option value="셰퍼드">셰퍼드</option>
					<option value="치와와">치와와</option>
					<option value="시베리안허스키">시베리안허스키</option>
					<option value="시츄">시츄</option>
					<option value="말티즈">말티즈</option>
					<option value="닥스훈트">닥스훈트</option>
					<option value="보더콜리">보더콜리</option>
					<option value="웰시코기">웰시코기</option>
							
				</select>
			</div>
			
			<input type="text" id="input_petKind" placeholder="Enter PetKind..." name="petKind">
			<p id="petkind_check"></p>
			
			<label for="petAge"><b>Pet Age</b></label>
            <input type="text" id="input_petAge" placeholder="Enter PetAge..." name="petAge" required>
            <p id="petAge_check"></p>
            
            <label><b>사진</b></label>
            
				<div id="img_box">
					
				</div>
					
             
            	<label class="btn btn-primary btn-file">
        			사진 <input id="up-file" type="file" name="upfile" style="display: none;" accept=".gif, .jpg, .png, .jpeg">
    			</label>

            	<label class="btn btn-primary btn-file">  
            		삭제<input type="button" id="delete-file" name="deletefile" onclick="deleteFiles();">
            	</label>
            
            <%--
            <label for="petImage"><b>Pet Image</b></label>
            <div class="filebox preview-image"> 
            	<input class="upload-name" value="파일선택" disabled="disabled" > 
            	<label for="input-file">사진</label> 
            	<input type="file" id="input-file" class="upload-hidden" name="upfile"> 
            </div>
            
             --%>
            <p></p>

            <button type="submit" id="btn_sign">Sign</button>
            
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
<script src="../JS/sign.js?ver=<%= System.currentTimeMillis()%>" type="text/javascript"></script>

</html>