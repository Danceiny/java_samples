/**
 * create the XmlHttpRequest Object
 * @return
 */
function createRequest(){
	
	try{
		request = new XMLHttpRequest();
	}catch(tryMS){
		try{
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(otherMS){
			try{
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(failed){
				request = null;
				alert("Create XMLHttpRequest Object failed");
			}
		}
	}
	return request;
}