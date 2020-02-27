<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--header style-->
<link rel="stylesheet" href="resources/CSS/hs_us_main_header.css">
<!--header style-->

</head>
<body>
   <div>
      <div class="hs_us_mainheader_topdiv">
         <div class="hs_us_mainheader_topdiv_width">
            <span>지금 당신의 취미를 찾아보세요</span>
            <span class="hs_us_mainheader_topdiv_cartloginsearchbox">
               <a href=""><i class="fa fa-shopping-cart"></i></a>
               <sec:authorize access="isAnonymous()">  <!-- 모든사람들이 다 -->
                  <a href="/customlogin">로그인 </a><span style="margin-left: 10px;">|</span><a href="">회원가입</a>
               </sec:authorize>
         <!-- 
                    모달창으로 만들꺼라서 로그인 모달창 만들때 이거 추가하기
           <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
          -->

         <!-- 로그인한 사람들만  -->
               <sec:authorize access="isAuthenticated()">
                  <form action="/logout" method='post'>
                     <input type="hidden"name="${_csrf.parameterName}"value="${_csrf.token}"/>  <!-- 이걸  main 페이지에 넣어서 구분을   -->
                     <button>로그아웃</button>
                  </form>
               </sec:authorize>
               <a href=""><i class='fas fa-search'></i></a>
            </span>
         </div>
      </div>
      <div class="hs_us_mainheader_center">
         <a href="/alluser">
            <img src="resources/IMG/User_Logo.png" alt="User_Logo" style="width: 11%;">
         </a>
         <span class="hs_us_mainheader_center_senior">
            <a href="">
               <button>시니어지원 🌴</button>
            </a>
         </span>
      </div>
      <div class="hs_us_mainheader_line"></div>
      <div class="hs_us_mainheader_navbox">
         <nav>
            <a href="">얼리버드</a>
            <a href="">전체레슨</a>
            <a href="">자유게시판</a>
            <a href="">스토어</a>
            <a href="">이벤트</a>
         </nav>
      </div>
      <div class="hs_us_mainheader_line"></div>
   </div>
</body>
</html>