var date = new Date();
var d = date.getDate();
var m = date.getMonth()+1;
var y = date.getFullYear();

$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
     
});

window.onload = function () {
	var id = $('#id_wanted').val()
	console.log(id);
	getEvent(id); 
	
}
 
function getEvent(id){        //사용자 db일정정보 가져오기
  
	$.ajax({
    	url : 'diary.ajax?reqType=json&id='+id,
    	type : 'POST',
    	cache : false,
    	success : function(data,status) {
    		
    		if(status=="success") {
    			createCalendarDateResult(data, id)
    		}
    		
    	}
  
    }, createCalendarDateResult);   //콜백함수
}

function createCalendarDateResult(resp , id){  //제이슨으로 캘린더 이벤트 등록형식에 맞게 뿌리기
	var result = resp.data
  
	console.log(result)
  
	var eventData = [];
	var diaryData = [];
  
	for(var i = 0; i < result.length; i++)
	{
		eventData.push({
			no : result[i].no,
			title : result[i].title,
			start : result[i].regdate.split("T")[0]
		});
		
		diaryData.push({
			no : result[i].no,
			title : result[i].title,
			start : result[i].regdate.split("T")[0],
			content : result[i].content,
			img : result[i].img,
			id : result[i].id,
		});
	}
   
	calendarEvent(eventData, diaryData, id);        //캘린더 메소드 호출
}

function calendarEvent(eventData, diaryData, id)       
{
	
	var calendarEl = document.getElementById('calendar');
	
	m = m >=10 ? m : '0'+m
	d = d >=10 ? d : '0'+d
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialDate: y+'-'+m+'-'+d,
		navLinks: true,
		selectable: true,
		selectMirror: true,
		select: function(arg) {
			
			var chk = false;
			var i = 0
			for(i=0; i<diaryData.length; i++) {
				if(arg.startStr == diaryData[i].start) {
					
				  	document.getElementById('diary_title').innerHTML = diaryData[i].title;
				  	document.getElementById('diary_regdate').innerHTML = diaryData[i].start;
				  	if(typeof diaryData[i].img == "undefined" || diaryData[i].img  == null || diaryData[i].img == "") {
				  		document.getElementById('diary_image').src = "../Image/noimg.gif";
				  	}else {
				  		document.getElementById('diary_image').src = "../../upload/diary/"+diaryData[i].img;
				  	}
				  	document.getElementById('diary_content').innerHTML = diaryData[i].content;
					
					chk = true
					break;
				}
			}
			
			if(chk) {
				var modal = document.getElementById('myModal');
			  	modal.style.display = "block";
			  	var span = document.getElementsByClassName("close")[0];
				
			  	span.onclick = function() {
			        modal.style.display = "none";
			  	}
				
				window.onclick = function(event) {
			      if (event.target == modal) {
			          modal.style.display = "none";
			      }
				}
				
				$('#btnConfirm').click(function(){
					modal.style.display = "none";
				});

				$('#btnRevise').click(function(){
					location.href="diary_update.do?no="+diaryData[i].no
				});

				$('#btnDelete').click(function(){
					location.href = 'diary_deleteOk.do?no=' + diaryData[i].no;
				});
			  	
			}else if(!chk) {
				location.href="diary_write.jsp?id="+id+"&regdate="+arg.startStr;
			}
			
			
		},
		eventClick: function(arg) {
											
			var modal = document.getElementById('myModal');
		  	modal.style.display = "block";
		  	var span = document.getElementsByClassName("close")[0];
		  	var i = 0
		  	var num = 0;
		  	for(i=0; i<diaryData.length; i++) {
				if(arg.event.startStr == diaryData[i].start) {
					document.getElementById('diary_title').innerHTML = diaryData[i].title;
				  	document.getElementById('diary_regdate').innerHTML = diaryData[i].start;
				  	if(typeof diaryData[i].img == "undefined" || diaryData[i].img  == null || diaryData[i].img == "") {
				  		document.getElementById('diary_image').src = "../Image/noimg.gif";
				  	}else {
				  		document.getElementById('diary_image').src = "../../upload/diary/"+diaryData[i].img;
				  	}
				  	document.getElementById('diary_content').innerHTML = diaryData[i].content;
				  	num = diaryData[i].no
				  	break;
				}
			}
		  	
		  	span.onclick = function() {
		          modal.style.display = "none";
		    }
		  	
			window.onclick = function(event) {
		        if (event.target == modal) {
		            modal.style.display = "none";
		        }
		    }
			
			$('#btnConfirm').click(function(){
				
				modal.style.display = "none";
			});

			$('#btnRevise').click(function(){
				
				location.href="diary_update.do?no="+arg.event.extendedProps.no
			});

			$('#btnDelete').click(function(){
				location.href = 'diary_deleteOk.do?no=' + arg.event.extendedProps.no;
			});
		
			/*
		    if (confirm('Are you sure you want to delete this event?')) {
		      arg.event.remove()
		    }
		    */
		  },
		  editable: false,
		  dayMaxEvents: true,
		  events: eventData
	});
	calendar.render();
	

}

function chkDelete(no){
	// 삭제 여부, 다시 확인 하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = 'diary_deleteOk.do?no=' + no;
	}
} // chkDelete