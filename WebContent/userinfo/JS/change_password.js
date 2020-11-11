function check() {

	var regex= /^[a-zA-Z0-9]{4,12}$/;
	
	if($('#input_pwd').val() == '') {

	    alert("Password을 입력해 주세요.");
	
	    $('#input_pwd').focus();
	
	    return false;
	}
	else if($('#input_pwdChk').val() == '') {
	
		alert("Password Chk를 입력해 주세요.");
	
		$('#input_pwdChk').focus();
	
		return false;
	
	} else if(!regex.test($('#input_pwd').val())) {
		alert("Password 영문 또는 숫자 4~12 입니다.");
		
		return false;
		
	} else if($('#input_pwd').val() != $('#input_pwdChk').val()) {
		alert("Password와 Password Chk가 서로 다릅니다.");
		 
		return false;
	} 
	
	else return true;

}