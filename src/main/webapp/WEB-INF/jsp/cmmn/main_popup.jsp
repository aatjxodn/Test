<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head> 
<title>${rs.subject }</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript" src="se2/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js "></script>
<script type="text/javascript" src="./se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="./js/lib/jquery.fileupload.js"></script>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/util.js'/>"></script>
<style type="text/css">
/* * {
margin:0;
padding:0;
border:0; 

font-family: 'NanumGothic';
font-weight: normal; font-size: 12px;
} */

#ph_popup_call {
	width:100%;
	height: 100%;
}

#pop_filedown {
	margin: 0;
	padding: 5px;
	background: #c7c7c7;
	color: #000000;
	line-height: 20px;
	font-weight: bold;
}
#pop_filedown label {
	vertical-align: top;
	margin-left: 5px;
}

#pop_close {
	margin: 0;
	padding: 5px;
	background: #7e7e7e;
	color: #ffffff;
	line-height: 20px;
	font-weight: bold;
}

#pop_close input[type='checkbox'] {
	margin-top: 5px;
}

#pop_close label {
	vertical-align: top;
	margin-left: 5px;
}

#pop_close span a{
	float: right;
	font-size: 12px;
	text-decoration: none;
	color: #ffffff;
	padding-right: 5px;
}
</style>
<script type="text/javascript">
<!--
function setCookie(name, value, expiredays){
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate()+expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

function fn_close_pop(){
	var ch_box = document.getElementById("today_no"); 
	if(ch_box.checked){
		setCookie("pop_new"+${rs.idx}, "done", 7);
	}
	self.close();
}
-->
</script>
</head>
<body style="overflow-x:hidden; overflow-y:auto; margin: 0px;">

<!-- 팝업 -->
<div id="ph_popup_call">
	<div>
	<!-- <img src="images/jeju_main_popup.jpg" alt="센터 인터넷.앱 예약시스템 구축으로 인해 홈페이지 서비스 일부가 제한됩니다. 빠른 시일내에 정상화 될 수 있도록 최선을 다하겠습니다." />  -->
	<c:out value='${rs.contents}' escapeXml='false'/>
	</div>
	
	<c:if test="${!empty rs.attach_file}">
	<div>
		<form name="filedown" id="pop_filedown">
			<label>※ 파일 다운로드 :</label><label id="filelist"><script>parseFile2('${rs.attach_file}','${rs.attach_source}', 4);</script></label>
		</form>
	</div>
	</c:if>
	
	<div>
		<form name="checkClose" id="pop_close">
			<input type="checkbox" name="Notice" id="today_no"/><label for="today_no">일주일 보지 않기</label>
			<span><a href="javascript:fn_close_pop()">창닫기 X</a></span>
		</form>
	</div>
</div>

  <!-- //팝업 -->


</body>
</html>