$(window).load(() => {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
});

function submitChk(){
	let name = $("input:text[name=name]").val().trim();
	let price = $("input:text[name=price]").val().trim();
	let category = $("select[name=category]").val();
	let count = $("input:text[name=count]").val().trim();
	console.log(category);
	let num_form = /^[0-9]*$/;

	let chk = true;

	if(name == null || name == ""){
		alert("상품명을 입력해 주세요.");
		chk = false;
	}

	if(price == null || price == ""){
		alert("가격을 입력해 주세요.");
		chk = false;
	} else {
		if(!num_form.test(price)){
			alert("가격은 숫자만 입력해 주세요.");
			chk = false;
		}
	}

	if(category == 0){
		alert("분류를 정해주세요.");
		chk = false;
	}

	if(count == null || count == 0){
		alert("개수을 입력해 주세요.");
		chk = false;
	} else {
		if(!num_form.test(count)){
			alert("개수는 숫자만 입력해 주세요.");
			chk = false;
		}
	}
	
	return chk;
}
