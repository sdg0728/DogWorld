

$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
	
	$('#myModal').modal("hide");
	
});

window.onload = function () {
	var id = $('#id_wanted').val()
	console.log(id);
	getEvent(id); 
	petDietEvent(id);
	getEvent2(id);
}


function getEvent(id){
	 var url = "";
	 
	 console.log(id);
	 
	// JSON
	url = "petInfo.ajax?reqType=json&id="+id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") {
				parseJSON(data);
			} 
			
		}
	 });
	
}

function getEvent2(id){
	 var url = ""
		 	 
	// JSON
	url = "today.ajax?reqType=json&id=" + id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") 
				parseJSON2(data);
		}
	 });
	
}

function parseJSON(jsonObj){
	
	var result = jsonObj.data
		
	$('#p_name').text(result[0].petName)
	$('#p_age').text("나이: "+result[0].petAge)
	$('#p_kind').text("종: "+result[0].dogBreed)
	
	if(typeof result[0].img == "undefined" || result[0]  == null || result[0] == "") {
		document.getElementById('p_img').src = "../Image/noimg.gif";
	}else {
		document.getElementById('p_img').src = "../../upload/user/"+result[0].img;
	}
		
}

function parseJSON2(jsonObj){
	
	var result = jsonObj.data
		
	for (var i = 0; i < result.length; i++) {
		if(result[i].s_check == 0)
			$('#today_input').append("<li><a id='+"+result[i].no+"' href='today_updateOk.do?no="+result[i].no+"&s_check=1' class='button alt fit'>"+result[i].content+"</a></li>")
	}
	
	$('#today_input').children('li').children('a').click(function() {
		$(this).parent('li').remove()
	})
	
	for (var i = 0; i < result.length; i++) {
		if(result[i].s_check == 0)
			$('#today_imperfect').append("<li><a href='today_updateOk.do?no="+result[i].no+"&s_check=1' class='button alt fit'>"+result[i].content+"</a></li>")
		else
			$('#today_perfect').append("<li><a href='today_updateOk.do?no="+result[i].no+"&s_check=0' class='button alt fit'>"+result[i].content+"</a></li>")
	}
	
	$('#today_imperfect').children('li').children('a').click(function() {
		$(this).parent('li').remove()
	})
	
	$('#today_imperfect').children('li').children('a').click(function() {
		$(this).parent('li').remove()
	})
	
}


function petDietEvent(id){
	 var url = ""
		 	 
	// JSON
	url = "diet.ajax?reqType=json&id="+id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") {
				petDietJSON(data);
			} 
			
		}
	 });
	
}

function petDietJSON(jsonObj){
	
	result = jsonObj.data
	
	var url = ""
	 	 
	// JSON
	url = "getWeight.ajax?reqType=json&petId="+result[0].petId;
	
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") {
				petWeightJSON(data);
			} 
			
		}
	 });
		
}

function petWeightJSON(jsonObj){
	dietInfo = jsonObj.data
	
	if(dietInfo.length !=0)
		$('#p_weight').text("체중: "+dietInfo[dietInfo.length-1].weight+"kg")
	else
		$('#p_weight').text("입력된 체중이 없음")
}

$('#work_list').click(function() {
	$('#myModal').modal("show");
})

