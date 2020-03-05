<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>

</head>
<body>
<h2>회원가입</h2>

<form method="post" id="smsForm">
<label>연락처</label>
<input type="text" name="to" id="text1" placeholder=" 전화번호 입력 " 
      required="required" value="${to}"/>
      <input type="hidden" name="text" id="text2" > 
     
      
      <input type="button" name="sendSMS" id="sendSMS" value="인증번호 요청" /><br>

      <label>인증번호 확인:</label>
<input type="text" name="numcheck" id="numcheck" required="required" placeholder="인증번호 입력" onkeyup="smscheckfunction()"><br>
<div class="alert alert-success" id="alert-success2">인증번호가 일치합니다.</div>
<div class="alert alert-danger" id="alert-danger2">인증번호가 일치하지않습니다.</div>

 <input type="hidden" name="${_csrf.parameterName}"
    value="${_csrf.token}" />
</form>
<form method="post" action="/joinresult">
<input type="hidden" name="phone" value="${to }"/>
<label>아이디</label>
<input type="text" name="id" id="id" required="required" placeholder="아이디">
<button type="button" class="btn btn-primary" id="multicheck">중복확인</button><br>
<div id="checkMsg"></div>

<label>비밀번호</label>
<input type="password" name="pwd" id="pwd" required="required" placeholder="비밀번호"><br>
<label>비밀번호확인</label>
<input type="password" name="pwdcheck" id="pwdcheck" required="required" placeholder="비밀번호확인"
onkeyup="passwordCheckFunction();"
><br>
<div class="alert alert-success" id="alert-success">비밀번호가
						일치합니다.</div>
					<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지
						않습니다.</div>

<label>이름</label>
<input type="text" name="name" id="name" placeholder="이름"><br>
<label>생년월일</label>
<input type="date" name="birth" id="birth" ><br>
<%-- <label>연락처</label>
<input type="text" name="to" id="text1" placeholder=" 전화번호 입력 " 
      required="required" value="${to}"/>
      <input type="hidden" name="text" id="text2" > 
     
      
      <input type="button" name="sendSMS" id="sendSMS" value="인증번호 요청" /><br>

      <label>인증번호 확인:</label>
<input type="text" name="numcheck" id="numcheck" required="required" placeholder="인증번호 입력" onkeyup="smscheckfunction()"><br>
<div class="alert alert-success" id="alert-success2">인증번호가 일치합니다.</div>
<div class="alert alert-danger" id="alert-danger2">인증번호가 일치하지않습니다.</div> --%>
 
 <label>e-mail</label>
<input type="email" name="email" id="email" placeholder="example@email.com"><br>
 
<label for='addr'>주소</label>
					<div class='form-row'>
						<div class='col-5'>
							<input type="text" id="sample4_postcode" name='zipcode'
								placeholder="우편번호" class='form-control'>
						</div>
						<input type="button" onclick="sample4_execDaumPostcode()"
							value="우편번호 찾기" readonly="readonly" class='btn btn-primary'><br>
					</div>
					<div class='juso'>
						<input type="text" id="sample4_roadAddress" placeholder="도로명주소"
							name="roadaddr" readonly="readonly" class="form-control">
						<input type="radio" id='roadAddress' name='addrselect' value="0"
							required><label for="addrselect">도로명주소 선택</label> <br>

						<input type="text" id="sample4_jibunAddress" placeholder="지번주소"
							name="jibunaddr" readonly="readonly" class="form-control">
						<input type="radio" id='jibunAddress' name='addrselect' value="1"><label
							for="addrselect">지번주소 선택</label><br>
					</div>
					<span id="guide" style="color: #999"></span> <label
						for="detailaddr">상세주소</label> <input type="text" id="detailaddr"
						name="detailaddr" class="form-control"> 




<!-- <input type="text" name="zipcode" placeholder="도로명주소"><br>
<input type="text" name="zipcode" placeholder="상세주소"><br> -->



<label>닉네일</label>
<input type="text" name="nick" placeholder="닉네임"><br>
<button type="submit" id="submit" class="btn btn-primary">회원가입</button><br>
<!-- 시큐리티 토큰 -->
    <input type="hidden" name="${_csrf.parameterName}"
    value="${_csrf.token}" />
</form>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

	<script type="text/javascript" src="resources/JS/yi_findAddr.js"></script>

<script>
$(function(){
	$("#alert-success").hide();
	$("#alert-danger").hide();
	$("input").keyup(function() {
		var pwd1 = $("#pwd").val();
		var pwd2 = $("#pwdcheck").val();
		if (pwd1 != "" || pwd2 != "") {
			if (pwd1 == pwd2) {
				$("#alert-success").show();
				$("#alert-danger").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alert-success").hide();
				$("#alert-danger").show();
				$("#submit").attr("disabled", "disabled");
			}
		}
	});

	$("input")

});

$('#multicheck').click(function() {
	var csrfHeaderName ="${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}"; 
	
	if($("#id").val() == ""){
		alert("아이디를 입력하세요");
		return;
	}
	
	$.ajax({
		
		
		type :'POST',
		url : 'idCheck',
		data : {"id" : $('#id').val()},
		beforeSend : function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		},
		success : function(data) {
			console.log("data : "+ data);
			if(data == 0){
				$('#checkMsg').html('<p style="color:blue">사용가능</p>');
				$("#submit").removeAttr("disabled");
        	}
        	else{
            	$('#checkMsg').html('<p style="color:red">사용불가능</p>');
            	$("#submit").attr("disabled", "disabled");
            	$('#id').val('');
                $('#id').focus();
        	}
    	},
		error : function(data) {
			console.log(data);
			console.log("ajax error");
		}
	});
});


</script>	
	
	
 
<script>
/* 인증번호 */
	$("#alert-success2").hide();
	$("#alert-danger2").hide();
	
    $("#sendSMS").on('click',function(){
       
        var result = Math.floor(Math.random() * 1000000)+100000;
        if(result>1000000){
           result = result - 100000;
        }
        
      document.getElementById('text2').value= null;
       document.getElementById('text2').value = result;
       var result2 = document.getElementById('text2').value;
      console.log("hidden : "+ result2);
      
        console.log("문자를 전송합니다.");
         console.log(" 인증번호:"+result);
         $("#smsForm").attr("action",  "join"); //위에 있는 폼태그를 컨트롤러로 전송한다.
         $("#smsForm").submit();  
       
       
    });
        
 
     </script>
  <script>

/* 	$( */function smscheckfunction() {
			$("#alert-success2").hide();
			$("#alert-danger2").hide();
			$("#numcheck").keyup(function() {
				var authnum =${auth_num}; 
				console.log("authnum:"+authnum)
				var numcheck = $("#numcheck").val();
				console.log("numcheck:",numcheck);
				if (authnum != "" || numcheck != "") {
					if (authnum == numcheck) {
						$("#alert-success2").show();
						$("#alert-danger2").hide();
						$("#submit").removeAttr("disabled");
					} else {
						$("#alert-success2").hide();
						$("#alert-danger2").show();
						$("#submit").attr("disabled", "disabled");
						}
				}
			});


		}/* );  */
	
	
	
	</script>


<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>
