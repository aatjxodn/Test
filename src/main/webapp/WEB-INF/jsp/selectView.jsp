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
<title>상세보기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>

body {
	background-color: #f8f8f8;
}
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
		margin-top: 200px;
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
	
	#container2 table {
		margin: auto;
		text-align: center;
		border: 1px solid black;
		width: 600px;
		margin-top: 50px;
	}
	
	#layout3 {
		width: 100%;
	}
	
	#container3 {
		width: 600px;
		margin: auto;
	}
	
	#layout4 {
		width: 100%;
		margin-top: 50px;
		margin-bottom: 50px;
	}
	
	#container4 {
		width: 600px;
		margin: auto;
		text-align: center;
	}
	
	#moreButton {
		padding: 15px 40px;
		cursor: pointer;
	}
	
	#layout5 {
		width: 100%;
		margin-top: 50px;
		margin-bottom: 50px;
	}
	
	#container5 {
		width: 600px;
		margin: auto;
		text-align: center;
	}
	
	#listGo {
		padding: 10px 35px;
		cursor: pointer;
	}

	
	
	
</style>

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js "></script> -->
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

// 댓글 추가 기능
$(document).ready(function(){
	
	var TSC = ${totalSelectCommentList };
	
	if (TSC <= 5) {
		$("#moreButton").hide();
	}
	
	fn_selectCommentList();
	
	$('#insertClick').on('click', function(){
	    $.ajax({
	        url: "insertComment.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: $('#insertCommentFirst').serialize(),
	        success: function(data){

	        	console.log(data);

	            fn_selectCommentList();
	            
	            $("#commentArea").val("");

	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
	});

});

// 댓글 목록 조회 기능
function fn_selectCommentList() {
	
	$("#insertCommentArea").css("display","none");
	
    $.ajax({
        url: "selectCommentList.do",
        type: "POST",
        async: false,
        dataType: 'json',
        data: $('#insertCommentFirst').serialize(),
        /* contentType: 'application/json; charset=utf-8', */
        success: function(data){
        	console.log(data);
        	
        	let display = "";
             
            for (var selectCommentList of data) {
            	
				let groupNum = selectCommentList.seq;
	
             	var margin = 0;
             	var width = 600;
             	for(var i=0; i < selectCommentList.depth; i++) {
             		margin += 20;
             		width += -20;
             	}
             	
             	display += "<ul id='ul_"+selectCommentList.seq+"' style='border-bottom: 1px solid #ccc; width:"+ width +"; padding: 15px 0px 15px 0px; margin-left: "+margin+"px; list-style: none;'>";
             	display += "<li style='font-weight: bold; padding: 5px 0px;'>" + selectCommentList.id + "</li>";
             	display += "<li style='padding: 5px 0px;'>" + selectCommentList.comment + "</li>";
             	display += "<li style='padding: 5px 0px;'>";	                
             	display += "<input type='button' value='답글 작성' id='insertCommentButton' onclick='fn_insertCommentButton("+selectCommentList.seq+","+selectCommentList.depth+","+selectCommentList.groupOrder+","+ selectCommentList.groupNum +")'>";		                   
             	if (selectCommentList.id) {
             		display += "<input type='button' value='수정' id='updateCommentButton' onclick='fn_updateCommentButton("+selectCommentList.seq +")'>";	         		
             		display += "<input type='button' value='삭제' id='deleteCommentButton' onclick='fn_deleteCommentButton("+selectCommentList.seq +")'>";	         		
             	}
             	display += "</li>";
             	display += "</ul>";
             	display += "<ul id='ul2_"+selectCommentList.seq+"'>";
             	display += "</ul>";
	                
             }
             
             $("#container3").html(display);

        },
        error: function(){
        	alert("댓글 보기 실패");  
        }
 		
    });
}


// 답글 작성 버튼 누를 시 생성 
function fn_insertCommentButton(seq, depth, groupOrder, groupNum) { 
	
	var testHtml = "";

	testHtml += "<form id='insertCommentCommentArea' method='post' style='display: block; margin-top: 15px;'>";
	testHtml += "<input type='text' value='' name='comment' style='padding: 15px; width: 300px; text-align: left;' placeholder='답글을 입력하세요.'>";
	testHtml += "<input type='button' value='취소' onclick='fn_cancelBut()' style='padding: 15px;'>";
	testHtml += "<input type='button' value='답글' onclick='fn_insertCommentComment()' style='padding: 15px;'>";
	testHtml += "<input type='hidden' value='" + groupNum + "' id='groupNum' name='groupNum'>";
	testHtml += "<input type='hidden' value='" + depth + "' id='depth' name='depth'>";
	testHtml += "<input type='hidden' value='" + groupOrder + "' id='order' name='groupOrder'>";
	testHtml += "<input type='hidden' value='" + seq + "' id='seq' name='seq'>";
	testHtml += "<input type='hidden' value='${selectView.paymentId }' name='paymentId'>";
	testHtml += "<input type='hidden' value='${user.id }' name='id'>";
	testHtml += "</form>";
	
	$("#ul2_"+seq).append(testHtml)
	$("#depth").val(depth+1);
	$("#order").val(groupOrder+1);
	
	// 버튼 비활성화
//	const target1 = document.getElementById('insertCommentButton');
//	const target2 = document.getElementById('updateCommentButton');
//	const target3 = document.getElementById('deleteCommentButton');
//	target1.disabled = true;
//	target2.disabled = true;
//	target3.disabled = true;

}

// 답변 수정 버튼 누를 시 생성
function fn_updateCommentButton(seq, depth, groupOrder, groupNum) {
	
	var testHtml = "";

	testHtml += "<form id='updateCommentCommentArea' method='post' style='display: block; margin-top: 15px;'>";
	testHtml += "<input type='text' value='' name='comment' style='padding: 15px; width: 300px; text-align: left;' placeholder='답글을 수정하세요.'>";
	testHtml += "<input type='button' value='취소' onclick='fn_cancelBut2()' style='padding: 15px;'>";
	testHtml += "<input type='button' value='수정' onclick='fn_updateCommentComment()' style='padding: 15px;'>";
	testHtml += "<input type='hidden' value='" + seq + "' id='seq' name='seq'>";
	testHtml += "<input type='hidden' value='${selectView.paymentId }' name='paymentId'>";
	testHtml += "<input type='hidden' value='${user.id }' name='id'>";
	testHtml += "</form>";


	$("#ul2_"+seq).append(testHtml)
	$("#depth").val(depth+1);
	$("#order").val(groupOrder+1);
	
	// 버튼 비활성화
//	const target1 = document.getElementById('insertCommentButton');
//	const target2 = document.getElementById('updateCommentButton');
//	const target3 = document.getElementById('deleteCommentButton');
//	target1.disabled = true;
//	target2.disabled = true;
//	target3.disabled = true;
}

// 답글 및 답변 수정
function fn_updateCommentComment() {

	var result = confirm('수정 하시겠습니까?');
	if (result == true) {
		
	    $.ajax({
	        url: "goUpdateComment.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: $('#updateCommentCommentArea').serialize(),
	        success: function(data){
	        	console.log(data);
	        	
	        	alert('수정이 완료되었습니다.');
	        	
	            fn_selectCommentList();
	            
	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
		
	}
	
}


// 답변 삭제
function fn_deleteCommentButton(seq) {

	var result = confirm('삭제 하시겠습니까?');
	if (result == true) {
		
		var deleteSeq = seq;
		
	    $.ajax({
	        url: "deleteComment.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: {
	        	deleteSeq : deleteSeq
	        },
	        success: function(data){
	        	console.log(data);
	        	
	        	alert('삭제가 완료되었습니다.');
	        	
	            fn_selectCommentList();
	            
	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
		
	}
}


//답글 취소 기능
function fn_cancelBut() {
	
//	var testHtml = "";
	
//	$("#insertCommentCommentArea").html(testHtml);
	$("#insertCommentCommentArea").css("display","none");
	
	// 버튼 활성화
//	const target1 = document.getElementById('insertCommentButton');
//	const target2 = document.getElementById('updateCommentButton');
//	const target3 = document.getElementById('deleteCommentButton');
//	target1.disabled = false;
//	target2.disabled = false;
//	target3.disabled = false;

	
}

//답글 취소 기능2
function fn_cancelBut2() {
	$("#updateCommentCommentArea").css("display","none");
	
	$("#ul2_"+seq).append(testHtml)
	$("#depth").val(depth+1);
	$("#order").val(groupOrder+1);
	

	// 버튼 활성화
//	const target1 = document.getElementById('insertCommentButton');
//	const target2 = document.getElementById('updateCommentButton');
//	const target3 = document.getElementById('deleteCommentButton');
//	target1.disabled = false;
//	target2.disabled = false;
//	target3.disabled = false;
}


// 답글 추가 기능
function fn_insertCommentComment() {
	
    $.ajax({
        url: "insertCommentComment.do",
        type: "POST",
        async: false,
        dataType: 'json',
        data: $('#insertCommentCommentArea').serialize(),
        success: function(data){
        	console.log(data);
        	
        	var limit = Number($("#limit").val());
        	limit += 1;

        	$("#limit").val(limit);
        	
            fn_selectCommentList();
        },
        error: function(error){
        	alert("실패 : " + error);  
        }
    });
}

// 더보기 기능
function fn_moreView() {
	
	/* var TSCL = '<c:out value='${totalSelectCommentList }'/>'; */
	var TSCL = ${totalSelectCommentList };
	var limit = Number($("#limit").val());
	limit += 5;
	
	$("#limit").val(limit);
	
	fn_selectCommentList();
	
	if (limit >= TSCL) {
		$("#moreButton").hide();
	}

}

function fn_likeView() {
	
	 $.ajax({
	        url: "likeView.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: $('#likeView').serialize(),
	        success: function(data){
	        	console.log(data);
	        	
	        	var testHtml = "";
	        	
	        	testHtml += "<form id='cancleLikeView' method='post'>"
	        	testHtml += "<span><input type='button' value='좋아요 취소 "+ data +"' name='likeCnt' onclick='fn_cancleLikeView();' style='padding: 10px 20px; background-color: red; color: white; font-weight: bold;'></span>"
	        	testHtml += "<input type='hidden' value='${selectView.paymentId }' name='paymentId'>"
	        	testHtml += "<input type='hidden' value='${user.id }' name='id'>"
	        	testHtml += "</form>"
				
	        	$("#like_Cnt").html(data);
	        	$("#boardViewLike").html(data);
	        	
	        	var like_cnt = document.getElementById("like_cnt").value;
	        	alert(like_cnt);
	        	
	            
	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
	 

}

function fn_cancleLikeView() {
	
//	var result = confirm('취소 하시겠습니까?');
//	if (result == true) {
		
		$.ajax({
	        url: "cancleLikeView.do",
	        type: "POST",
	        async: false,
	        dataType: 'json',
	        data: $('#cancleLikeView').serialize(),
	        success: function(data){
	        	console.log(data);
				
	        	var testHtml = "";
	        	
	        	alert(data);
	        	testHtml += "<form id='likeView' method='post'>"
	        	testHtml += "<span><input type='button' value='좋아요 " + data +"' name='likeCnt' onclick='fn_likeView();' style='padding: 10px 20px; background-color: white; font-weight: bold;'></span>"
	        	testHtml += "<input type='hidden' value='${selectView.paymentId }' name='paymentId'>"
	        	testHtml += "<input type='hidden' valu1231e='${user.id }' name='id'>"
	        	testHtml += "</form>"

	        	$("#like_Cnt").html(data);
	        	$("#boardViewLike").html(data);
	        	
	        	var like_cnt = document.getElementById("like_cnt").value;
	        	alert(like_cnt);
	        	
//	        	alert("좋아요 취소 완료!");
//	        	location.reload();
	        },
	        error: function(error){
	        	alert("실패 : " + error);  
	        }
	    });
//	}

}


</script>

<body>
<div id="layout1">
	<div id="container1">
		<table>
			<tr>
				<th>고객아이디</th>
				<th>번호</th>
				<th>좋아요</th>
				<th>조회수</th>
			</tr>
			<tr>
				<td width="20%">${selectView.customerId }</td>
				<td width="40%">${selectView.paymentId }</td>
				<td width="20%" id="boardViewLike">${selectView.likeCnt }</td>
				<td width="20%">${selectView.viewCnt }</td>
			</tr>
		</table>
	</div>
</div>

<div id="layout2">
	<div id="container2">
		<form id="insertCommentFirst" method="post">
			<table>
				<tr>
					<th style="width: 15%;">${user.id }</th>
					<td style="width: 65%;"><input type="text" id="commentArea" value="" name="comment" placeholder="댓글을 입력하세요" style="width: 100%; border: none; border-left: 1px solid #ccc; padding: 10px;"></td>
					<td style="width: 20%;"><input type="button" value="작성" id="insertClick" style="width: 100%; padding: 10px; cursor: pointer;"></td>
					<td style="display: none;"><input type="hidden" value="${selectView.paymentId }" name="paymentId"></td>
					<td style="display: none;"><input type="hidden" value="${user.id }" name="id"></td>
					<td style="display: none;"><input type="hidden" id="limit" value="5" name="limit"></td>
				</tr>
			</table>
		</form>
		<c:if test="${checkLike == 1 }">
			<div id="cancleLikeArea" style="display: block; margin-top: 50px; margin-left: 480px;">
				<form id="cancleLikeView" method="post">
					<span><input type="button" value="좋아요 취소 ${likeCntTot.likeCnt }" name="likeCnt" id="like_Cnt" onclick="fn_cancleLikeView();" style="padding: 10px 20px; background-color: red; color: white; font-weight: bold; cursor: pointer;"></span>
					<input type="hidden" value="${selectView.paymentId }" name="paymentId">
					<input type="hidden" value="${user.id }" name="id">
				</form>
			</div>
		</c:if>
		<c:if test="${checkLike == 0 }">
			<div id="likeArea" style="display: block; margin-top: 50px; margin-left: 480px;">
				<form id="likeView" method="post">
					<span><input type="button" value="좋아요 ${likeCntTot.likeCnt }" name="likeCnt" id="like_Cnt" onclick="fn_likeView();" style="padding: 10px 20px; background-color: white; font-weight: bold; cursor: pointer;"></span>
					<input type="hidden" value="${selectView.paymentId }" name="paymentId">
					<input type="hidden" value="${user.id }" name="id">
				</form>
			</div>
		</c:if>
	</div>	
</div>


<!-- 댓글 공간 -->
<div id="layout3">
	<div id="container3">
		<!-- selectCommentList ajax 댓글 공간 -->
	</div>
</div>
<!-- 더보기 -->
<div id="layout4">
	<div id="container4">
		<input type="button" value="더보기" onclick="fn_moreView()" id="moreButton">
	</div>
</div>	
<!-- 목록가기 -->
<div id="layout5">
	<div id="container5">
		<input type="button" value="목록가기" onclick="javascript:location.href='selectBoardList.do'" id="listGo">
	</div>
</div>

</body>
</html>