let id = $("#purchase").attr("data-id");

$(window).load(function() {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
	
	let url = "basket.ajax?reqType=json&user_id="+id;
	
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		success : function(data, status) {
			if (status == "success")
				parseJSON1(data);
		}
	});
});

function parseJSON1(jsonObj) {
	let data = jsonObj.data;
	let cart = jsonObj.cart;
	
	let $goods_wrap = $(".goods_wrap");
	let $total_price = $(".total-price");
	let html_data = "";
	let total = 0;
	
	if(data.length == 0){
		alert("장바구니가 비어있습니다.");
		history.back();
		return;
	}
	
	for (var i = 0; i < data.length; i++) {
		html_data += "<div class='goods'>";
		html_data += "<input type='hidden' name='goods_id' value='"+data[i].goods_id+"'/>";
		html_data += "<input type='hidden' name='count' value='"+cart[i].count+"'/>";
		html_data += "<input name='purcase_id"+i+"' type='hidden' value='"+cart[i].purchase_id+"' />";
		html_data += "<div class='goods-img'>";
		html_data += "<img src='" + data[i].img + "'/>";
		html_data += "</div>";
		html_data += "<div class='goods-content'>";
		html_data += "<h3>" + data[i].name + "</h3>";
		html_data += "<div class='date'>구매일자: "+cart[i].buydate+"</div>";
		html_data += "<p>" + data[i].price + "원</p>";
		html_data += "<p>수량: " + cart[i].count + "개</p>";
		html_data += "</div></div>";

		total += data[i].price * cart[i].count;
	}
	$goods_wrap.html(html_data);
	$total_price.text(total + "원")
}

$("input:radio[id=priority-low]").click(() => {
	let url = "card.ajax?reqType=json&user_id="+id;

	
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		success : function(data, status) {
			if (status == "success")
				parseJSON2(data);
		}
	});
});

function parseJSON2(jsonObj) {
	let data = jsonObj.data;
	
	$("select[name=category]").val(data[0].bank).prop("selected",true);
	let numArr = data[0].card_num.split("-");
	
	$("input:text[name=num1]").val(numArr[0]);
	$("input:text[name=num2]").val(numArr[1]);
	$("input:text[name=num3]").val(numArr[2]);
	$("input:text[name=num4]").val(numArr[3]);
	
	$("input:text[name=year]").val(data[0].card_lim_year);
	$("input:text[name=month]").val(data[0].card_lim_month);
	$("input:password[name=cvc]").val(data[0].cvc);
	
	$("input:password[name=card_passowrd]").val(data[0].password);
		
	
	$(".card-button").hide();
}


$("input:radio[id=priority-normal]").click(() => {
	$("select[name=category]").val(0).prop("selected",true);
	
	$("input:text[name=num1]").val("");
	$("input:text[name=num2]").val("");
	$("input:text[name=num3]").val("");
	$("input:text[name=num4]").val("");
	
	$("input:text[name=year]").val("");
	$("input:text[name=month]").val("");
	$("input:text[name=cvc]").val("");
	
	$("input:password[name=card_passowrd]").val("");
		
	
	$(".card-button").show();
});

$(".card-button").click(() => {
	let card_num = $("input:text[name=num1]").val() + "-"
				+ $("input:text[name=num2]").val() + "-"
				+ $("input:text[name=num3]").val() + "-"
				+$("input:text[name=num4]").val();
	let bank = $("select[name=category]").val();
	let year = $("input:text[name=year]").val();
	let month = $("input:text[name=month]").val();
	let cvc = $("input:password[name=cvc]").val();
	let password = $("input:password[name=card_passowrd]").val();	
	
	let card_form = /^[0-9]{4}$/;
	let year_form = /^[0-9]{2}$/;
	let month_form = /^[0-9]{1,2}$/;
	let cvc_form = /^[0-9]{3}$/;
	
	if(card_num == null || card_num == "" || bank == 0 || year == null || year == "" 
		|| month == null || month == "" || cvc == null || cvc == "" || password == null || password == ""){
		alert("빈칸없이 입력해 주세요.");
		return;
	} else{
		if(!card_form.test($("input:text[name=num1]").val()) || !card_form.test($("input:text[name=num2]").val())
				|| !card_form.test($("input:text[name=num3]").val()) || !card_form.test($("input:text[name=num4]").val())){
			alert("카드번호는 숫자 4자리로 입력해 주세요.");
			return;
		} else if(!year_form.test(year)){
			alert("년도를 다시 입력해 주세요.");
			return;
		} else if(!month_form.test(month)){
			alert("월을 다시 입력해 주세요.");
			return;
		} else if(!cvc_form.test(cvc)){
			alert("cvc를 다시 입력해 주세요.");
			return;
		} else if(!card_form.test(password)){
			alert("비밀번호를 다시 입력해 주세요.");
			return;
		}
	}
	
	let url = "cardReg.ajax?reqType=json&user_id="+id + "&card_num="+card_num
		+"&year=" + year + "&month=" + month + "&cvc=" + cvc + "&password=" + password + "&bank=" + bank;
	console.log(url);
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		success : function(data, status) {
			if (status == "success")
				parseJSON3(data);
		}
	});
});

