var date = new Date();
var d = date.getDate();
var m = date.getMonth()+1;
var y = date.getFullYear();

$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
	$(".time_element").timepicki();
    	
});

window.onload = function () {
	var id = $('#id_wanted').val()
	console.log(id);
	getEvent(id); 
	
}

function getEvent(id){        //사용자 db일정정보 가져오기
  
	$.ajax({
    	url : 'schedule.ajax?reqType=json&id='+id,
    	type : 'POST',
    	cache : false,
    	success : function(data,status) {
    		
    		if(status=="success") {
    			createCalendarDateResult(data, id)
    		}
    		
    	}
  
    }, createCalendarDateResult);   //콜백함수
}

function createCalendarDateResult(resp, id){  //제이슨으로 캘린더 이벤트 등록형식에 맞게 뿌리기
	var result = resp.data
  
	console.log(result)
  
	var eventData = [];
	var diaryData = [];
  
	
	
	for(var i = 0; i < result.length; i++)
	{
		if(result[i].start_date == result[i].end_date) {
			eventData.push({
				no : result[i].no,
				title : result[i].content,
				start : result[i].start_date.split("T")[0],
			});
		}else if(result[i].start_date.split('T')[1] == '00:00:00'
			&& result[i].end_date.split('T')[1] == '00:00:00'){
			eventData.push({
				no : result[i].no,
				title : result[i].content,
				start : result[i].start_date.split("T")[0],
				end : result[i].end_date.split("T")[0]
			});
		} else {
			
			eventData.push({
				no : result[i].no,
				title : result[i].content,
				start : result[i].start_date,
				end : result[i].end_date
			});
		}
		
		diaryData.push({
			no : result[i].no,
			title : result[i].content,
			start : result[i].start_date,
			end : result[i].end_date,
			id : result[i].id,
		});
	}
   
	calendarEvent(eventData, diaryData, id);        //캘린더 메소드 호출
}

