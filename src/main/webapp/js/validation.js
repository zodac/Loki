function validateIMSI(){
	var imsi = document.forms["imsiform"]["imsi"].value;
	var RE = /^-{0,1}\d*\.{0,1}\d+$/;
    if(!RE.test(imsi)){
    	alert("Invalid IMSI format!");
    	document.forms["imsiform"]["imsi"].focus();
    	return false;
    }
    return true;
}