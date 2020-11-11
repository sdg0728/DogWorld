var chk = [false,false,false,false,false,false,false,false,false,false];

function getId(){
	
	$.ajax({
    	url : 'signId.ajax?reqType=json',
    	type : 'GET',
    	cache : false,
    	success : function(data,status) {
    		
    		if(status=="success") {
    			parseJSON(data)
    		}
    		
    	}
  
    });
	
}

function parseJSON(jsonObj){	
	
	var data = jsonObj.data;
	var i;
	var result = 0
	console.log($("#input_id").val())
	
	var currentVal = $("#input_id").val()
	var regex= /^[a-z]+[a-z0-9]{4,15}$/g;
	for(i = 0; i < data.length; i++) {
		if(data[i].id == $("#input_id").val()){
			
			result = 1
	        break;
		}else {
			result = 0
		}
	}
		
	if(result==0) {
		if(currentVal == '') {
	        $('#id_check').text('id를 입력하세요.')
	        $('#id_check').css('color','#ee1100')
	        chk[0] = false
	    }
	    else if (!regex.test(currentVal)) {
	        $('#id_check').text('id는 영소문자로 시작하는 5~16자 영소문자 또는 숫자이어야 합니다.')
	        $('#id_check').css('color','#ee1100')
	        chk[0] = false
	    }
	    else {
	    	    	
	        $('#id_check').text('사용가능한 id입니다.')
	        $('#id_check').css('color','#43aa32')
	        chk[0] = true
	    }
	}else {
		$('#id_check').text('사용중인 id입니다.')
        $('#id_check').css('color','#ee1100')
        chk[0] = false
	}

}

$("#input_pwd").blur(function() {
	var currentVal = $(this).val();
    var regex= /^[a-zA-Z0-9]{4,12}$/;

    if(currentVal == '') {
        $('#pwd_check').text('Password를 입력하세요.')
        $('#pwd_check').css('color','#ee1100')
        chk[1] = false
        
        if ($("#input_pwd_chk").val() != $("#input_pwd").val()) {
            $('#pwd_chk_check').text('Password가 일치하지 않습니다.')
            $('#pwd_chk_check').css('color','#ee1100')
            
            chk[2] = false
        }
 
    }
    else if (!regex.test(currentVal)) {
        $('#pwd_check').text('Password 영문 또는 숫자 4~12 입니다.')
        $('#pwd_check').css('color','#ee1100')
        chk[1] = false
        
        if ($("#input_pwd_chk").val() != $("#input_pwd").val()) {
            $('#pwd_chk_check').text('Password가 일치하지 않습니다.')
            $('#pwd_chk_check').css('color','#ee1100')
            chk[2] = false
        }
        
    }
    else {
        $('#pwd_check').text('사용가능한 Password입니다.')
        $('#pwd_check').css('color','#43aa32')
        chk[1] = true
        
        if ($("#input_pwd_chk").val() != $("#input_pwd").val()) {
            $('#pwd_chk_check').text('Password가 일치하지 않습니다.')
            $('#pwd_chk_check').css('color','#ee1100')
            chk[2] = false
        }else {
            $('#pwd_chk_check').text('사용가능한 Password입니다.')
            $('#pwd_chk_check').css('color','#43aa32')
            chk[2] = true
        }
    }

});


$("#input_pwd_chk").blur(function() {
    var currentVal = $(this).val();
    
    if(currentVal == '') {
        $('#pwd_chk_check').text('Password를 한번 더 입력하세요.')
        $('#pwd_chk_check').css('color','#ee1100')
        chk[2] = false
    }
    else if (currentVal != $("#input_pwd").val()) {
        $('#pwd_chk_check').text('Password가 일치하지 않습니다.')
        $('#pwd_chk_check').css('color','#ee1100')
        chk[2] = false
    }
    else {
        $('#pwd_chk_check').text('사용가능한 Password입니다.')
        $('#pwd_chk_check').css('color','#43aa32')
        chk[2] = true
    }

});

$("#input_name").blur(function() {
    var currentVal = $(this).val();
    var regex = /^[가-힝a-zA-Z]{2,}$/;

    if(currentVal == '') {
        $('#name_check').text('이름을 입력하세요.')
        $('#name_check').css('color','#ee1100')
        chk[3] = false
    }
    else if (!regex.test(currentVal)) {
        $('#name_check').text('이름의 형식이 잘못 되었습니다.')
        $('#name_check').css('color','#ee1100')
        chk[3] = false
    }
    else {
        $('#name_check').text('사용가능한 이름입니다.')
        $('#name_check').css('color','#43aa32')
        chk[3] = true
    }

});

function getEmail(){
	
	$.ajax({
    	url : 'signEmail.ajax?reqType=json',
    	type : 'GET',
    	cache : false,
    	success : function(data,status) {
    		
    		if(status=="success") {
    			parseJSON2(data)
    		}
    		
    	}
  
    });
	
}

function parseJSON2(jsonObj){	
	
	var data = jsonObj.data;
	var i;
	var result = 0
	console.log($("#input_email").val())
	
	var currentVal = $("#input_email").val()
	var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	for(i = 0; i < data.length; i++) {
		if(data[i].email == $("#input_email").val()){
			
			result = 1
	        break;
		}else {
			result = 0
		}
	}
	
	if(result==0) {
		if(currentVal == '') {
	        $('#email_check').text('Email를 입력하세요.')
	        $('#email_check').css('color','#ee1100')
	        chk[5] = false
	    }
	    else if (!regex.test(currentVal)) {
	        $('#email_check').text('Email 형식이 잘못 되었습니다.')
	        $('#email_check').css('color','#ee1100')
	        chk[5] = false
	    }
	    else {
	        $('#email_check').text('사용가능한 email입니다.')
	        $('#email_check').css('color','#43aa32')
	        chk[5] = true
	    }
	}else {
		$('#email_check').text('사용중인 email입니다.')
        $('#email_check').css('color','#ee1100')
        chk[5] = false
	}

}

