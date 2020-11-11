let $view_detail = $('.view_detail');

$(window).load(() => {
	if($("input:hidden[name=session_chk]").val() == ""){
		location.href = "../../index.do";
	}
});

$view_detail.click(() => {
    let $detail = $('.details');
    if($detail.height() > 0){
        $('.details').height(0);
    } else {
        $('.details').css('height', $('.detail_img').height());
    }
});