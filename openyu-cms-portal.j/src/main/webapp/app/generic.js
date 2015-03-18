/**
 * 選取所有checkbox
 */
function pickAll(name, checked) {
	var elements = document.getElementsByName(name);
	for ( var i = 0; i < elements.length; i++) {
		if (elements[i].type != "checkbox") {
			continue;
		}
		elements[i].checked = checked;
	}
}

/**
 * enter提交
 */
function enterSubmit(event, name) {
	var key = window.event ? event.keyCode : event.which;
	if (key == 13 || key == 32) {
		var form = document.forms[name];
		form.submit();
	}
}

/**
 * 改變元素值 
 */
function changeValue(name, value) {
	var element = document.getElementById(name);
	if (element != null) {
		element.value = value;
	}
}

/**
 * 選取送出
 * @param formName
 * @param elementName
 * @param action
 * @param text
 * @param alertMsg
 * @param confirmMsg
 * @returns {Boolean}
 */
function pickSubmit(formName, elementName, action, text, alertMsg, confirmMsg) {

	if (!pickCheck(elementName, alertMsg) || !confirm(confirmMsg + text)) {
		return false;
	}
	//
	var form = document.forms[formName];
	if (typeof (action) != "undefined") {
		form.action = action;
	}
	//alert(form.name + " " + form.action);
	form.submit();
}

/**
 * 檢查選取
 * @param elementName
 * @param alertMsg
 * @returns {Boolean}
 */
function pickCheck(elementName, alertMsg) {
	var elements = document.getElementsByName(elementName);
	for ( var i = 0; i < elements.length; i++) {
		if (elements[i].type != "checkbox") {
			continue;
		}
		if (elements[i].checked) {
			//alert("pick one");
			return true;
		}
	}
	alert(alertMsg);
	return false;
};

;
