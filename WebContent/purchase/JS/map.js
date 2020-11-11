let api_key = "AIzaSyDwxnbRm21uHihZ20b65SmP7JRi5QIBJJ4";

let x = 0;
let y = 0;

function loadDate(){
    var url = "https://maps.googleapis.com/maps/api/geocode/json?address=강남역&key="+api_key
    console.log(url)
    $.ajax({
        url : url,
        type: "GET",
        cache: false,
        success : function(data, status){
            if(status == "success") parseXML(data);
        }
    })
}

function parseXML(xmlDOM){
    if($(xmlDOM).find("status").html() == "ZERO_RESULTS"){
        alert("검색 결과가 없습니다.");

        return;
    }
    
    
    $("#location").html($(xmlDOM).find("formatted_address").html());
    $(xmlDOM).find("location").each(function(){
        x = eval($(this).find("lat").text());
        y = eval($(this).find("lng").text());
        myMap();
    });
}

function myMap() {
    var mapProp = {
        center: new google.maps.LatLng(x, y),
        zoom: 17
    }

    var map = new google.maps.Map(
        document.getElementById("map"), mapProp
    );

    var myPos1 = {
        lat: x,
        lng: y
    };
    var marker1 = new google.maps.Marker({
        position: myPos1
    });

    marker1.setMap(map);
}

$(".search_bar input").keydown(function(){
    if(event.keyCode == 13){
        address = $(this).val().trim();
        loadDate();
    }
});

setSize();
window.onresize = function(){
    setSize();
}

function setSize(){
    var height = $(window).height();
    var width = eval($(window).width())-390;
    
    $("#wrap").css("height", height+"px");
    $("#map").css("width", width+"px");
}