function calendarEvent(eventData, diaryData, id) {
    var calendarEl = document.getElementById('calendar');

    m = m >=10 ? m : '0'+m
    d = d >=10 ? d : '0'+d
    
    
    		
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridDay'
      },
      initialDate: y+'-'+m+'-'+d,
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
    	  
    	  
    	  $('#datePicker').val(arg.startStr)
    	  
    	  var modal = document.getElementById('myModal');
    	  var day_form = document.getElementById('day_form');
    	  var time_form = document.getElementById('time_form');
          modal.style.display = "block";
          var input = document.getElementById('input_title');
          var monthBtn = document.getElementById('button1');
          var datehBtn = document.getElementById('button2');
          var btn = document.getElementById('submit');
          var span = document.getElementsByClassName("close")[0];
        	   	
          var chk = true;
          
          span.onclick = function() {
        	  day_form.style.display = "block";
        	  arg.allDay = true;
        	  time_form.style.display = "none";
        	  modal.style.display = "none";
          }
        	
          window.onclick = function(event) {
        	  if (event.target == modal) {
        		  day_form.style.display = "block";
            	  arg.allDay = true;
            	  time_form.style.display = "none";
        		  modal.style.display = "none";
	      	 }
          }
          
          monthBtn.onclick = function() {
        	  day_form.style.display = "block";
        	  arg.allDay = true;
        	  time_form.style.display = "none";
        	  
          }
          
          datehBtn.onclick = function() {
        	  day_form.style.display = "none";
        	  arg.allDay = false;
        	  time_form.style.display = "block";
        	  //calendar.changeView('timeGridDay', arg.startStr);
          }
          
          btn.onclick = function() {
        	  
        	  var startTime= ""
        	  var endTime= ""
        	  
        	  if(arg.allDay) {
        		  
        		  if($.trim($('#input_title').val()) == null || $.trim($('#input_title').val()) == '') {
            		  alert("스케줄 내용을 입력해야 합니다.");
                  }else {
                	  
                	  var end = $('#datePicker').val();
                	  var end_y = end.substr(0,4);
                	  var end_m = end.substr(5,2);
                	  var end_d = end.substr(8,2);
                	  
                	  var nextDate = new Date(end_y, end_m-1, end_d)
                	  var n_d = nextDate.getDate()+1;
                	  var n_m = nextDate.getMonth()+1;
                	  var n_y = nextDate.getFullYear();
                	  
                	  n_m = n_m >=10 ? n_m : '0'+n_m
                	  n_d = n_d >=10 ? n_d : '0'+n_d
                	  
                	  end = n_y+"-"+n_m+"-"+n_d
                	  
                	  console.log(end)
                	  
                	  if($('#datePicker').val() == "") {
                		  end = arg.endStr
                	  }
                	  
                	  if((arg.startStr.split('-')[0]
                	  +arg.startStr.split('-')[1]
                	  +arg.startStr.split('-')[2])*1 <= 
                	  (end.split('-')[0]
                	  +end.split('-')[1]
                	  +end.split('-')[2])*1) {
                	  
	                	  calendar.addEvent({
	                          title: input.value,
	                          start: arg.startStr,
	                          end: end,
	                          allDay: arg.allDay
	                        })
	                        day_form.style.display = "block";
	                	  	arg.allDay = true;
        	  				time_form.style.display = "none";
	                        modal.style.display = "none";
	                        chkInsert(id, input.value, arg.startStr,  end);
                	  }else {
                		  alert("날짜 입력은 현재 날짜보다 같거나 커야합니다.");
                	  }
                  }
        		  
        		  
        	  }else {
        		  
        		  if($('#startTime').val().split(' ')[1] == 'AM') {
        			  var tt = $('#startTime').val().split(' ')[0].split(':')[0]
            		  var mm = $('#startTime').val().split(' ')[0].split(':')[1]
        			  if($('#startTime').val().split(' ')[0].split(':')[0] != '12') {
        				  startTime = $('#startTime').val().split(' ')[0]
        			  }else {
        				  tt = '00'
                		  startTime = tt+':'+mm 
        			  }
            	  }else if ($('#startTime').val().split(' ')[1] == 'PM'){
            		  var tt = $('#startTime').val().split(' ')[0].split(':')[0]
            		  var mm = $('#startTime').val().split(' ')[0].split(':')[1]
            		  if($('#startTime').val().split(' ')[0].split(':')[0] != '12') {
            			  tt = tt*1+12
            			  startTime = tt+':'+mm
            		  }else {
            		      startTime = tt+':'+mm
            		  }
            		  
            	  }
        		  
        		  if($('#endTime').val().split(' ')[1] == 'AM') {
        			  var tt = $('#endTime').val().split(' ')[0].split(':')[0]
            		  var mm = $('#endTime').val().split(' ')[0].split(':')[1]
        			  if($('#endTime').val().split(' ')[0].split(':')[0] != '12') {
        				  endTime = $('#endTime').val().split(' ')[0]
        			  }else {
        				  tt = '00'
        				  endTime = tt+':'+mm 
        			  }
            	  }else if ($('#endTime').val().split(' ')[1] == 'PM'){            		  
            		  var tt = $('#endTime').val().split(' ')[0].split(':')[0]
            		  var mm = $('#endTime').val().split(' ')[0].split(':')[1]
            		  if($('#endTime').val().split(' ')[0].split(':')[0] != '12') {
            			  tt = tt*1+12
            			  endTime = tt+':'+mm
            		  }else {
            			  endTime = tt+':'+mm
            		  }
            		  
            	  }
        		  
        		  if($.trim($('#input_title').val()) == null || $.trim($('#input_title').val()) == '') {
            		  alert("스케줄 내용을 입력해야 합니다.");
                  }else {
                	  
                	  if((startTime.split(':')[0]*1+startTime.split(':')[1]*1) <=
                		  (endTime.split(':')[0]*1+endTime.split(':')[1]*1)) {
                	  
	                	  calendar.addEvent({
	                          title: input.value,
	                          start: arg.startStr+"T"+startTime,
	                          end: arg.startStr+"T"+endTime,
	                          allDay: arg.allDay
	                        })
	                        day_form.style.display = "block";
	                	  	arg.allDay = true;
        	  				time_form.style.display = "none";
	                        modal.style.display = "none";
	                        
	                        chkInsert(id, input.value , arg.startStr+"T"+startTime,  arg.startStr+"T"+endTime);
                	  }else {
                		  alert("시간은 시작시간보다 종료시간이 커야합니다.");
                	  }
                  }
        		  
        	  }
        	 
          }
   
        calendar.unselect()
        
        
      },
      eventClick: function(arg) {
    	  
    	  var date = arg.event.start.getDate();
    	  var month = arg.event.start.getMonth()+1;
    	  var year = arg.event.start.getFullYear();
    	      	  
    	  var modal = document.getElementById('contentModal');
    	  modal.style.display = "block";
    	  var span = document.getElementsByClassName("close")[0];
    	  
    	  console.log(arg.event.startStr)
    	  
    	  
    	  var i = 0;
    	  for(i=0; i<diaryData.length; i++) {
    		  
    		  if(arg.event.startStr.indexOf('+') >-1) {
    			  
    			  if(arg.event.startStr.split('+')[0] == diaryData[i].start
    					  && arg.event.endStr.split('+')[0] == diaryData[i].end) {
    				  document.getElementById('schedule_title').innerHTML = diaryData[i].title;
        			  document.getElementById('schedule_regdate').innerHTML = diaryData[i].start.split('T')[0]
        			  +"<br><h5>"+diaryData[i].start.split('T')[1]+"~"+diaryData[i].end.split('T')[1]+"</h5>";
        			  break;
    			  }
    			  
    		  }else if(arg.event.startStr == diaryData[i].start.split('T')[0]
			  && arg.event.endStr == diaryData[i].end.split('T')[0]){
    			  document.getElementById('schedule_title').innerHTML = diaryData[i].title;
    			  document.getElementById('schedule_regdate').innerHTML = arg.event.startStr 
    			  + "~" + arg.event.endStr
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

		$('#btnDelete').click(function(){
			location.href = 'schedule_deleteOk.do?no=' + arg.event.extendedProps.no;
		});
 
      },
      eventDrop: function(arg) {
    	  
    	  console.log(arg.event.startStr)
    	  console.log(arg.event.endStr)
    	  scheUpdate(arg)
      },
      eventResize: function(arg){
    	  
    	  console.log(arg.event.startStr)
    	  console.log(arg.event.endStr)
    	  scheUpdate(arg)
      },
      editable: true,
      
      dayMaxEvents: true, // allow "more" link when too many events
      events: eventData
    });

    calendar.render();
    
}


