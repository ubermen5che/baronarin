<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.jsp" %>
    <title>회원가입</title>
</head>
<body class="d-flex flex-column">
<main class="flex-shrink-0">
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container px-5">
            <a class="navbar-brand" href="/">바로날인</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/customerCenter">고객센터</a></li>
                    <li class="nav-item"><a class="nav-link" href="/board">자료실</a></li>
                    <li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
                    <li class="nav-item"><a class="nav-link" href="/loginForm">로그인</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Blog</a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                            <li><a class="dropdown-item" href="blog-home.html">Blog Home</a></li>
                            <li><a class="dropdown-item" href="blog-post.html">Blog Post</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Portfolio</a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                            <li><a class="dropdown-item" href="portfolio-overview.html">Portfolio Overview</a></li>
                            <li><a class="dropdown-item" href="portfolio-item.html">Portfolio Item</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- Page content-->
    <section class="py-5">
        <div class="container px-5">
            <!-- Contact form-->
            <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                <div class="text-center mb-5">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                    <h1 class="fw-bolder">For SignUp!</h1>
                </div>
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-4 col-xl-3">
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- * * SB Forms Contact Form * *-->
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- This form is pre-integrated with SB Forms.-->
                        <!-- To make this form functional, sign up at-->
                        <!-- https://startbootstrap.com/solution/contact-forms-->
                        <!-- to get an API token!-->
                        <div id="RegisterForm">
                            <!-- Email address input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                <label for="email">Email address</label>
                                <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                                <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                            </div>

                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="name" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                <label for="name">Full name</label>
                                <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                            </div>
                            <!-- Name input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" name="password"  placeholder="Enter your Password..."  type="password" id="password" data-sb-validations="required"  />
                                <label for="name">Password</label>
                                <div class="invalid-feedback" data-sb-feedback="password:required">A password is required.</div>
                            </div>

                            <div class="form-floating mb-3">
                                <div class="d-grid"><button class="btn btn-dark btn-outline-warning" id="ChkTelBtn" onclick="ChkTelBtn()">check phone number</button></div>
                                <script>
                                    var chktelbtn = false;
                                    function ChkTelBtn(){
                                        window.open("/ChkTelPopUpPage","ChkTelPopUpPage","width=400, height=460,left=30,top=30");


                                    }
                                </script>

                            </div>

                            <!-- SignUp Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-lg" id="SignUpBtn" onclick="BtnClkSignUp()">Sign Up</button></div>

                            <script>
                                function BtnClkSignUp(){
                                    if(!chktelbtn){
                                        alert("false");
                                        return;
                                    }
                                    var params={
                                        email:	$("#email").value,
                                        password: $('#password').value,
                                        realName : $('#name').value,
                                        phoneNumber : $('#phone').value
                                    }
                                    $.ajax({
                                        async : true,
                                        type : 'POST',
                                        data: params,
                                        url: "/join",
                                        dataType: "json",
                                        contentType: "application/json; charset=UTF-8",
                                        success: function(res) {
                                            if(res==true){
                                                alert("가입된 이메일로 메일이 전송되었습니다. 링크를 통해 인증을 해주시길 바랍니다.");
                                            }
                                            else{
                                                alert(res);
                                            }
                                        },
                                        error: function(error){
                                            alert("오류발생:"+error);
                                        }
                                    });
                                }
                            </script>
                        </div>
                    </div>

                </div>
            </div>
            <!-- Contact cards-->
            <div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
                <div class="col">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-chat-dots"></i></div>
                    <div class="h5 mb-2">Chat with us</div>
                    <p class="text-muted mb-0">Chat live with one of our support specialists.</p>
                </div>
                <div class="col">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                    <div class="h5">Ask the community</div>
                    <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                </div>
                <div class="col">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
                    <div class="h5">Support center</div>
                    <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                </div>
                <div class="col">
                    <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                    <div class="h5">Call us</div>
                    <p class="text-muted mb-0">Call us during normal business hours at (555) 892-9403.</p>
                </div>
            </div>
        </div>
    </section>
</main>
<!-- Footer-->
<footer class="bg-dark py-4 mt-auto">
    <div class="container px-5">
        <div class="row align-items-center justify-content-between flex-column flex-sm-row">
            <div class="col-auto"><div class="small m-0 text-white">Copyright &copy; Your Website 2021</div></div>
            <div class="col-auto">
                <a class="link-light small" href="#!">Privacy</a>
                <span class="text-white mx-1">&middot;</span>
                <a class="link-light small" href="#!">Terms</a>
                <span class="text-white mx-1">&middot;</span>
                <a class="link-light small" href="#!">Contact</a>
            </div>
        </div>
    </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>

