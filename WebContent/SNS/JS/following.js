var id = $("#myfeed").attr("data-id");

$(window).load(function() {
	$('#comment-text').keypress(function(f) {
		if(f.keycode == 13){
			$(".comment").text($(f.target).val());
		}
	});
});

function enterSubmit(){
	if(event.keyCode == 13){
		
	}
}