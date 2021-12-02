<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2021/11/01
  Time: 7:43 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="copyright" content="Copyright (c) 2021 VANE company All rights reserved">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/style_myPage.css">
    <link rel="stylesheet" type="text/css" href="/css/style_signpage.css?ver=4">
    <link rel="stylesheet" type="text/css" href="/css/style_stamppage.css?ver=4">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <title>Baronarin(Beta)</title>
</head>
<body>

<div id="Page" style="width: 100%;">
    <div id="Controll_Layer">
        <nav class="navbar" style="display: flex;justify-content: space-between;align-items: center;padding: 8px 12px;max-height:80px;
		min-height:80px;">
            <a href="/">
                <img src="/img/google.png" style="height:80px" />
            </a>

            <ul class="button_layer" style="display: flex;list-style : none; padding-left : 0;">
                <li>
                </li>
                <li> <a onclick="location.href='/board'" class="btn5" style="cursor: pointer"> 자료실
                </a> </li>
                <li> <a onclick="location.href='/customerCenter'" class="btn5" style="cursor: pointer"> 고객센터
                </a> </li>

                <sec:authorize access="isAnonymous()">
                    <script>
                        function announce() {
                            window.open("/popup", "popup", "width=700, height=450,left=30,top=30");
                        }
                        announce();
                    </script>
                    <li> <a href="/joinForm" class="btn7"
                            style="cursor: pointer; text-decoration:none"> 회원가입 </a> </li>
                    <li> <a href="/loginForm" class="btn6"
                            style="cursor: pointer; text-decoration:none"> 로그인 </a> </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li style="margin-left:14px; margin-right:6px;"><a href="/user/myPage" class="btn5" style="cursor: pointer">마이페이지</a></li>
                    <li>
                        <form action="/logout" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                            <a class="btn6"><button type="submit" class="btn6_sub"
                                                    style="cursor: pointer; text-decoration:none;">로그아웃</button>
                            </a>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
        </nav>
    </div>


    <!-- 상단 부 -->
    <div style="position:absolute;top: 102px;left: 320px;text-align: left;font: normal normal bold 48px/64px Noto Sans KR;letter-spacing: -1.2px;color: #191919;opacity: 1;">
        서명 등록</div>
    <div style="position: absolute;width:100px;top: 149px;left: 1280px;text-align: left;font: normal normal bold 15px/23px Noto Sans KR;letter-spacing: -0.38px;color: #245AE3;">
        ${name} <span style="color: #6E6E6E;">님</span>
    </div>
    <div style="position: absolute;top: 147px;left: 1386px;text-align: left;font: normal normal normal 17px/23px Noto Sans KR;letter-spacing: -0.42px;color: #000000;opacity: 1;">
        ${email} </div>
    <div style="position: absolute;top: 182px;left: 320px;">
        <hr style="height: 5px;width: 1280px;border-radius: 10px; background-color: #245AE3;">
    </div>

    <!-- 현재 진행중인 계약서 파트 -->

    <div style="position: absolute;top: 271px;left:440px">
        <div style="position:absolute;width:300px;font: normal normal bold 23px/23px Noto Sans KR;color: #245AE3;">
            저장한 서명
        </div>
    </div>
    <div style="position: absolute;top: 367px;left: 440px;">

    <div class="card-group">
        <c:forEach items="${userSigns}" var="userSigns" varStatus="status">
        <div class="card" id="${status.count}">
            <img src="${userSigns.fileName}" name="signUrl" class="card-img-top" alt="...">
            <div class="card-body">
                <div class="form-check-inline">
                    <input type="checkbox" name="inputCheck" class="form-check-input" value="${status.count}">
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

    <div>
        <input type="button" value="NFT 등록" onclick="uploadNFT()">
    </div>

    <div style="position: absolute;top: 271px;left:0px">
        <div style="position:absolute;width:300px;font: normal normal bold 23px/23px Noto Sans KR;color: #245AE3;">
            NFT 서명
        </div>
    </div>

    <div style="position: absolute;top: 367px;left: 0px;">
        <div class="card-group">
            <c:forEach items="${userSigns}" var="userSign" varStatus="status">
                <c:if test="${userSign.isNFT eq 'T'}">
                <div class="card" id="${status.count}">
                    <img src="${userSign.fileName}" name="signUrl" class="card-img-top" alt="...">
                    <div class="card-body">
                        <div class="form-check-inline">
                            <input type="checkbox" name="inputCheck" class="form-check-input" value="${status.count}">
                        </div>
                    </div>
                </div>
                </c:if>
            </c:forEach>
        </div>
    </div>

        <!-- 여기서 부터는 쿼리에 따른 입력. top을 +60씩 하면서 적용하면 됩니다.-->
        <c:set var="total" value="22"/>
        <c:set var="totalbar" value="60"/>
        <c:set var="number" value="1"/>
        <c:forEach items="${ongoing}" var="data">


            </div>
            <c:set var="total" value="${total + 60}"/>
            <hr id="bar" class="my_page_bar" style="top:${totalbar}px;">
            <c:set var="totalbar" value="${totalbar + 60}"/>
            <c:set var="number" value="${number + 1}"/>

        </c:forEach>



        <!-- 완료된 계약서 파트 위의 마지막 바(ex.<hr class="my_page_bar" style="top:180px;">) 기준 +64px -->
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
</div>

