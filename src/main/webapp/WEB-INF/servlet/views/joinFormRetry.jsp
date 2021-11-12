<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/css/webHome.css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="stylesheet" type="text/css" href="/css/style_login2.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css?ver=1">
	<link rel="stylesheet" type="text/css" href="/css/style_signpage.css?ver=4">
	<link rel="stylesheet" type="text/css" href="/css/style_stamppage.css?ver=4">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap" rel="stylesheet">
	<title>회원가입테스트</title>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>


<body>
<div id="Page" style="width: 100%;">
	<div id = "Controll_Layer">
		<nav class="navbar" style="display: flex;justify-content: space-between;align-items: center;padding: 8px 12px;max-height:80px;
			min-height:80px;">
			<a href="/"><img src = "img/google.png" style="height:80px"/></a>
			<ul class="button_layer" style="display: flex;list-style : none; padding-left : 0;">
				<li> <a onclick="location.href='/board'" class="btn5" style="cursor: pointer"> 자료실 </a> </li>
				<li> <a onclick="location.href='/customerCenter'" class="btn5" style="cursor: pointer"> 고객센터 </a> </li>
				<sec:authorize access="isAnonymous()">
					<li> <a  href="/joinForm" class="btn7" style="cursor: pointer; text-decoration:none"> 회원가입 </a> </li>
					<li> <a href="/loginForm" class="btn6" style="cursor: pointer; text-decoration:none"> 로그인 </a> </li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li style="margin-left:14px; margin-right:6px;"><sec:authentication property="principal.realname"/>님</li>
					<li><form action="/logout" method="POST">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<a class="btn6"><button type="submit" class="btn6_sub" style="cursor: pointer; text-decoration:none;">로그아웃</button>
						</a></form></li>
				</sec:authorize>
			</ul>
		</nav>
	</div>
</div>

<img src="/img/bi)바로날인beta_KR.png" style="position: absolute;top: 186px;left: 830px;width: 260px;height: 111px;"/>

<div style="position: absolute;top: 356px;left: 761px">
	<input type="text"  name="email" placeholder="아이디" id="email" onchange="check_id()" style="width: 398.00px;height: 60.00px;" class="sign_up_input"><br/>
	<p style="display:none; color: red;" id="idPoss">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사용 가능한 아이디입니다.
	</p>
	<p style="display:none; color: red;" id="idImposs">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;해당 아이디는 이미 사용 중 입니다.
	</p>
	<p style="display:none; color: red;" id="idNeqEmail">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이메일을 아이디로 입력하십시오.
	</p>
	<p style="display:none; color: red;" id="idEmpty">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;아이디에는 공백이 들어갈 수 없습니다.
	</p>
</div>

<div style="position: absolute; display: flex; justify-content: space-between; align-items: center;top: 448px;left: 761px">
	<input type="text"  name="realName" placeholder="이름" id="realName" class="sign_up_input"  style="width: 398.00px;height: 60.00px;"><br/>
</div>
<input type="hidden" id="result" value="${realName}">
<input type="hidden" id="result2" value="${checkSuccess}">

<div style="position: absolute;top: 540px;left: 761px">
	<input type="password" name="password"  placeholder="비밀번호" id="password" class="sign_up_input"  style="width: 398.00px;height: 60.00px;">
</div>
<div style="position: absolute;top: 632px;left: 761px">
	<input type="password" name="checkpw" placeholder="비밀번호 확인" id="pass2" class="sign_up_input"  style="width: 398.00px;height: 60.00px;">
</div>

<div style="position: absolute;top: 704px;left: 761px">
	<button onclick="ChkTelBtn()" id="ChkTelBtn" style="width: 398.00px;height: 60.00px;">check phone number</button>
</div>

<div style="position: absolute;top: 784px;left: 810px">
	<input type="checkbox" style='zoom:2.0;' id="sign_check">
	<font style="font: normal normal normal 14px/26px Noto Sans KR;padding-bottom:2px;padding-left:8px">개인정보 수집 및 이용약관에 동의합니다.</font>
</div>

<input type="button" id="button" value="저장" onclick="submit_form()" class="btn2" style="position: absolute;top: 840px;left: 880px;width:172px;height:54px;">

<br/>
</body>


