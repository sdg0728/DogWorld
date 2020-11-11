
$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
	var id = $('#weather_id').val()
	weatherEvent(id); 	
});

function weatherEvent(id){
	 var url = "";
	   
	// JSON
	url = "weather.ajax?reqType=json&id="+id;
	$.ajax({
		url :  url,
		type : "POST",
		cache : false,
		success : function(data, status){
			if(status == "success") {
				dietJSON(data);
			} 
			
		}
	 });
	
}

function dietJSON(jsonObj){
	var result = jsonObj.data;
	//console.log(result)
	
	var weather = new Array();
	var time1 = {}
	var time2 = {}
	var time3 = {}
	var time4 = {}
	var time5 = {}
	var time6 = {}
	var time7 = {}

	for(var i = 0; i < result.length; i++) {
		
		var val = ""
			
		if(result[i].category=='POP')
			val = result[i].fcstValue + '%'
		else if(result[i].category=='T3H')
			val = result[i].fcstValue + '℃'
		else if(result[i].category=='PTY' && result[i].fcstValue != 0) {
			
			switch(result[i].fcstValue) {
			case "1":
				val = '비';
				break;
			case "2":
				val = '비/눈';
				break;
			case "3":
				val = '눈';
				break;
			}
		}else if(result[i].category=='SKY' && result[i].fcstValue != 0) {
			
			switch(result[i].fcstValue) {
			case "1":
				val = '맑음';
				break;
			case "2":
				val = '구름조금';
				break;
			case "3":
				val = '구름많음';
				break;
			case "4":
				val = '흐림';
				break;
			}
		}
		
		if(val != '') {
			switch(result[i].fcstTime) {
			case "0300":
				time1["날짜"] = result[i].fcstDate;
				time1[result[i].category+''] = val;
				time1["시간"] = result[i].fcstTime;
				break;
			case "0600":
				time2["날짜"] = result[i].fcstDate;
				time2[result[i].category+''] = val;
				time2["시간"] = result[i].fcstTime;
				break;
			case "0900":
				time3["날짜"] = result[i].fcstDate;
				time3[result[i].category+''] = val;
				time3["시간"] = result[i].fcstTime;
				break;
			case "1200":
				time4["날짜"] = result[i].fcstDate;
				time4[result[i].category+''] = val;
				time4["시간"] = result[i].fcstTime;
				break;
			case "1500":
				time5["날짜"] = result[i].fcstDate;
				time5[result[i].category+''] = val;
				time5["시간"] = result[i].fcstTime;
				break;
			case "1800":
				time6["날짜"] = result[i].fcstDate;
				time6[result[i].category+''] = val;
				time6["시간"] = result[i].fcstTime;
				break;
			case "2100":
				time7["날짜"] = result[i].fcstDate;
				time7[result[i].category+''] = val;
				time7["시간"] = result[i].fcstTime;
				break;
			}
		}
 		
	}
	
	weather.push(time1)
	weather.push(time2)
	weather.push(time3)
	weather.push(time4)
	weather.push(time5)
	weather.push(time6)
	weather.push(time7)
	
	console.log(weather)
	
	var today = weather[0].날짜.substring(0,4)+'년 '+weather[0].날짜.substring(4,6)+'월 '+weather[0].날짜.substring(6,8)+'일'
	
	$('#today').text(today)
	
	for(var i=0; i< weather.length; i++) {
		$('.weather_box').append('<div class="one_box box_'+i+'"'+'></div>')
		$('.box_'+i).append('<div class="temp temp_'+i+'"'+'><h6>기온: '+weather[i].T3H+'</h6></div>')
		$('.box_'+i).append('<div class="staus staus_'+i+'"'+'"></div>')
		
		if(weather[i].SKY) {
			$('.staus_'+i).append('<div class="staus_text staus_text_'+i+'"'+'"><h6>'+weather[i].SKY+'</h6></div>')
			$('.staus_'+i).append('<div class="staus_img staus_img_'+i+'"'+'"></div>')
			
			//var img = ""
			
			switch(weather[i].SKY) {
			case "맑음":
				//img = "../Image/free-icon-sunny-890347.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-sunny-890347.png">')
				break;
			case "구름조금":
				//img = "../Image/free-icon-sun-861059.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-sun-861059.png">')
				break;
			case "구름많음":
				//img = "../Image/free-icon-cloud-116324.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-cloud-1163624.png">')
				break;
			case "흐림":
				//img = "../Image/free-icon-clouds-414927.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-clouds-414927.png">')
				break;
			}
			
		}else if(weather[i].PTY) {
			$('.staus_'+i).append('<div class="staus_text staus_text_'+i+'"'+'"><h6>'+weather[i].PTY+'</h6></div>')
			$('.staus_'+i).append('<div class="staus_img staaus_img_'+i+'"'+'"></div>')
			
			//var img = "";
			
			switch(weather[i].PTY) {
			case "비":
				//img = "../Image/free-icon-rain-1146858.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-rain-1146858.png">')
				break;
			case "비/눈":
				//img = "../Image/free-icon-rain-1146915.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-rain-1146915.png">')
				break;
			case "눈":
				//img = "../Image/free-icon-snowflake-1200430.png"
				$('.staus_img_'+i).append('<img alt="" src="../Image/free-icon-snowflake-1200430.png">')
				break;
			}
		}
		
		$('.box_'+i).append('<div class="rain rain_'+i+'"'+'"><h6>강수확률: '+weather[i].POP+'</h6></div>')
		
		var time = weather[i].시간.substring(0,2)+":"+weather[i].시간.substring(2,4)
		
		if(time.charAt(0) == '0') {
			time = time.substring(1,time.length)
		}
		
		$('.box_'+i).append('<div class="times times_'+i+'"'+'"><h6>'+time+'</h6></div>')
		
	}
	
}