$("#input_phone").blur(function() {
	var currentVal = $(this).val();
    var regex = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/g;

    if(currentVal == '') {
        $('#phone_check').text('전화번호를 입력하세요.')
        $('#phone_check').css('color','#ee1100')
        chk[6] = false
    }
    else if (!regex.test(currentVal)) {
        $('#phone_check').text('전화번호는 -없이 입력하세요.')
        $('#phone_check').css('color','#ee1100')
        chk[6] = false
    }
    else {
        $('#phone_check').text('사용가능한 전화번호입니다.')
        $('#phone_check').css('color','#43aa32')
        chk[6] = true
    }

});

$("#input_petName").blur(function() {
    var currentVal = $(this).val();
    var regex = /^[가-힝a-zA-Z]{2,}$/;

    if(currentVal == '') {
        $('#petName_check').text('반려견의 이름을 입력하세요.')
        $('#petName_check').css('color','#ee1100')
        chk[7] = false
    }
    else if (!regex.test(currentVal)) {
        $('#petName_check').text('반려견 이름의 형식이 잘못 되었습니다.')
        $('#petName_check').css('color','#ee1100')
        chk[7] = false
    }
    else {
        $('#petName_check').text('사용가능한 반려견 이름입니다.')
        $('#petName_check').css('color','#43aa32')
        chk[7] = true
    }

});

function selectCate() {
	var num = document.getElementById("category");
	
	var value = num.options[document.getElementById("category").selectedIndex].value;
	
	if(value == 'write') {
		
		$('#petkind_check').text('')
		
		$("#input_petKind").attr("disabled",false);
		
		$("#input_petKind").blur(function() {
		    var currentVal = $(this).val();
		    var regex = /^[가-힝a-zA-Z]{2,}$/;

		    if(currentVal == '') {
		        $('#petkind_check').text('반려견의 종을 입력하세요.')
		        $('#petkind_check').css('color','#ee1100')
		        chk[8] = false
		    }
		    else if (!regex.test(currentVal)) {
		        $('#petkind_check').text('직접 입력 시 한글 2자 이상입니다.')
		        $('#petkind_check').css('color','#ee1100')
		        chk[8] = false
		    }
		    else {
		        $('#petkind_check').text(currentVal)
		        $('#petkind_check').css('color','#43aa32')
		        chk[8] = true
		    }

		});
		
		
	}else {
		$("#input_petKind").val('')
		$("#input_petKind").attr("disabled",true);
		
		$('#petkind_check').text(value)
		$('#petkind_check').css('color','#43aa32')
		chk[8] = true
	}
	
	
}


$("#input_petAge").blur(function() {
    var currentVal = $(this).val();
    var regex = /^[0-9]*$/;

    if(currentVal == '') {
        $('#petAge_check').text('반려견의 나이을 입력하세요.')
        $('#petAge_check').css('color','#ee1100')
        chk[9] = false
    }
    else if (!regex.test(currentVal)) {
        $('#petAge_check').text('반려견 나이는 숫자만 가능합니다.')
        $('#petAge_check').css('color','#ee1100')
        chk[9] = false
    }
    else {
        $('#petAge_check').text('반려견 나이 입력완료.')
        $('#petAge_check').css('color','#43aa32')
        chk[9] = true
    }

});

function checkSign(check,chk) {
	for(idx in chk) {
		if(chk[idx] == check)
			return true;
	}
	return false;
}


function onSign(){
	
	if($('#roadFullAddr').val() != '') {
		chk[4] = true
	}
	
	if(checkSign(false,chk)) {
		console.log(checkSign(false,chk))
		alert("회원가입 조건을 모두 알맞게 입력해야 합니다.")
		return false;
	}else {
		return true;
	}
	        
}


// 이미지 파일 첨부
$(document).ready(function(){
	
	$("#id_load").click(function(){
		 getId();
	 });
	
	$("#email_load").click(function(){
		 getEmail();
	 });
	
	if($("select option[value='write']")) {
		$("#input_petKind").attr("disabled",false);
		
		$("#input_petKind").blur(function() {
		    var currentVal = $(this).val();
		    var regex = /^[가-힝a-zA-Z]{2,}$/;

		    if(currentVal == '') {
		        $('#petkind_check').text('반려견의 종을 입력하세요.')
		        $('#petkind_check').css('color','#ee1100')
		        chk[8] = false
		    }
		    else if (!regex.test(currentVal)) {
		        $('#petkind_check').text('직접 입력 시 한글 2자 이상입니다.')
		        $('#petkind_check').css('color','#ee1100')
		        chk[8] = false
		    }
		    else {
		        $('#petkind_check').text(currentVal)
		        $('#petkind_check').css('color','#43aa32')
		        chk[8] = true
		    }

		});
	}
	
});

$(function() {
    $("#up-file").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    
    $('#img_box').append("<img id='uploadImg' />")

    reader.onload = function (e) {
            $('#uploadImg').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}

function deleteFiles(){
	
	$("#up-file").val("");
	$('#img_box').empty();
	
}