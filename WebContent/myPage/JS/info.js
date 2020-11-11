$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
	var id = $('#info_id').val()
	infoEvent(id); 	
});

function infoEvent(id){
	 var url = ""
		 
	// JSON
	url = "info.ajax?reqType=json&id="+id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") {
				infoJSON(data);
			} 
			
		}
	 });
	
}

function infoJSON(jsonObj){
	var result = jsonObj.data;
	console.log(result)
	
	$('#info_title').html("<h4>"+result[0].title+"</h4>")
	$('#info_img').append('<img id="dog_img" src="'+result[0].thumbnail+'">')
	$('#info_content').html(result[0].description)
	$('#info_link').append('<a id="dog_link" href="'+result[0].link+'">더보기</a>')
	
}