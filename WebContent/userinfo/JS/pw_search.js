function check() {
	
	if($('#input_id').val() == '') {

	    alert("ID를 입력해 주세요.");
	
	    $('#input_id').focus();
	
	    return false;
	} else if($('#input_email').val() == '') {

	    alert("Email을 입력해 주세요.");
	
	    $('#input_email').focus();
	
	    return false;
	} else if($('#input_phone').val() == '') {

	    alert("전화번호를 입력해 주세요.");
	
	    $('#input_phone').focus();
	
	    return false;
	}
	
	else return true;

}