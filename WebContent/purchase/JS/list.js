let $window = $(window);
let $list_background = $('.list_background');
let $goods = $('.goods').height() + 10;
let length = $(".goods").length;
let $wrap_div = $('.wrap_div');
let $sub_menu = $('#sub_menu');
let $goods_list = $('.goods_list');
let nav_link = document.getElementsByClassName('nav-link');

$window.on("load", () => {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
	
    $sub_menu.hide();

    $('body').css("padding-top", $('#header').height());

    let h = 0;

    if (length % 3 != 0) {
        length = length / 3 + 1;
        h = length * $goods + $('.navbar').height() + 10;
    } else {
        length /= 3;
        h = length * $goods + $('.navbar').height() + 102.5;
    }
    

    $list_background.css('min-height', h);

    $wrap_div.height($list_background.height());
    
    for (var i = 0; i < nav_link.length; i++) {
    	console.log($(nav_link[i]).attr('cate'));
    	if($('.goods').attr('cate') == $(nav_link[i]).attr('cate')){
    		$(nav_link[i]).addClass('active');
    	}
	}
    
});