<script>
	var isPhoneNumberAuthenticated = false; // 휴대폰 인증을 거쳤는지 여부

	function ChkTelBtn(){
		console.log('ChkTelBtn Clicked');
		window.open("/ChkTelPopUpPage","ChkTelPopUpPage","width=400, height=460,left=30,top=30");
	}

	function getPhoneNumberFromFBpopUp(phoneNumber) {
		// firebase 팝업창으로부터 휴대폰 번호를 받아온다.
		phoneNumber = phoneNumber.substring(3); //+82 국가코드 잘라내기
		if(phoneNumber == '1122223333'){
			//테스트용 전화번호 코드
		} else {
			// 국제 전화번호 규칙상 +82 010 1234 5678은 앞의 0을 잘라낸 모양인 +821012345678로 표기된다.
			// 사라진 0을 붙여주는 작업
			phoneNumber = '0' + phoneNumber;
		}
		$.ajax({
			//휴대폰 번호 중복검사
			type: 'POST',
			data: {"phoneNumber" : phoneNumber},
			dataType: "json",
			url: "/phoneNumberCheck",
			success: function (res) {
				if (res['res'] == true) {
					// 중복이 아닌 경우 params에 바로 넣는다.
					params.phoneNumber = phoneNumber;
					console.log('휴대폰 번호 중복검사 완료 : '+phoneNumber);
					isPhoneNumberAuthenticated = true;
				} else {
					alert("이미 가입된 전화번호입니다.");
					console.log('휴대폰 번호 중복됨')
				}
			},
			error: function (error) {
				alert("오류발생:" + error);
			}
		});
	}

	function check_id() {
		var memberId = $("#email").val();

		if(memberId.search(/\s/) != -1) {
			document.getElementById('idPoss').style.display= "none";
			document.getElementById('idImposs').style.display= "none";
			document.getElementById('idNeqEmail').style.display= "none";
			document.getElementById('idEmpty').style.display= "block";
		} else {
			if(memberId.trim().length != 0) {
				$.ajax({
					async : true,
					type : 'POST',
					data: memberId,
					url: "/idCheck",
					dataType: "json",
					contentType: "application/json; charset=UTF-8",
					success: function(count) {
						if(count == -1) {
							document.getElementById('idPoss').style.display= "none";
							document.getElementById('idImposs').style.display= "none";
							document.getElementById('idNeqEmail').style.display= "block";
							document.getElementById('idEmpty').style.display= "none";
							$('#result2').val("Fail");
						} else if(count == 1) {
							document.getElementById('idPoss').style.display= "none";
							document.getElementById('idImposs').style.display= "block";
							document.getElementById('idNeqEmail').style.display= "none";
							document.getElementById('idEmpty').style.display= "none";
							$('#result2').val("Fail");
						} else if(count == 0){
							document.getElementById('idPoss').style.display = "block";
							document.getElementById('idImposs').style.display= "none";
							document.getElementById('idNeqEmail').style.display= "none";
							document.getElementById('idEmpty').style.display= "none";
							$('#result2').val("Success");
						}
					},
					error: function(error) {
						document.getElementById('idPoss').style.display= "none";
						document.getElementById('idImposs').style.display = "block";
						document.getElementById('idNeqEmail').style.display= "none";
						document.getElementById('idEmpty').style.display= "none";
						$('#result2').val("Fail");
					}
				});
			} else {
				document.getElementById('idPoss').style.display= "none";
				document.getElementById('idImposs').style.display= "none";
				document.getElementById('idNeqEmail').style.display= "none";
				document.getElementById('idEmpty').style.display= "block";
				$('#result2').val("Fail");
			}
		}
	}

	window.onload = function() {
		var result = $('#result').val();
		{
			$('#email').val(result);
		}
	}

	var params={
		email:	"",
		password: "",
		realName : "",
		phoneNumber : ""
	}

	function stubJoinData() {
		params['email'] = $('#email').val();
		params['password'] = $('#password').val();
		params['realName'] = $("#realName").val();
		console.log(params);
	}

	function submit_form() {
		stubJoinData();
		if(isPhoneNumberAuthenticated == false) {
			alert("휴대폰 번호 인증을 완료하여야 합니다.")
			return;
		}
		console.log(params);

		$.ajax({
			async: true,
			type: 'POST',
			data: JSON.stringify(params),
			url: "/join",
			dataType: "json",
			contentType: "application/json; charset=UTF-8",
			success: function (res) {
				if (res['res'] == true) {
					alert("가입된 이메일로 메일이 전송되었습니다. 링크를 통해 인증을 해주시길 바랍니다.");
				} else {
					alert(res);
				}
			},
			error: function (error) {
				alert("오류발생:" + error);
			}
		});
	}
</script>
</html>
