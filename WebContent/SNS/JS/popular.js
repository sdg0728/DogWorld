var id = $("#myfeed").attr("data-id");

var arr = []

$(window).load(function() {
	
	//follower 정보 가져 오는 ajax 
	var url = "followingList.ajax?reqType=json&follower=" + id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") 
				parseJSON3(data);
		}
	});
	
	$('#search-text').keypress(function(f) {
		
		console.log(f)
		
		if(f.charCode == 13){
			var url = "userId.ajax?reqType=json&name=" + $('#search-text').val();
			$.ajax({
				url :  url,
				type : "POST",
				cache : false,
				success : function(data, status){
					if(status == "success") 
						parseJSON(data);
				}
			});
		}
	});
});

function enterSubmit(){
	if(event.keyCode == 13){
		
	}
}

function parseJSON3(jsonObj){
	var data = jsonObj.data;
	console.log(data)
	
	for(var i=0; i<data.length; i++) {
		arr[i] = data[i].following
	}
	
}

function parseJSON(jsonObj){
	var data = jsonObj.data;
	console.log(data)
	var table="";
	//var insert_add = 'location.href=followingAddOk.do?following=${user_id }&follower="+data[i].id+"';
	if(data == '' || data == undefined || data == null) {
		alert("해당 정보가 없습니다")
	}else {
	
		for (var i = 0; i < data.length; i++) { 
			var chk = false;
			table += "<tr>";
			table += "<td>" +"<a class='result_id'>"+ data[i].id +"</a>"+ "</td>";
			for(var j=0; j< arr.length; j++) {
				if(data[i].id == arr[j])
					chk = true;
			}
			if(!chk)
				table += "<td>" + "<input onclick='go(\""+data[i].id+"\", this)' id='following_add"+i+"' type='button' value='팔로잉' />" + "</td></tr>";
			else {
				console.log(">>>>>>>>>>>>>>>>")
				table += "<td>" + "<input onclick='go(\""+data[i].id+"\", this)' id='following_add"+i+"' type='button' value='취소' />" + "</td></tr>";
				
			}

		} // end for
		
		$(".searchId_table").html(table);
		
		
		
//		for (var i = 0; i < data.length; i++) {
//			console.log(i);
//			$("#following_add"+i).click(function(){
//				console.log(i+"????" + $('#search_id'+i))
//				
//				//location.href="followingAddOk.do?following='${user_id}'&follower="+$('#following_add'+i).parent().prev().children('a').text()
//			});
//		}
		
		
		
		
	}
} // end parseJSON()

function go(follower, e){
	
	if($(e).val() == '팔로잉') {
		var user_id = $("input:hidden[name=user_id]").val();
		location.href = "followingAddOk.do?following="+user_id+"&follower="+follower;
	}else if($(e).val() == '취소') {
		var user_id = $("input:hidden[name=user_id]").val();
		location.href = "followingdeleteOk.do?following="+user_id+"&follower="+follower;
	}
}

$('#search-text').keypress(function(f) {
	if(f.keycode == 13){
		var url = "petKind.ajax?reqType=json&name=" + $('#search-text').val();
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
});


function parseJSON2(jsonObj){
	var data = jsonObj.data;
	console.log(data);
	var table="";
	for (var i = 0; i < data.length; i++) { 
		table += "<tr>";
		table += "<td>" + data[i].id + "</td></tr>";
	} // end for
	$(".searchId_table").html(table);
}






//$("#following_add").css({background:"#fffd8", color:"#065471"});
//$("#following_add("+$(this).index()+")button").css({backgorund:"#065471", color:"#ffc045"});

