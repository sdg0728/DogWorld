let id = $("#cart").attr("cart-id");

let url = "basket.ajax?reqType=json&user_id="+id;
let $list_background = $(".list_background");
let $delete_button = $(".delete_button");

$(window).load(function() {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
	
	$list_background.css('min-height', $(window).height());
	
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		success : function(data, status) {
			if (status == "success")
				parseJSON(data);
		}
	});
});

function parseJSON(jsonObj) {
	let data = jsonObj.data;
	let cart = jsonObj.cart;
	
	let $goods_wrap = $(".goods_wrap");
	let $total_price = $(".total-price");
	let html_data = "";
	let total = 0;

	for (var i = 0; i < data.length; i++) {
		html_data += "<div class='goods'>"
		html_data += "<div class='goods-img'>"
		html_data += "<img src='" + data[i].img + "'/>"
		html_data += "</div>"
		html_data += "<div class='goods-content'>"
		html_data += "<h3><a href='goods_detail.do?goods_id="
				+ data[i].goods_id + "'>" + data[i].name + "</a><div class='date'>"+cart[i].buydate+"</div></h3>"
		html_data += "<p>" + data[i].price + "원</p>"
		html_data += "<p>수량: " + cart[i].count + "개</p>"
		html_data += "</div>"
		html_data += "<div class='button alt delete_button' onclick='deleteBtn("+cart[i].purchase_id+")'>X</div>"
		html_data += "</div>"

		total += data[i].price * cart[i].count;
	}
	$goods_wrap.html(html_data);
	$total_price.text(total + "원")
}

function deleteBtn(purchase_id) {
	location.href="../JSP/cartDeleteOk.do?purchase_id="+purchase_id;
}