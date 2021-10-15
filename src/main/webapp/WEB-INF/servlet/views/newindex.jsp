<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>
<body class="d-flex flex-column h-100">

<main class="flex-shrink-0">
    <!-- Navigation-->
    <sec:authorize access="isAnonymous()">
        <script>
            function announce() {
                window.open("/popup", "popup", "width=700, height=450,left=30,top=30");
            }
            announce();
        </script>
    </sec:authorize>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container px-5">
            <a class="navbar-brand" href="/">바로날인</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/customerCenter">고객센터</a></li>
                    <li class="nav-item"><a class="nav-link" href="/board">자료실</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/DocumentPage">계약서작성</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/DocumentPage2">저작권등록</a></li>
                    <sec:authorize access="isAnonymous()">
                        <script>
                            function announce() {
                                window.open("/popup", "popup", "width=700, height=450,left=30,top=30");
                            }
                            announce();
                        </script>
                        <li class="nav-item"><a class="nav-link" href="/joinForm">회원가입</a></li>
                        <li class="nav-item"><a class="nav-link" href="/loginForm"><button class="nav-item btn-dark">로그인</button></a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal" var="principal" />
                        <c:if test="${principal.role eq 'ROLE_ADMIN'}">
                            <li class="nav-item"><a class="nav-link" href="/user/adminPage">관리자페이지</a></li>
                        </c:if>
                        <li class="nav-item"><a class="nav-link" href="/user/myPage">마이페이지</a></li>
                        <li class="nav-item">
                            <form action="/logout" method="POST">
                                <a class="nav-link"><button type="submit" class="nav-item btn-dark">로그아웃</button>
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}" />
                                </a>
                            </form>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
    <div>
        <div id ="Carousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#Carousel" data-slide-to="0" class="active"></li>
                <li data-target="#Carousel" data-slide-to="1"></li>
            </ol>

            <div class="carousel-inner">
                <div class="item active" style="float: left">
                    <img src="/img/web1.jpg"style="max-width: 100%; height: auto">
                </div>
                <div class="item" style="float: left">
                    <img src="/img/web3.png"style="max-width: 100%; height: auto">
                </div>
            </div>

            <a class="left carousel-control" href="#Carousel" data-slide="prev">
            </a>
            <a class="right carousel-control" href="#Carousel" data-slide="next">
            </a>
        </div>
    </div>

    <!-- Features section-->
    <section class="py-5" id="features">
        <div class="container px-5 my-5">
            <div class="row gx-5">
                <div class="col-lg-4 mb-5 mb-lg-0"></div>
                <div class="col-lg-8">
                    <div class="row gx-5 row-cols-1 row-cols-md-2">
                        <div class="col mb-5 h-100">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-collection"></i></div>
                            <h2 class="h5" style="font-weight: bold">비용절감</h2>
                            <p class="mb-0">인쇄비,우편비,교통비 등 계약비 등 계약에 불필요한 비용은 절감하고, 도장 만들기부터 계약서 만들기까지 무료로 이용해보세요.</p>
                        </div>
                        <div class="col mb-5 h-100">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-building"></i></div>
                            <h2 class="h5" style="font-weight: bold">비대면 계약</h2>
                            <p class="mb-0">무료로 제공되는 계약서 양식으로 빠르게 작성하고, 비대면 계약 체결로 아까운 업무처리 시간을 단축해 보세요.</p>
                        </div>
                        <div class="col mb-5 mb-md-0 h-100">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-toggles2"></i></div>
                            <h2 class="h5" style="font-weight: bold">쉬운 관리</h2>
                            <p class="mb-0">계약서를 내 PC에 저장해 종이가 쌓이지 않는 쾌적한 업무환경과 찾기 쉬운 계약서로 관리해보세요.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Testimonial section-->
    <div class="py-5 bg-light">
        <div class="container px-5 my-5">
            <div class="row gx-5 justify-content-center">
                <div class="col-lg-10 col-xl-7">
                    <div class="text-center">
                        <div class="fs-4 mb-4 fst-italic">"Working with Start Bootstrap templates has saved me tons of development time when building new projects! Starting with a Bootstrap template just makes things easier!"</div>
                        <div class="d-flex align-items-center justify-content-center">
                            <img class="rounded-circle me-3" src="https://dummyimage.com/40x40/ced4da/6c757d" alt="..." />
                            <div class="fw-bold">
                                Tom Ato
                                <span class="fw-bold text-primary mx-1">/</span>
                                CEO, Pomodoro
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
