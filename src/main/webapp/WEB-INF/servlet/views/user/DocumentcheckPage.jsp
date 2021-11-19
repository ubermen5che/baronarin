<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>

<head>
	<title>Baronarin(Beta)</title>
	<meta charset="UTF-8">
	<meta name="copyright" content="Copyright (c) 2021 VANE company All rights reserved">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/style.css?ver=1">
	<link rel="stylesheet" type="text/css" href="/css/style_documentpage.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

	<link rel="stylesheet" type="text/css" href="/css/style.css?ver=5">
	<link rel="stylesheet" type="text/css" href="/css/style_signpage.css?ver=4">
	<link rel="stylesheet" type="text/css" href="/css/style_stamppage.css?ver=4">

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-promise/4.1.1/es6-promise.auto.js"></script>
	<script src="//mozilla.github.io/pdf.js/build/pdf.js"></script>

	<style>
		.button {
			outline: none;
			text-align: center;
			font: normal normal normal 10pt/16pt Noto Sans CJK KR;
			letter-spacing: -0.3px;
			font-size: 10pt;
			color: #FFFFFF;
			border: 4px;
			padding-top: 8px;
			padding-bottom: 8px;
			padding-left: 20px;
			padding-right: 20px;
			background: #245ae3 0% 0% no-repeat padding-box;
			border-radius: 50px;
			cursor: pointer;
			margin: 2px;
		}
	</style>
</head>

