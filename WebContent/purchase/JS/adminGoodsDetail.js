$(window).load(() => {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
});

function replaceDetail($this){
	let $cart_frm = $($this).parents(".goods-detail").children(".goods");
	let $goods_content = $cart_frm.children(".goods-content");
	let $img = $cart_frm.parents(".goods-detail").children(".img_inputs").children(".img");
	let $img_detail = $cart_frm.parents(".goods-detail").children(".img_inputs").children(".img_detail");
	let $g_name = $goods_content.children("div").children("h3[class=g_name]");
	let $g_price = $goods_content.children("div").children("span[class=g_price]");
	let $g_count = $goods_content.children("div").children("span[class=g_count]");
	let $btm = $cart_frm.children(".buttons").children(".btn-wrap");
	let $replace = $btm.children("button:button[name=replace]");
	let $register = $btm.children("button:submit[name=register]");
	
	let name = $g_name.text().trim();
	let price = $g_price.text().trim();
	let count = $g_count.text().trim();
	
	$img.attr("class", "img");
	$img_detail.attr("class", "img_detail");
	$replace.attr("class","button pur hidden");
	$register.attr("class","button pur");
	$g_name.replaceWith("<input type='text' name='g_name' class='g_name' value='"+name+"' />");
	$g_price.replaceWith("<input type='text' name='g_price' class='g_price' style='width:50%;' value='"+price+"' />");
	$g_count.replaceWith("<input type='text' name='g_count' class='g_count' style='width:50%;' value='"+count+"' />");
}

function updateGoods(){
	let name = $("input:text[name=g_name]").val();
	let price = $("input:text[name=g_price]").val();
	let count = $("input:text[name=g_count]").val();
	let goods_id = $("input:hidden[name=goods_id]").val();
	
	let img = $("input:file[name=img]")[0].files[0];
	let img_detail = $("input:file[name=img_detail]")[0].files[0];
	
	if(img == undefined){
		img = $(".ori-img").attr("src");
	} else {
		img = "../../upload/purchase/" + $("input:file[name=img]")[0].files[0].name;
	}
	
	if(img_detail == undefined){
		img_detail = $(".ori-de-img").attr("src");
	} else {
		img_detail = "../../upload/purchase/" + $("input:file[name=img]")[0].files[0].name;
	}
	
	let url = "updateGoods.ajax?reqType=json&name=" + name + "&price=" + price + "&count=" + count + "&img=" + img
			+ "&img_detail=" + img_detail + "&goods_id=" + goods_id;
	
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		success : function(data, status) {
			if (status == "success")
				parseJSON(data);
		}
	});
}

function parseJSON(jsonObj) {
	if(jsonObj.status == 'OK'){
		alert("수정 완료");
		location.href = "admin.do";
	}
	if(jsonObj.status == 'FAIL'){
		alert("수정 실패했습니다.");
	}
}