function parseJSON3(jsonObj) {
	let status = jsonObj.status;
	let chk = jsonObj.data[0];
	
	if(status == 'OK'){
		if(chk == 1){
			alert("카드를 등록했습니다.");
		} else {
			alert("카드정보를 수정했습니다.");
		}
		
	} else{
		alert("카드를 등록하지 못했습니다.");
	}
}

function pur_submit(){
	let name = $("input:text[name=name]").val();
	let phone = $("input:text[name=phone]").val();
	let zipNo = $("input:text[name=zipNo]").val();
	let roadFullAddr = $("input:text[name=roadFullAddr]").val();
	let card_num = $("input:text[name=num1]").val() + "-"
				 + $("input:text[name=num2]").val() + "-"
				 + $("input:text[name=num3]").val() + "-"
				 + $("input:text[name=num4]").val();
	let bank = $("select[name=category]").val();
	let year = $("input:text[name=year]").val();
	let month = $("input:text[name=month]").val();
	let cvc = $("input:password[name=cvc]").val();
	let password = $("input:password[name=card_passowrd]").val();
	
	let name_form = /^[가-힝a-zA-Z]{2,}$/;
	let phone_form = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/g;
	let card_num_form = /^[0-9]{4}-?[0-9]{4}-?[0-9]{4}-?[0-9]{4}$/g;
	let year_form = /^[0-9]{2}$/;
	let month_form = /^[0-9]{2}$/;
	let cvc_form = /^[0-9]{3}$/;
	let password_form = /^[0-9]{4}$/;
	console.log(name + " " + phone + " " + card_num + " " + year + " " + month + " " + cvc)
	
	if(name == null || name == "" || name == null || name == "" || phone == null || phone == "" ||
			zipNo == null || zipNo == "" || roadFullAddr == null || roadFullAddr == "" ||
			card_num == null || card_num == "" ||bank == null || bank == "0" ||
			year == null || year == "" ||month == null || month == "" ||
			cvc == null || cvc == "" ||password == null || password == "" ){
		alert("빈칸 없이 입력해 주세요.");
	} else {
		var chk = true;
		if(!name_form.test(name)){
			alert("이름은 한글만 가능합니다.");
			$("input:text[name=name]").focus();
			chk = false;
		} else if(!phone_form.test(phone)){
			alert("전화번호를 확인해 주세요.");
			$("input:text[name=phone]").focus();
			chk = false;
		} else if(!card_num_form.test(card_num)){
			alert("카드번호를 확인해 주세요.");
			$("input:text[name=num1]").focus();
			chk = false;
		} else if(!year_form.test(year)){
			alert("년도를 확인해 주세요.");
			$("input:text[name=year]").focus();
			chk = false;
		} else if(!month_form.test(month)){
			alert("월을 확인해 주세요.");
			$("input:text[name=month]").focus();
			chk = false;
		} else if(!cvc_form.test(cvc)){
			alert("cvc를 확인해 주세요.");
			$("input:password[name=cvc]").focus();
			chk = false;
		} else if(!password_form.test(password)){
			alert("카드비밀번호를 확인해 주세요.");
			$("input:password[name=cvc]").focus();
			chk = false;
		}
		if(chk){
			$('form[name=user_info]').submit();
		}
	}
}