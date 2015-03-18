$(function() {

	$("#markColor").colorpicker({
		fillcolor : true,

		target : $("#config_markColor")
	});
});

function preview() {
	//var img = $('#configMarkImagePath').attr('value');
	var img = document.getElementById("configMarkImagePath").value;
	if(img!="") {
		$('#markPreview').attr('src',img);		
	} else {
		$('#markPreview').removeAttr('src');
	}
}