<body>
<div id="Page" style="width: 100%; height:100%">
	<div id="Controll_Layer" style="display: flex;justify-content: space-between;align-items: center;">
		<a href="/">
			<!-- 로고-->
			<img src="/img/google.png" style="padding-left:315x;height:80px" />
		</a>
		<div id="button_layer" style="padding-bottom:10px;">
			쉽고 빠른 전자계약, 바로날인
		</div>
	</div>
	<div id="black_div" style="display:none; position:fixed; left:0px; top:0px; background:rgba(28, 31, 38, 0.5); z-index:99; width:100%; height:100%;"></div>
	<div id="black_information_div" style="display:none;"></div>
	<script>
		document.getElementById("black_information_div").addEventListener("mousedown", function () {
			document.getElementById("black_information_div").style.display = "none";
		});
	</script>

	<div id="Root" style="height:200%">
		<div id="controller_background_gray">
			<div id="remote_2">
				<br>
				<input type="hidden" id="person_num_count" value="${person_num}">

				<script type="text/javascript">
					function getNum(a) {
						Num = a;
					}

					function show_init() {
						document.getElementById("back_View").style.display = "block";
						if (Num == 1) {
							document.getElementById("first_Sign_View").style.display = "block";
						}
						else if (Num == 2) {
							document.getElementById("first_Sign_View").style.display = "block";
							document.getElementById("second_Sign_View").style.display = "block";
						}
						else if (Num == 3) {
							document.getElementById("first_Sign_View").style.display = "block";
							document.getElementById("second_Sign_View").style.display = "block";
							document.getElementById("third_Sign_View").style.display = "block";
						}
						document.getElementById("other_View").style.display = "block";
					}
					getNum(document.getElementById("person_num_count").value);
				</script>

				<br>
				<div id="back_View" style="width:250px;height:313px;padding-top:20px;padding-bottom:20px;padding-left:20px;margin-left:20px;text-align: center;display:none;background-image:url(/img/서명확인배경.svg);background-size:auto;background-repeat:no-repeat;background-position:center center">
					<div id="first_Sign_View" style="display:none;text-align:center;background-image:url(/img/서명확인뒷배경.png);margin-right:20px;background-repeat:no-repeat;background-position:center center;">
						<div style="font: normal normal bold 15px/17px Noto Sans KR;margin-top:3px;padding-top:8px;padding-bottom:10px;">
							<p>이름 :${person1_name}<p>
							<span style="display:none" id="per1_ok">승인 여부 :<span style="color: blue">승인</span></span>
							<span style="display:none" id="per1_no">승인 여부 :<span style="color: red">미승인</span></span>
							<input type=hidden value="${person1_issign}" id="query_per1_sign">
							<input type=hidden value="${person1_name}" id="query_per1_name">
							<br>
						</div>
					</div>

					<div id="second_Sign_View" style="display:none;text-align:center;background-image:url(/img/서명확인뒷배경.png);margin-right:20px;background-repeat:no-repeat;background-position:center center;">
						<div style="font: normal normal bold 15px/17px Noto Sans KR;margin-top:3px;padding-top:8px;padding-bottom:10px;">
							<p>이름 :${person2_name}<p>
							<span style="display:none" id="per2_ok">승인 여부 :<span style="color: blue">승인</span></span>
							<span style="display:none" id="per2_no">승인 여부 :<span style="color: red">미승인</span></span>
							<input type=hidden value="${person2_issign}" id="query_per2_sign">
							<input type=hidden value="${person2_name}" id="query_per2_name">
							<br>
						</div>
					</div>

					<div id="third_Sign_View" style="display:none;text-align:center;background-image:url(/img/서명확인뒷배경.png);margin-right:20px;background-repeat:no-repeat;background-position:center center;">
						<div style="font: normal normal bold 15px/17px Noto Sans KR;margin-top:3px;padding-top:8px;padding-bottom:10px;">
							<p>이름 :${person3_name}<p>
							<span style="display:none" id="per3_ok">승인 여부 :<span style="color: blue">승인</span></span>
							<span style="display:none" id="per3_no">승인 여부 :<span style="color: red">미승인</span></span>
							<input type=hidden value="${person3_issign}" id="query_per3_sign">
							<input type=hidden value="${person3_name}" id="query_per3_name">
							<br>
						</div>
					</div>
				</div>

				<script>
					window.onload = function () {
						var a1 = $('#query_per1_sign').val();
						var a2 = $('#query_per2_sign').val();
						var a3 = $('#query_per3_sign').val();

						if (a1 == '1') {
							document.getElementById('per1_ok').style.display = "block";
						}
						else {
							document.getElementById('per1_no').style.display = "block";
						}

						if (a2 == '1') {
							document.getElementById('per2_ok').style.display = "block"
						}
						else {
							document.getElementById('per2_no').style.display = "block";
						}

						if (a3 == '1') {
							document.getElementById('per3_ok').style.display = "block";
						}
						else {
							document.getElementById('per3_no').style.display = "block";
						}
					}
				</script>

				<%--				승인하기 + 문서 페이지 이동 버튼--%>
				<div id="other_View" style="display:none">
					<p style="font: normal normal bold 15px/15px Noto Sans KR;">페이지 이동</p>
					<input type="button" value="<" onclick="prevImg()" class="button" />
					<input type="button" value=">" onclick="nextImg()" class="button" /><br>
					<br><br><br><br><br><br><br><br><br><br>
					<div style="display:flex;text-align: center;justify-content: center;">
						<input id="btnout" type="button" onclick="location.href='/'" value="취소" class="button" style="font-size:20px;margin-right:30px;">
						<form action="/user/sign" method="post" id="signSub">
							<input id="btnin" type="button" value="승인하기" onclick="to_sign()" class="button" style="font-size:20px;">
							<input type="hidden" value="${paper_name}" name="title">
							<input type="hidden" value="${create_date}" name="create_time">

							<script>
								function to_sign() {
									$.ajax({
										async:true,
										type:"get",
										dataType:"json",
										url: "/user/checksign",
										success: function(result) {
											if(result==-1) {
												alert('서명파일이 존재하지 않습니다.\n서명을 생성해 주십시오.');
											}
											else {
												alert('사인 하였습니다.');
												$('#signSub').submit();
											}
										},
										error: function() {
											alert("error")
										}
									});
								}
							</script>
						</form>
					</div>
				</div>

				<script type="text/javascript">
					function del_add_img_layer() {
						for (var i = 0; i < add_file; i++) {
							var test = document.getElementById(i + "add_img");
							test.remove();
							test = document.getElementById(i + "img");
							test.remove();
						}
						add_file = 0;
					}
					//button을 누르면 nextImg
					function nextImg() {
						del_add_img_layer();

						console.log(img_source);

						cur++;
						console.log(cur);
						if (cur >= max_index)
							cur = 0;

						var ctx0 = document.getElementById("first").getContext("2d");
						var img = new Image();
						img.src = img_source[cur].src;
						console.log(img.src);
						img.onload = function () {
							ctx0.drawImage(img, 0, 0, ctx0.canvas.width, ctx0.canvas.height);
						}
					}

					function prevImg() {
						//모든 도장 삭제
						del_add_img_layer();
						console.log(img_source);
						cur--;
						console.log(cur);
						if (cur <= -1)
							cur = max_index - 1;
						var ctx0 = document.getElementById("first").getContext("2d");
						var img = new Image();
						img.src = img_source[cur].src;
						console.log(img.src);
						img.onload = function () {
							ctx0.drawImage(img, 0, 0, ctx0.canvas.width, ctx0.canvas.height);
						}
					}
				</script>
			</div>

			<div id="controller_background_white">
				<div id="capture_layer">
					<p name="title" style="margin-left:30px;font: normal normal bold 25px/15px Noto Sans KR;font-size:30px;text-align: center">
					<div style="display:flex; justify-content: center;" >
						<a id="serpdf" class="btn7" target="_blank" href="<spring:url value = '/pdfview/${file_serverName}'/>" style="padding:2%;width: auto; font: normal normal bold 25px/35px Noto Sans KR;font-size:30px;text-align: center;display:none" >${orig_Name}</a>
						<img src="<spring:url value='/pdfview/${file_serverName}'/>" style="margin-top:20px;width:800px;display:none" id="serimage">
					</div>
					<input type="hidden" value="${is_pdf}" id="ispdf">
					<script>
						var pdf = document.getElementById('ispdf').value;
						if(pdf=='') {
							document.getElementById('serimage').style.display= "block";
						}
						else {
							document.getElementById('serpdf').style.display= "block";
						}
					</script>
					</p>

					<div id="controller" style="width:793px; height:1122px;">
						<canvas id="first" width="793px" height="1122px"></canvas>
						<script type="text/javascript">
							//first의 canvas 설정
							var $canvas = document.querySelector('#first');
							$canvas.globalCompositeOperation = "source-over"; // 덮어 씌우기 가능
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="download_div"
	 style="position:absolute; z-index : 1; top:0; left:50vw; transform: translate(-50%, 20%); ">
	<canvas id="download_test" width="793px" height="1122px"></canvas>
</div>
<script>
</script>

</div><!-- Page -->

<script>
	document.getElementById("download_div").style.visibility = "hidden";
	show_init();
</script>

<div id="add"></div>
</div>
</body>

</html>