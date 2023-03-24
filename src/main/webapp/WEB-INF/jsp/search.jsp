<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
	* {
		margin: 0px;
		padding: 0px;
		list-style: none;
		text-decoration: none;
		color: black;
		border-collapse: collapse;
	}
	
	#layout1 {
		width: 100%;
	}
	
	#container1 {
		width: 800px;
		margin: auto;
		margin-top: 300px;
	}
	
	#container1 table {
		margin: auto;
		text-align: center;
		border: 1px solid black;
		width: 800px;
	}
	#layout2 {
		width: 100%;
	}
	
	#container2 {
		width: 800px;
		margin: auto;
		text-align: center;
	}
	
	#container2 ul {
		margin-top: 10px;
	}
	
	#container2 ul li {
		display: inline;
	}
	
	#container2 ul li a {
		padding: 5px;
	}
	
	#clickList {
		padding: 10px;
	}
	
	#clickList a{
		padding: 10px 80px;
		transition: all 0.2s;
		color: black;
	}
	
	#clickList a:HOVER {
		background-color: black;
		color: white;
	}
	
	#orderView1 {
		transition : all 0.3s;
	}	
	
	#orderView1:hover {
		background-color: #F0F0F0;
	}
	
	#orderByView {
	    position: absolute; 
	    top: 230px;
	    left: 1500px;
		animation:ani 2s infinite alternate;  
		width: 300px;
		height: 200px;
		text-align: center;
		transition: all 1s cubic-bezier(0.340, -0.600, 0.515, 1.570);
	}
	
	@keyframes ani {
		0% {
			transform:translate(0,0px);
		}
		100% {
			transform:translate(0,20px);
		}
	}  
	
	
</style>

<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	
});



/* function fn_searchClick() {
	
		var idx = document.getElementById('idx').value;
		var keyWord = $("#keyWord").val();
		
		alert(idx);
		alert(keyWord);
		
	    $.ajax({
	        url: "search.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: JSON.stringify({
	        	idx : idx,
	        	keyWord : keyWord
	        }),
	        contentType: 'application/json; charset=utf-8',
	        success: function(data){
	        	
	        	console.log(data);
	        	
	        	alert("성공");
	        	
	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
		
} */



</script>

</head>
<body>
<div id="layout1">
	<div id="orderByView">
		<div>
			<span>조회순</span>
			<c:forEach var="orderByView" items="${orderByView }">
				<ul id="orderView1" style="border: 1px solid black; padding: 15px 0px;"><a href="selectView.do?paymentId=${orderByView.paymentId }">
					<li>고객아이디 ${orderByView.customerId }</li>
					<li>번호 ${orderByView.paymentId }</li>
					<li>조회수 ${orderByView.viewCnt }</li>
				</a></ul>
			</c:forEach>
		</div>
	</div>
	
	<div id="container1">
		<div style="text-align: center;">
<%-- 		<form id="searchArea" method="post">
				<select name="idx" id="idx" style="padding: 10px 5px 10px 5px;">
					<option value="0">제목</option>
					<option value="1">이름</option>
				</select>
				<input type="text" id="keyWord" name="keyWord" placeholder="검색하세요." style="padding: 10px 150px 10px 5px;">
				<input type="button" value="검색" id="searchClick" onclick="fn_searchClick();" style="padding: 10px 10px 10px 10px;">			
			</form> --%>
			<form action="search.do" method="post">
				<select name="idx" id="idx" style="padding: 10px 5px 10px 5px;">
					<option value="0">제목</option>
					<option value="1">이름</option>
				</select>
				<input type="text" id="keyWord" name="keyWord" placeholder="검색하세요." style="padding: 10px 150px 10px 5px;">
				<input type="submit" value="검색" id="searchClick" style="padding: 10px 10px 10px 10px;">			
			</form>
		</div>
		<div style="margin-bottom: 10px; float: right;">
			<input type="button" value="로그아웃" onclick="javascript:location.href='logout.do'" style="padding: 5px 20px;">
			<input type="hidden" value="${user.id }" name="id">
			<input type="hidden" value="${user.password }" name="password">
		</div>
		<table>
			<tr>
				<th>순서</th>
				<th>번호</th>
				<th>고객아이디</th>
				<th>좋아요</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="searchList" items="${searchList }">
				<tr id="tr1">
					<td width="15%">${searchList.rNum2 }</td>
					<td id="clickList" width="30%"><a href="selectView.do?paymentId=${searchList.paymentId }">${searchList.paymentId }</a></td>
					<td width="25%">${searchList.customerId }</td>
					<td width="15%">${searchList.likeCnt }</td>
					<td width="15%">${searchList.viewCnt }</td>
				</tr>
			</c:forEach>
			<c:if test="${searchTot == 0}">
				<tr id="tr1">
					<td colspan="5" style="padding: 30px; font-size: 20px; font-weight: bold;">등록된 게시글이 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>

</body>
</html>
