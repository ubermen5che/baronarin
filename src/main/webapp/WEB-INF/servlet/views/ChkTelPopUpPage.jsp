<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.6.5/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/ui/4.8.1/firebase-ui-auth.js"></script>
    <link type="text/css" rel="stylesheet"
          href="https://www.gstatic.com/firebasejs/ui/4.8.1/firebase-ui-auth.css" />

    <script type="module">
        const firebaseConfig = {
            apiKey: "AIzaSyAuf8ms6Ub3ZUfo-j29Skc1XEV7dNDUyuE",
            authDomain: "baronarintest.firebaseapp.com",
            projectId: "baronarintest",
            storageBucket: "baronarintest.appspot.com",
            messagingSenderId: "15977281983",
            appId: "1:15977281983:web:07630bfa6e7f2ad6d8490a",
            measurementId: "G-KJQGEK296K"
        };
        var httpRequest;
        var userPhoneNumber;
        function callBackFunc(){
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    var jsonRes = JSON.parse(httpRequest.responseText);
                    if (jsonRes['res'] == "true"){
                        alert("성공적으로 인증되었습니다.");
                        window.opener.getPhoneNumberFromFBpopUp(userPhoneNumber);
                        window.close();
                    } else
                        alert("json parse error" + jsonRes['res']);
                } else {
                    alert("실패:" + httpRequest.status);
                }
            }
        }
        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);

        // Initialize the FirebaseUI Widget using Firebase.
        var ui = new firebaseui.auth.AuthUI(firebase.auth());
        ui.start('#firebaseui-auth-container', {
            signInOptions: [
                firebase.auth.PhoneAuthProvider.PROVIDER_ID,
            ],
            callbacks: {
                // 로그인 성공시 실행
                signInSuccessWithAuthResult: (authResult, redirectUrl) => {
                    firebase.auth().onAuthStateChanged(function(user) {
                        // 인증한 폰 번호 가져오는 작업
                        if(user){
                            userPhoneNumber = user.phoneNumber;
                        }
                    });
                    firebase.auth().currentUser.getIdToken(/* forceRefresh */ true).then(function (idToken) {
                        // 해당 유저의 idToken을 Firebase로부터 받아온다.
                        // idToken을 백엔드 서버로 전송하고 토큰 검증 절차를 처리한다.
                        // AJAX를 통해 idToken을 헤더로 전송
                        httpRequest = new XMLHttpRequest();
                        httpRequest.open('GET', '/fb', true);
                        httpRequest.setRequestHeader("Authorization", idToken);
                        httpRequest.send(null);
                        httpRequest.onreadystatechange = callBackFunc;
                    }).catch(function (error) {
                        // Handle error
                    })
                }
            }
            // Other config options...
        });

    </script>
</head>
<body>
<div id="firebaseui-auth-container"></div>
</body>
</html>