$(function() {	
	$('#datePicker').datepicker({
	    format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
	    startDate: 'd',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
	    autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
	    calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
	    clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
	    disableTouchKeyboard : false,	//모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
	    immediateUpdates: false,	//사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false 
	    multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false 
	    multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
	    templates : {
	        leftArrow: '&laquo;',
	        rightArrow: '&raquo;'
	    }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징 
	    showWeekDays : true ,// 위에 요일 보여주는 옵션 기본값 : true
	    title: "날짜",	//캘린더 상단에 보여주는 타이틀
	    todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false 
	    toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
	    weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일 
	    language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
	    
	});//datepicker end
});//ready end

function scheUpdate(arg) {
	
	var start = ""
	var end = ""
	
	if(arg.event.startStr.indexOf('+') >-1) {
		start = arg.event.startStr.split('+')[0]
		end = arg.event.endStr.split('+')[0]
		chkUpdate(arg.event.extendedProps.no, arg.event.title, start, end)
	}else {
		start = arg.event.startStr
		end = arg.event.endStr
		chkUpdate(arg.event.extendedProps.no, arg.event.title, start, end)
	}
}

function chkInsert(id, content, start, end){
		
	location.href = 'schedule_writeOk.do?id='+id+'&content='+content
	+'&start='+start+'&end='+end;
	
} 


function chkUpdate(no, content, start, end){
		
	location.href = 'schedule_updateOk.do?no='+no+'&content='+content
	+'&start='+start+'&end='+end;
	
} 


function chkDelete(no){
	// 삭제 여부, 다시 확인 하고 진행하기
	var r = confirm("삭제하시겠습니까?");
	
	if(r){
		location.href = 'schedule_deleteOk.do?no=' + no;
	}
}
