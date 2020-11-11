var id = $("#myfeed").attr("data-id");
var arr= [];

$(window).load(function() {
	
	//좋아요 상태 가져오기 
	var urls = "PostlikeList.ajax?reqType=json&user_id=" + id;
	$.ajax({
		url :  urls,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") 
				parseJSON3(data);
		}
	});
	
	$('#comment-text').keypress(function(f) {
		if(f.keycode == 13){
			$(".comment").text($(f.target).val());
		}
	});
});


function parseJSON3(jsonObj){
	var data = jsonObj.data;

	console.log(data);
	
	for(var i=0; i<data.length; i++) {
		arr[i] = data[i].post_id
		
	}
	for(var i=0; i < document.getElementsByClassName('fa-heart').length; i++) {
		
		for(var j=0; j < arr.length; j++) {
			
			if($('#postId'+i).val() == arr[j]) {
				var color = $('#likes_cnt'+i).css('color');
				console.log(color)
				$('#box'+i).css('color', 'rgb(255, 0, 0)')
				
			}
		} 
	}
	
}

function enterSubmit(){
	if(event.keyCode == 13){
		
	}
}

function deleteCom(id){
	var chk = confirm("댓글을 삭제하시겠습니까?")
	if(chk){
		location.href="commentdeleteOk.do?comment_id=" + id;
	}

}

$("button[class=friends_btn]").click(function(){
	
	var url = "follower.ajax?reqType=json&follower=" + id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") 
				parseJSON(data);
		}
	 });
});

function parseJSON(jsonObj){
	var data = jsonObj.data;
	console.log(data)
	var table="";
	for (var i = 0; i < data.length; i++) { 
		table += "<tr>";
		table += "<td>" + data[i].following + "</td></tr>";
	} // end for
	$(".follower-table").html(table);

} // end parseJSON()

$("button[class=following_btn]").click(function(){
	
	var url = "following.ajax?reqType=json&following=" + id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") 
				parseJSON2(data);
		}
	 });
});

function parseJSON2(jsonObj){
	var data = jsonObj.data;
	console.log(data);
	var table="";
	for (var i = 0; i < data.length; i++) { 
		table += "<tr>";
		table += "<td>" + data[i].follower + "</td></tr>";
	} // end for
	$(".following-table").html(table);

} 


for(var i=0; i < document.getElementsByClassName('fa-heart').length; i++) {
	
		
	$('#box'+i).click((e) => {
		
		var post_id = $('#postId'+e.target.id.substring(3,e.target.id.length)).val()
		
		var color = $(e.target).css('color');
		if(color == 'rgb(128, 128, 128)') {
			
			var url = "like.ajax?reqType=json&user_id=" + id+"&post_id="+post_id;
			$.ajax({
				url :  url,
				type : "POST",
				cache : false,
				success : function(data, status){
					if(status == "success") 
						$(e.target).css('color', 'rgb(255, 0, 0)');
				}
			 });
			
		}else if(color == 'rgb(255, 0, 0)') {
			
			var url = "unlike.ajax?reqType=json&user_id=" + id+"&post_id="+post_id;
			$.ajax({
				url :  url,
				type : "POST",
				cache : false,
				success : function(data, status){
					if(status == "success") 
						$(e.target).css('color', 'rgb(128, 128, 128)');
				}
			 });
			
		}
	});
	
}













