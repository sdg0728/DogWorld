let $goods_wrap = $('.goods_wrap');
let $window = $(window);
let $hidden_btn = $('.hidden_button');
let $order_cancle = $('.order_cancle');

$goods_wrap.css('min-height', $window.height());
$hidden_btn.hide();

function del($this, goods_id, count) {
	let cc = confirm("삭제 하시겠습니까?");
    if (cc) {
        let chk = false;
        
        let url = "ordersDelete.ajax?reqType=json&purchase_id=" + $($this).attr("data-pur") + 
        "&goods_id="+goods_id + "&count=" + count;
        console.log(url)
        $.ajax({
    		url : url,
    		type : "POST",
    		cache : false,
    		success : function(data, status) {
    			if (status == "success"){
    				chk = parseJSON2(data);
    				
    				if(chk){
    					$($this).parent().remove();
    				}
    			}
    		}
    	});
        
    }
}

$(window).load(() => {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
	
	let url = "orders.ajax?reqType=json&user_id=" + $("div[class=user_id]").html();
	console.log(url);
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
	let chk = ["결제완료","상품준비","배송중","배송완료"];
	let html_data = "";
	
	for (var i = 0; i < data.length; i++) {
		html_data += "<div class='goods' data-chk='"+data[i].p_check+"'>";
		html_data += "<input type='hidden' name='goods_id' value='"+data[i].goods_id+"'>";
		html_data += "<input type='hidden' name='count' value='"+data[i].count+"'>";
		html_data += "<div class='goods-img'>";
		html_data += "<img src='" + data[i].img + "'/>";
		html_data += "</div>";
		html_data += "<div class='goods-content'>";
		html_data += "<h3><a href='goods_detail.do?goods_id="+data[i].goods_id+"'>"+data[i].name+"</a></h3>";
		html_data += "<div class='date'>구매일자: "+data[i].buydate+"</div>";
		html_data += "<span>"+(data[i].price * data[i].count)+"원 (수량 : "+data[i].count+"개)<br>배송상태 : "+chk[data[i].p_check - 1]+"</span>";
		if(data[i].p_check == 1){
			html_data += "</div><div class='button order_cancle' data-pur='"+data[i].purchase_id+"' " +
					"onclick='del(this, "+data[i].goods_id+","+data[i].count+")'>주문취소</div></div>";
		} else {
			html_data += "</div><div class='button order_cancle hidden_button' data-pur='"+data[i].purchase_id+"'>주문취소</div></div>";
		}

	}
	$goods_wrap.html(html_data);
}

function parseJSON2(jsonObj) {
	if(jsonObj.status == 'OK'){
		return true;
	} else{
		alert("삭제되지 않았습니다.");
	}
}

