function checkAll(){
	if($("#ids").attr("checked")){
		$("input[name='ids']").each(function(i){
			$(this).attr("checked","checked");
		 });
		}else{
			$("input[name='ids']").each(function(i){
				$(this).attr("checked","");
			 });
		}
}
//批量刪除到垃圾箱
function toTrash(){
	var ids=new Array();
	$("input[name='ids']").each(function(i){
		if($(this).attr("checked")){
			ids.push($(this).val());
		}
	 });
	 if(ids.length>0){
		 if(!confirm("您確定要刪除這些資訊嗎？")) {
				return;
			}
		 $.post("message_trash.jspx", {
				"ids" : ids
			}, function(data) {
				if(data.result){
					for(var i=0;i<ids.length;i++){
						$("#tr_"+ids[i]).remove();
						}
					 $("#msgDiv").html("您選擇的站內資訊已被移動到垃圾箱 ");
				}else{
					alert("請先登錄");
				}
			}, "json");
		 }else{
			 $("#msgDiv").html("請先選擇資訊");
			 }
}
//單條信息到垃圾箱
function trash(id){
	 if(!confirm("您確定要刪除該條資訊嗎？")) {
			return;
		}
	 $.post("message_trash.jspx", {
			"ids" : id
		}, function(data) {
			if(data.result){
				$("#jvForm").submit();
			}else{
				alert("請先登錄");
			}
		}, "json");
}
function forward(){
	$("#jvForm").attr("action","message_forward.jspx");
	$("#jvForm").submit();
}
function empty(){
	var ids=new Array();
	$("input[name='ids']").each(function(i){
		if($(this).attr("checked")){
			ids.push($(this).val());
		}
	 });
	 if(ids.length>0){
		 if(!confirm("您確定要徹底刪除這些資訊嗎？")) {
				return;
			}
		 $.post("message_empty.jspx", {
				"ids" : ids
			}, function(data) {
				if(data.result){
					for(var i=0;i<ids.length;i++){
						$("#tr_"+ids[i]).remove();
						}
					 $("#msgDiv").html("您選擇的站內資訊已被徹底刪除 ");
				}else{
					alert("請先登錄");
				}
			}, "json");
		 }else{
			 $("#msgDiv").html("請先選擇資訊");
			 }
}
function emptySingle(id){
	 if(!confirm("您確定要徹底刪除該資訊嗎？")) {
			return;
		}
	 $.post("message_empty.jspx", {
			"ids" : id
		}, function(data) {
			if(data.result){
				$("#jvForm").submit();
			}else{
				alert("請先登錄");
			}
		}, "json");
}
function revert(){
	var ids=new Array();
	$("input[name='ids']").each(function(i){
		if($(this).attr("checked")){
			ids.push($(this).val());
		}
	 });
	 if(ids.length>0){
		 if(!confirm("您確定要還原這些資訊嗎？")) {
				return;
			}
		 $.post("message_revert.jspx", {
				"ids" : ids
			}, function(data) {
				if(data.result){
					for(var i=0;i<ids.length;i++){
						$("#tr_"+ids[i]).remove();
						}
					 $("#msgDiv").html("您選擇的站內資訊已還原 ");
				}else{
					alert("請先登錄");
				}
			}, "json");
		 }else{
			 $("#msgDiv").html("請先選擇資訊");
			 }
}
function toDraft(){
	$("#box").val(2);
	$("#nextUrl").val("message_mng.jspx?box=2");
	$("#jvForm").attr("action","message_save.jspx");
	$("#jvForm").submit();
}
function toSend(){
	$("#nextUrl").val("message_mng.jspx?box=1");
	$("#jvForm").attr("action","message_tosend.jspx");
	$("#jvForm").submit();
}
function reply(){
	$("#nextUrl").val("message_reply?box=1");
	$("#jvForm").attr("action","message_reply.jspx");
	$("#jvForm").submit();
}
function find_user(){
	var username=$("#username").val();
	$.post("message_findUser.jspx", {
		"username" : username
	}, function(data) {
		if(data.result){
			if(data.exist){
				$("#usernameMsg").html("沒有此用戶");
				$("#username").val("");
			}else{
				$("#usernameMsg").html("");
			}
		}else{
				alert("請先登錄");
		}
	}, "json");
}