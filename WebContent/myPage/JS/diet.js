var result = ""
var dietInfo = ""
var rowData;
$(document).ready(function(){   //페이지 로딩시 getEvent() 를가장먼저 호출
	var id = $('#diet_id').val() 	
	$('#myModal2').modal("hide");
	$('#myModal3').modal("hide");
	dietEvent(id);
	
});

function dietEvent(id){
	 var url = ""
		 	 
	// JSON
	url = "diet.ajax?reqType=json&id="+id;
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
				weightJSON(data);
			} 
			
		}
	 });
	
}

function weightJSON(jsonObj){
	dietInfo = jsonObj.data
	
	rowData = new Array(dietInfo.length);
	
	for(var i=0; i< dietInfo.length; i++) {
		
		var dateDate = dietInfo[i].regdate.split(" ")[0]
		
		var row = [new Date(dateDate.split('-')[0], dateDate.split('-')[1]-1, dateDate.split('-')[2]) 
		, dietInfo[i].weight];
		
		rowData[i] = row
	}
	if(rowData.length !=0) {
		google.charts.load('current', {'packages':['line']});
		google.charts.setOnLoadCallback(drawChart);
	}else {
		$('#chart_div').append("<h1 id='empty_text'><h1>")
		$('#empty_text').text("체중을 입력해야 그래프가 보입니다.")
	}
		
	
	console.log(rowData)
}



function drawChart() {

var chartDiv = document.getElementById('chart_div');

var data = new google.visualization.DataTable();
data.addColumn('date', 'Date');
data.addColumn('number', "내 강아지 몸무게");
data.addRows(rowData);
	
var materialOptions = {
  chart: {
    title: '체중 변화'
  },

  series: {
    // Gives each series an axis name that matches the Y-axis below.
    0: {axis: 'Temps'}
    
  },
  axes: {
    // Adds labels to each axis; they don't have to match the axis names.
    y: {
      Temps: {label: '몸무게'}
    }
  }
};

function drawMaterialChart() {
  var materialChart = new google.charts.Line(chartDiv);
  materialChart.draw(data, materialOptions);
}

drawMaterialChart();

}

$('#input_weight').click(function() {
	$('#myModal2').modal("show");
})

$('#delete_weight').click(function() {
	$('#myModal3').modal("show");
	$('#delete_val').text("현재몸무게는 "+rowData[rowData.length-1][1]+'kg 삭제하시겠습니까?')
})

function deleteSubmit(){
	$('#petId2').val(result[0].petId)
	$('#frm_delete').attr('action', 'diet_deleteOk.do')
}

function chkSubmit(){
	frm = document.forms["frm"];
	
	var weight = frm["weight"].value.trim();
	var regex = /^[0-9]*$/;

    if (weight == "") {
        alert("체중을 입력해야합니다.");
        frm["weight"].focus();
        return false;
    }else if (!regex.test(weight)) {
    	alert("숫자만 입력 가능합니다.");
        frm["weight"].focus();
        return false;
    }
    
    $('#petId').val(result[0].petId)
    $('#frm_id').attr('action', 'diet_writeOk.do')
    return true;
}