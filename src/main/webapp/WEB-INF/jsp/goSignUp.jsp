<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>	
* {
	text-decoration: none;
	list-style: none;
	color: black;
	margin: 0px;
	padding: 0px;
}

#layout1 {
	width: 100%;
}

#container1 {
	width: 1000px;
	margin: auto;
	text-align: center;
	margin-top: 350px;
	display: block;
}

#container1 form table {
	margin: auto;
	width: 450px;
}

#container1 form table tr th {
	padding-top: 5px;
	padding-right: 20px;
}
</style>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

// 비밀번호 체크
var pwdCheck2 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
// 중복체크
var idck = 0;
//전화번호 하이픈 생성
const autoHyphen2 = (target) => {
	target.value = target.value
	.replace(/[^0-9]/g, '')
	.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

	function id_idck() {		
	 
	   var id =  $("#id").val(); 
	   
	   $.ajax({
	          async: true,
	          type : 'POST',
	          data : id,
	          url : "idcheck.do",
	          dataType : "json",
	          contentType: "application/json; charset=UTF-8",
	          success : function(data) {
	              if (data > 0) {          
	                  alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
	                  $('#th_html0').html("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
					  $("#th_html0").css('font-size', '8px');
					  $('#th_html0').css('color', '#d32f2f');
					  $("#id").focus();
	                  
	              } else {
	                  alert("사용가능한 아이디입니다.");  
	                  $('#th_html0').html("사용가능한 아이디입니다.");
					  $("#th_html0").css('font-size', '8px');
					  $('#th_html0').css('color', '#4caf50');
					  $("#id").focus();
	                  
	                  idck = 1; 

	              }
	          },
	          error : function(error) {
	              alert("error : " + error);
	          }
	      });
	}

	// 클릭 시 글씨 삭제
	function a(y){
		   if (y.defaultValue==y.value) {
		      y.value = "";
		   }
		}


	//joinform_check 함수로 유효성 검사
	function joinform_check() {
		//변수에 담아주기
		var id = document.getElementById("id");
		var pwd = document.getElementById("password");
		var repwd = document.getElementById("repassword");
		var uname = document.getElementById("name");
		var mobile = document.getElementById("phone");
		var email = document.getElementById("email");//메일
		var agree = document.getElementById("agree");
		var RegExp = /^[a-zA-Z0-9]{4,12}$/; //id와 pwassword 유효성 검사 정규식
		var n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식
		// 이메일 유효성 검사
		var e_RegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
	  if(!RegExp.test(id.value)){ //아이디 유효성검사
          alert("ID는 4~12자의 영문 대소문자와 숫자로만 입력하여 주세요.");        
          return false;
      }

	  if (id.value == "") { //해당 입력값이 없을 경우 같은말: if(!uid.value)
	    alert("아이디를 입력하세요.");
	    id.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
	    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
	  };

	  if (pwd.value == "") {
	    alert("비밀번호를 입력하세요.");
	    pwd.focus();
	    return false;
	  };

	  //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
	  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

	  if (!pwdCheck.test(pwd.value)) {
	    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
	    pwd.focus();
	    pwd.value = "";
	    return false;
	  };

	  if (repwd.value != pwd.value) {
	    alert("비밀번호가 일치하지 않습니다..");
	    repwd.focus();
	    repwd.value = "";
	    return false;
	  };
	
	  
	  if(pwd.value==id.value){
          alert("Password는 ID와 동일하면 안됩니다.");
          return false;
      }
	  
		
	  if (uname.value == "") {
	    alert("이름을 입력하세요.");
	    uname.focus();
	    return false;
	  };
	  
	  if(!n_RegExp.test(uname.value)){
          alert("이름은 특수문자,영어,숫자는 사용할 수 없습니다. 한글만 입력하여주세요.");
          return false;
      }


	  var reg = /^[0-9]+/g; //숫자만 입력하는 정규식

	  if (mobile.value == "") {
	    alert("전화번호를 입력해주세요.");
	    mobile.focus();
	    return false;
	  }
	  
	  if (mobile.value.length != 13) {
		  alert("전화번호는 13자리를 입력해주세요.");
		    mobile.focus();
		    mobile.value = "";
		    return false;
	  }
	  
	  if(email.value == ''){ // 이메일 입력여부 검사
          alert("이메일을 입력해주세요.");
          email.focus();
          email.value = "";
          return false;
      }
      
      if(!e_RegExp.test(email.value)){ //이메일 유효성 검사
          alert("올바른 이메일 형식이 아닙니다.");
          email.focus();
          email.value = "";
          return false;
      }

	  if (!agree.checked) { //체크박스 미체크시
	    alert("약관 동의를 체크하세요.");
	    agree.focus();
	    return false;
	  }
	  
	  if(idck==0){
          alert('아이디 중복체크를 해주세요');
          return false;
      }

	  alert("회원가입 성공!");
	  //입력 값 전송
	  document.join_form.submit(); //유효성 검사의 포인트 
	 
	  
	}
	
	$(document).ready(function(){
		
		$('#id').on('click', function(){
			$('#th_html0').html('ID는 4~12자의 영문 대소문자와 숫자로만<br>입력하여 주세요.');
			$("#th_html0").css('font-size', '10px');
			$("#th_html0").css('color', 'gray');
			$("#th_html3").html('');
			$("#th_html4").html('');
			$("#th_html5").html('');
		});
		
		$('#name').on('click', function(){
			$('#th_html3').html('이름은 특수문자,영어,숫자는 사용할 수<br>없습니다. 한글만 입력하여주세요.');
			$("#th_html3").css('font-size', '10px');
			$("#th_html3").css('color', 'gray');
			$("#th_html4").html('');
			$("#th_html5").html('');
		});
		
		$('#phone').on('click', function(){
			$('#th_html4').html('전화번호는 13자리를 입력해주세요.');
			$("#th_html4").css('font-size', '10px');
			$("#th_html4").css('color', 'gray');
			$("#th_html3").html('');
			$("#th_html5").html('');			
		});
		
		$('#email').on('click', function(){
			$('#th_html5').html('올바른 이메일 형식으로 작성해주세요.');
			$("#th_html5").css('font-size', '10px');
			$("#th_html5").css('color', 'gray');
			$("#th_html3").html('');
			$("#th_html4").html('');
		});
		
		
	});

	$(function(){
		
		 $('#password').keyup(function(){		
				if (pwdCheck2.test($('#password').val())) {
					$('#th_html1').html('사용 가능한 비밀번호입니다!');
					$("#th_html1").css('font-size', '8px');
				 	$('#th_html1').css('color', '#4caf50');
				 } else {
					$('#th_html1').html('비밀번호는 영문자+숫자+특수문자조합으로 8~25자리 사용해야 합니다.');
				    $("#th_html1").css('font-size', '8px');
					$("#th_html1").css('color', '#d32f2f');
				 }
		    });

	    $('#repassword').keyup(function(){
	        if($('#password').val() != $('#repassword').val()){
	          $('#th_html2').html('비밀번호가 일치하지 않습니다.');
	          $('#th_html2').css('color', '#d32f2f');
	          $('#th_html2').css('text-align', 'left');
	          $("#th_html2").css('font-size', '11px');
	        } else{
	          $('#th_html2').html('비밀번호가 일치합니다!');
	          $('#th_html2').css('color', '#4caf50');
	          $('#th_html2').css('text-align', 'left');
	          $("#th_html2").css('font-size', '11px');
	        }
	    });
	});
	

	

</script>
</head>
<body>
	<div id="layout1">
		<div id="container1">
			<form name="join_form" action="signUp.do" method="post">
				<table
					style="display: block; border: 1px solid black; padding: 25px; border-radius: 10px;">
					<tr>
						<th>아이디</th>
						<th><input type="text" name="id" id="id" placeholder="아이디"></th>
						<th><input type="button" value="중복체크" id="idck" onclick="id_idck()" style="margin-left: 15px;"></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html0"></th>
					</tr>
					<tr>
						<th>비밀번호</th>
						<th><input type="password" name="password" id="password" placeholder="비밀번호"></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html1"></th>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<th><input type="password" name="repassword" id="repassword" placeholder="비밀번호 확인"></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html2"></th>
					</tr>
					<tr>
						<th>이름</th>
						<th><input type="text" name="name" id="name"></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html3"></th>
					</tr>
					<tr>
						<th>전화번호</th>
						<th><input type="text" name="phone" id="phone"
							oninput="autoHyphen2(this)" maxlength="13"
							placeholder="전화번호를 입력하세요."></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html4"></th>
					</tr>
					<tr>
						<th>이메일</th>
						<th><input type="text" name="email" id="email"></th>
					</tr>
					<tr>
						<th><div style="display: none;"></div></th>
						<th id="th_html5"></th>
					</tr>
					<tr>
						<th>약관동의</th>
						<th><input type="checkbox" value="Y" name="agree" id="agree">약관에
							동의합니다.</th>
					</tr>
					<tr>
						<th><button type="button" onclick="history.back();">이전페이지로</button></th>
						<th><button type="button" onclick="joinform_check();">가입하기</button></th>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>