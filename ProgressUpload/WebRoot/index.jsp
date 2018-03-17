<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>带进度条的文件上传Demo</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/util.js"></script>
  </head>
  
  <body>
    	<form id="f1" action="${pageContext.request.contextPath}/servlet/UploadServlet" method="post" enctype="multipart/form-data">
    		<table>
	    		<tr>
	    			<td>file:<input type="file" name="file"/></td>
	    			<td>
	    				<div id="progressBar" style="width: 200px; border: 1px solid gray;display: none;">
							<div id="progress" style="width:0%; background-color:green;"></div>
						</div>
	    			</td>
	    			<td>
	    				<span id="progressPercent"></span>
	    			</td>
	    		</tr>
	    	</table>
	    	<input type="button" value="上传" onclick="toSubmit()">
    	</form>
    	<script type="text/javascript">
    		var requestObject = createRequest();
    		//var progress = document.getElementById("progress");
    		//var progressPercent = document.getElementById("progressPercent");
	    	var progressInterval = null; //
	    	function toSubmit() {
	    		document.getElementById("f1").submit();
	    		var progressInterval = window.setInterval(doSubmitAsync, 100);
	    		progressBar.style.display='block';
	    	}
	    	//异步提交请求
	    	function doSubmitAsync(){
	    		var url = "/ProgressUpload/servlet/UploadServlet?"+(new Date()).getTime();
	    		requestObject.open("GET", url, true);
	    		requestObject.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
	    		requestObject.onreadystatechange = callback;
	    		requestObject.send(null);
	    	}
	    	//回调函数
	    	function callback() {
	    		if (requestObject.readyState == 4) {
	    			if (requestObject.status == 200) {
	    				var value = requestObject.responseText;
	    				if (value == "100%") {
	    					window.clearInterval(progressInterval);
	    					progress.style.width = "100%";
	    				} else {
	    					progress.style.width = value;
	    					progressPercent.innerHTML = value;
	    				}
	    			}
	    		}
	    	}
    	</script>
  </body>
</html>
