// <![CDATA[
function isDecimalKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	var resp = false;
	
	if(charCode == 46 || (charCode >= 48 && charCode <= 57)){
		resp = true;
	}

	return resp;
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	
	if (charCode < 48 || charCode > 57){
		return false;
	}
		

	return true;
}
// ]]>