<script>
</script>

<div id="black_div"
     style="display:none; position:fixed; left:0px; top:0px; background:rgba(28, 31, 38, 0.5); z-index:99; width:100%; height:100%;">
</div>


<!--
							<div id="imgControll_div" style="display:none">
								 <img  id="imgControll" name="imgControll" src="<spring:url value='/showsign/${signname}'/>" decoding="async" width="429px" height="201px" style="margin-top:90px">
                            	<a onclick="close_img()" style="position:absolute; top:30px; right:30px"><img
										src="/img/close.png" width=20px height=20px style="cursor:pointer" /></a>

                            </div>
                            -->

<div id="background_gray" style="display:none">
    <a onclick="close_div()" style="position:absolute; top:30px; right:30px"><img
            src="/img/close.png" width=20px height=20px style="cursor:pointer" /></a>
    <a onclick="prev_div()" style="position:absolute; top:30px; left:30px"><img
            src="/img/next.png" width=11.48px height=20px style="cursor:pointer" /></a>

    <div id="second_layer" style="margin-top:140px; display:none;">

        <a onclick="expand_layer('sign_draw')"><img src="/img/test4.png" width=160px
                                                    style="cursor:pointer"></a>
        <a onclick="expand_layer('stamp')"><img src="/img/test3.png" width=160px
                                                style="cursor:pointer"></a>
    </div>

    <div id="sign_Layer" style=" display:none">
        <div id='sign_background_white'>
            <div id='create_sign_layer' style="display:none;">

            </div>
            <div id='draw_sign_layer' style="display:none">
                <br><br><br>
                <img src="/img/sign_example.png" width=180px />
                <br><br><br>
            </div>
            <canvas id="sign" width="429" height="201"
                    style="border: 1px solid #767676; border-radius: 30px;"></canvas><br>
            <br>
        </div>
        <br>
        <input type="button" value="등록" onclick="saveSign()" class="button"
               style="height:45px; width:140px;">
        <input type="button" value="초기화" onclick="initSign()" class="button"
               style="height:45px; width:140px;">

    </div>
    <div id='stamp_Layer' style="display:none">
        <div id='stamp_background_white'>
            <br><br><br>
            <input type="text" id="name" value=""
                   style="padding-left:15px; outline:none; width: 317px; height: 45px; border: 1px solid #707070; border-radius: 30px;" />
            <input type="button" id="input_stamp_name" value="만들기" onclick='createStamp()'
                   class="button" style="width:99px; height:45px" />
            <br><br><br>
            <div style="font: normal normal normal 16px/21px Noto Sans KR;">만들기를 누르시고 도장을
                클릭하시면
                다운로드 됩니다.</div>
            <br><br>
            <div id="stamp_clear">
                <canvas id="ctx1" width="120" height="120"
                        style="margin-right:20px; border:1px solid black; display:none; cursor:pointer; cursor:hand"
                        onclick="download_stamp(this)"></canvas>
                <canvas id="ctx2" width="120" height="120"
                        style="border:1px solid black; display:none; cursor:pointer; cursor:hand"
                        onclick="download_stamp(this)"></canvas>
                <canvas id="ctx3" width="120" height="120"
                        style="margin-left:20px; border:1px solid black; display:none; cursor:pointer; cursor:hand"
                        onclick="download_stamp(this)"></canvas>

            </div>

            <br><br>

            <a href="#" style="font-family: 'SongganggasaB'"></a>
            <a href="#" style="font-family: 'BBTreeCB'"></a>
            <a href="#" style="font-family: 'TTSeokbosangjeolB'"></a>
            <a href="" id="ctx_down" style="display:none"
               download="stamp.png">stamp_download</a>

        </div>
    </div>
