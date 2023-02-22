<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link type="text/css" href="<c:url value='/css/default/nan5FBD.css'/>"  rel="stylesheet"/>
<link type="text/css" href="<c:url value='/css/default/default.css'/>"  rel="stylesheet"/>
<!--[if lte IE 6]><link rel="stylesheet" type="text/css" href="<c:url value='/css/default/default_ie6.css'/>"" /><![endif]-->
<style>
.hide { display: none !important; visibility: hidden; height: 0; line-height: 0; font-size: 1px; /* <-요거 */}
.load_con { background: url(images/bg_noti.gif), url(../images/bg_noti.gif) no-repeat left top; width: 539px; height: 436px; padding: 135px 96px 0 96px; position: absolute; left: 50%; top: 50%; margin-left: -365px; margin-top: -285px; border: 1px solid #e1e1e1;}
.box { padding: 10px; width: 539px; height: 241px; text-align: center; line-height: 24px;}
.box p {font-size: 13px;  padding-top: 15px;}
.box button { background: #06F; width: 120px; height: 40px; line-height: 33px; color: #fff; display: block; font-size: 13px; margin: 0 auto; margin-top: 30px; }
</style>
<script type="text/javascript" src="<c:url value='/js/lib/jquery-1.8.0.min.js' />"></script>
<title>Error</title>
<script type="text/javascript">
$(document).ready(function(){
	var imgs = document.getElementsByTagName('img');
	for(var i=0;i<imgs.length;++i) {
	  if(imgs[i].className === 'erroIcon') {
	    if(navigator.userAgent.indexOf("MSIE ")>-1) {
	      imgs[i].attachEvent("onerror",function(e) { e.srcElement.src ="../images/noti.gif"; })  // IE
	
	    } else {
	      imgs[i].onerror = function(e) { e.target.src = '../images/noti.gif';};  // FireFox, chrome, Opera, ...
	    }
	  }
	}
});
</script>
</head>


<body style="background: none;">


	<div id="roundBox_loading" class="load_con">
		<b class="rtop">
			<b class="r1"></b>
			<b class="r2"></b>
			<b class="r3"></b>
			<b class="r4"></b>
		</b>
		<div class="box">
			<img class="erroIcon" src="images/noti.gif" alt="" style="margin-top: 13px;" width="40" /> <br/>
			<p><spring:message code='fail.common.msg' /></p>				
		</div>
        <b class="rbottom" align="center"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b class="r1"></b></b>
	</div>
</body>
</html>