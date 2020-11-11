function chkSubmit(){
	frm = document.forms["frm"];
	
	var title = frm["title"].value.trim();

    if (title == "") {
        alert("제목은 반드시 작성해야 합니다");
        frm["title"].focus();
        return false;
    }
    return true;
}

function upfiles(file) {
	$("#delFiles").append("<input type='hidden' name='delFile' value='"+file +"'>");
	console.log("file: "+ file)
	
}

function deleteFiles(file){
	
	$("#up-file").val("");

	$('#uploadImg').attr('src',"../Image/noimg.gif");
	console.log("file: "+ file)
	$("#delFiles").append("<input type='hidden' name='delFile' value='"+file +"'>");
	
}

$(function() {
    $("#up-file").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#uploadImg').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}
