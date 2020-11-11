var chk = [true,true,true,true,true,true,true,true,true,true];


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

function onAccount() {
	if($('#roadFullAddr').val() != '') {
		chk[4] = true
	}else {
		chk[4] = false;
	}
	
	if(checkSign(false,chk)) {
		console.log(checkSign(false,chk))
		alert('모든 조건을 알맞게 입력해야 합니다.')
		return false;
	}else {
		return true;
	}
}

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



//이미지 파일 첨부
$(document).ready(function(){
	
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

function upfiles(file) {
	$("#delFiles").append("<input type='hidden' name='delFile' value='"+file +"'>");
	console.log("file: "+ file)
	
}

function deleteFiles(file){
	
	$("#up-file").val("");

	$('#uploadImg').attr('src',"../Image/noimg.gif");
	console.log("file: "+ file)
	$("#delFiles").append("<input type='hidden' name='delFile' value='"+file +"'>");
	
}

$(function() {
    $("#up-file").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#uploadImg').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}