</div>


<div class="myPage_btn" style="text-align: end; display:flex; position: absolute; top:100px; left:1130px">
    <a onclick="showModalWindow()" id="input_sign_ancher"><img src="/img/sign_on.svg" width=100px
                                                    style="cursor:pointer;object-fit:contain" id="input_sign"></a>
    <br>
</div>
</body>
<script type="text/javascript">

    function showModalWindow() {
        document.getElementById("background_gray").style.display = "inline-block";
        document.getElementById("background_gray").style.position = "fixed";
        document.getElementById("second_layer").style.display = "inline-block";
        document.getElementById('sign_Layer').style.display = "none";
        document.getElementById('stamp_Layer').style.display = "none";
        document.getElementById("black_div").style.display = "block";
    }

    function expand_layer(e) {
        document.getElementById('second_layer').style.display = "none";
        document.getElementById('sign_Layer').style.display = "none";
        document.getElementById('stamp_Layer').style.display = "none";
        if (e == 'sign_draw') {
            document.getElementById('sign_Layer').style.display = "inline-block";
            document.getElementById('create_sign_layer').style.display = "none";
            document.getElementById('draw_sign_layer').style.display = "block";
            initSign();
        }
        else if (e == 'stamp') {
            document.getElementById('stamp_Layer').style.display = "inline-block";
            document.getElementById("stamp_clear").style.display = "none";
        }
    }

    function close_div() {
        document.getElementById("background_gray").style.display = "none";
        document.getElementById("black_div").style.display = "none";
    }

    function prev_div() {
        if (document.getElementById("second_layer").style.display == "none") {
            document.getElementById("second_layer").style.display = "inline-block";
            document.getElementById('sign_Layer').style.display = "none";
            document.getElementById('stamp_Layer').style.display = "none";
        }
    }

    function close_img()
    {
        document.getElementById("imgControll_div").style.display = "none";
        document.getElementById("black_div").style.display = "none";
    }

    function createSign() {
        var ctx = document.getElementById("sign");
        ctx = ctx.getContext("2d");
        var name = document.getElementById("sign_name");
        var middle_width;
        var middle_height;

        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
        ctx.textBaseline = 'middle';
        ctx.font = '150px BBTreeCB';
        ctx.textAlign = 'center';
        ctx.fillStyle = "black";
        middle_width = ctx.canvas.width / 2;
        middle_height = ctx.canvas.height / 2;
        ctx.fillText(name.value, middle_width, middle_height);
    }

    function saveSign() {
        var canvas1 = document.getElementById("sign");
        var imgDataUrl = canvas1.toDataURL('image/png');

        var blobBin = atob(imgDataUrl.split(',')[1]);	// base64 데이터 디코딩
        var array = [];
        for (var i = 0; i < blobBin.length; i++) {
            array.push(blobBin.charCodeAt(i));
        }
        var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성

        var formdata = new FormData();
        formdata.append("file", file);
        formdata.append("title",`${paper_name}`);
        formdata.append("create_time",`${create_date}`);

        $.ajax({
            async : true,
            type : 'POST',
            url : '/upload/sign',
            data : formdata,
            dataType: "json",
            processData : false,	// data 파라미터 강제 string 변환 방지!!
            contentType : false,	// application/x-www-form-urlencoded; 방지!!
            success : function (result) {
                if(result['result']=='true')
                {
                    // document.getElementById("input_sign").src= "/img/sign_off.svg";
                    //document.getElementById("input_sign_ancher").onclick = null;
                    document.getElementById("background_gray").style.display = "none";
                    document.getElementById("black_div").style.display = "none";
                    alert('서명 등록 성공');
                    window.location.href = 'http://localhost:8091/user/signUpload';
                }
            },
            error : function()
            {
                document.getElementById("background_gray").style.display = "none";
                document.getElementById("black_div").style.display = "none";
                alert('서명 등록 실패');
            }

        });
    }

    function initSign() {
        var canvas = document.getElementById("sign");
        canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
    }

    function fnImgPop(){
        document.getElementById("imgControll_div").style.display = "inline-block";
        document.getElementById("imgControll_div").style.position = "fixed";
        document.getElementById("black_div").style.display = "block";
    }

    stamp_canvas = new Array();
    stamp_canvas[0] = new Array();

    for (var j = 0; j < 3; j++) {
        var name = "ctx" + (j + 1);
        stamp_canvas[0][j] = document.getElementById(name);
    }

    function download_stamp(e) {
        document.getElementById("ctx_down").addEventListener('click', event => event.target.href = e.toDataURL());
        document.getElementById("ctx_down").click();
    }

    function createStamp() {
        console.log(stamp_canvas);
        document.getElementById("stamp_clear").style.display = "inline";
        stamp_canvas[0][0].style.display = "none";
        stamp_canvas[0][1].style.display = "none";
        stamp_canvas[0][2].style.display = "none";

        var name = document.getElementById("name");
        switch (name.value.length) {
            case 1: draw_vertical(stamp_canvas[0][0], name, 1); break;
            case 2: draw_vertical(stamp_canvas[0][0], name, 2); break;
            case 3: draw_vertical(stamp_canvas[0][0], name, 3); draw_ver_hor(stamp_canvas[0][1], name, 3); break;
            case 4: draw_ver_hor(stamp_canvas[0][1], name, 4); break;
            default: alert("4자 이내로 입력해 주세요.");
        }
    }

    function drawSquare(ctx, cx, cy, width, height) {
        ctx.strokeStyle = "red";
        ctx.lineWidth = 4;
        ctx.strokeRect(cx - width / 2, cy - height / 2, width, height);
    }

    function drawEllipse(ctx, cx, cy, width, height) {
        var PI2 = Math.PI * 2;
        var ratio = height / width;
        var radius = Math.max(width, height) / 2;
        var increment = 1 / radius;

        ctx.strokeStyle = "red";
        ctx.lineWidth = 4;
        ctx.beginPath();
        var x = cx + radius * Math.cos(0);
        var y = cy - ratio * radius * Math.sin(0);
        ctx.lineTo(x, y);

        for (var radians = increment; radians < PI2; radians += increment) {
            var x = cx + radius * Math.cos(radians);
            var y = cy - ratio * radius * Math.sin(radians);
            ctx.lineTo(x, y);
        }

        ctx.closePath();
        ctx.stroke();
    }

    function draw_vertical(ctx, name, length) {
        ctx.style.display = "inline";
        ctx = ctx.getContext("2d");
        var middle_width;
        var middle_height;

        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
        ctx.textBaseline = 'middle';
        ctx.font = '25pt TTSeokbosangjeolB';
        ctx.textAlign = 'center';
        ctx.fillStyle = "red"

        switch (length) {
            case 1:
                middle_width = ctx.canvas.width / 2;
                middle_height = ctx.canvas.height / 2;
                console.log(middle_height);
                ctx.fillText(name.value[0], middle_width, middle_height);
                break;
            case 2:
                middle_width = ctx.canvas.width / 2;
                middle_height = ctx.canvas.height / 2;
                console.log(middle_height);
                ctx.fillText(name.value[0], middle_width, middle_height - 19);
                ctx.fillText(name.value[1], middle_width, middle_height + 19);
                break;
            case 3:
                middle_width = ctx.canvas.width / 2;
                middle_height = ctx.canvas.height / 2;
                console.log(middle_height);
                ctx.fillText(name.value[0], middle_width, middle_height - 36);
                ctx.fillText(name.value[1], middle_width, middle_height);
                ctx.fillText(name.value[2], middle_width, middle_height + 36);
                break;
        }
        drawEllipse(ctx, middle_width, middle_height, 31, 59);
    }

    function draw_ver_hor(ctx, name, length) {
        ctx.style.display = "inline";
        ctx = ctx.getContext("2d");
        var middle_width;
        var middle_height;
        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);

        ctx.textBaseline = 'middle';
        ctx.font = '25pt TTSeokbosangjeolB';
        ctx.textAlign = 'center';
        ctx.fillStyle = "red"

        var copy_ctx = stamp_canvas[0][2];
        copy_ctx.style.display = "inline";
        copy_ctx = copy_ctx.getContext("2d");
        copy_ctx.clearRect(0, 0, copy_ctx.canvas.width, copy_ctx.canvas.height);
        copy_ctx.textBaseline = 'middle';
        copy_ctx.font = '25pt TTSeokbosangjeolB';
        copy_ctx.textAlign = 'center';
        copy_ctx.fillStyle = "red"
        switch (length) {
            case 3:
                middle_width = ctx.canvas.width / 2;
                middle_height = ctx.canvas.height / 2;
                ctx.fillText(name.value[0] + name.value[1], middle_width, middle_height - 19);
                ctx.fillText(name.value[2] + "인", middle_width, middle_height + 19);

                middle_width = copy_ctx.canvas.width / 2;
                middle_height = copy_ctx.canvas.height / 2;
                copy_ctx.fillText(name.value[0] + name.value[1], middle_width, middle_height - 19);
                copy_ctx.fillText(name.value[2] + "인", middle_width, middle_height + 19);
                break;
            case 4:
                middle_width = ctx.canvas.width / 2;
                middle_height = ctx.canvas.height / 2;
                ctx.fillText(name.value[0] + name.value[1], middle_width, middle_height - 19);
                ctx.fillText(name.value[2] + name.value[3], middle_width, middle_height + 19);

                middle_width = copy_ctx.canvas.width / 2;
                middle_height = copy_ctx.canvas.height / 2;
                copy_ctx.fillText(name.value[0] + name.value[1], middle_width, middle_height - 19);
                copy_ctx.fillText(name.value[2] + name.value[3], middle_width, middle_height + 19);
                break;
        }
        drawEllipse(ctx, middle_width, middle_height, 95, 95);
        drawSquare(copy_ctx, middle_width, middle_height, 78, 78);
    }

    var pos = {
        drawbale: false,
        x: -1,
        y: -1
    };
    var canvas, ctx;

    canvas = document.getElementById("sign");
    ctx = canvas.getContext("2d");

    canvas.addEventListener("mousedown", listener);
    canvas.addEventListener("mousemove", listener);
    canvas.addEventListener("mouseup", listener);
    canvas.addEventListener("mouseout", listener);
    canvas.addEventListener("touchstart", listener);
    canvas.addEventListener("touchmove", listener);
    canvas.addEventListener("touchend", listener);
    canvas.addEventListener("touchcancel", listener);

    function listener(event) {
        if (document.getElementById('draw_sign_layer').style.display == "block") {
            switch (event.type) {
                case "mousedown":
                    initDraw(event);
                    break;
                case "touchstart":
                    mobile_initDraw(event);
                    break;
                case "mousemove":
                    if (pos.drawable)
                        draw(event);
                    break;
                case "touchmove":
                    if (pos.drawable) {
                        event.preventDefault();
                        mobile_draw(event);
                    }
                    break;
                case "mouseout":
                case "mouseup":
                case "touchend":
                case "touchcancel":
                    finishDraw();
                    break;
            }
        }
    }

    function initDraw(event) {
        ctx.beginPath();
        pos.drawable = true;
        var coors = getPosition(event);
        pos.X = coors.X;
        pos.Y = coors.Y;
        ctx.moveTo(pos.X, pos.Y);
    }

    function mobile_initDraw(event) {
        ctx.beginPath();
        pos.drawable = true;
        var coors = mobile_getPosition(event);
        pos.X = coors.X;
        pos.Y = coors.Y;
        ctx.moveTo(pos.X, pos.Y);
    }

    function draw(event) {
        var coors = getPosition(event);
        ctx.lineWidth = 0.5;
        ctx.lineTo(coors.X, coors.Y);
        pos.X = coors.X;
        pos.Y = coors.Y;
        ctx.stroke();
    }

    function mobile_draw(event) {
        var coors = mobile_getPosition(event);
        ctx.lineWidth = 0.5;
        ctx.lineTo(coors.X, coors.Y);
        pos.X = coors.X;
        pos.Y = coors.Y;
        ctx.stroke();
    }


    function finishDraw() {
        pos.drawable = false;
        pos.X = -1;
        pos.Y = -1;
    }


    function getPosition(event) {
        var target = event.target || event.srcElement,
            rect = target.getBoundingClientRect(),
            offsetX = event.clientX - rect.left,
            offsetY = event.clientY - rect.top;
        return { X: offsetX, Y: offsetY }
    }

    function mobile_getPosition(event) {
        var touches = event.changedTouches;
        var target = event.target || event.srcElement,
            rect = target.getBoundingClientRect(),
            offsetX = touches[0].clientX - rect.left,
            offsetY = touches[0].clientY - rect.top;
        //alert("x" + offsetX + "y" + offsetY);
        return { X: offsetX, Y: offsetY }
    }
</script>
<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript">
        $(function (){
        })

        function uploadNFT() {
            var chkBoxArr = new Array();
            var list = $("input[name='inputCheck']");
            var signUrlList = $("img[name='signUrl']");

            console.log(signUrlList[0].currentSrc);

            for(var i = 0; i < list.length; i++){
                if(list[i].checked){
                    chkBoxArr.push(signUrlList[i].currentSrc);
                }
            }

            console.log(chkBoxArr);

            if (chkBoxArr.length == 0) {
                alert("선택된 사인이 없습니다.");
            } else if (chkBoxArr.length > 1) {
                alert("하나의 사인만 선택해주세요.");
            } else {
                console.log(chkBoxArr);
                $.ajax({
                    url : '/upload/signNFT',
                    type : 'POST',
                    traditional : true,
                    data : {
                        chkBoxArr : chkBoxArr
                    },
                    success: function(res){
                        console.log(res['res']);
                        if(res['res'] == 'true'){
                            alert("등록 성공");
                            location.replace("/user/signUpload");
                        } else
                            alert("internal error");
                    }
                })
            }
            console.log(chkBoxArr);
        }
    </script>

</html>
