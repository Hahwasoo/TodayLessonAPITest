<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Custom Login Page</h1>
  <h2><c:out value="${error}"/></h2>
  <h2><c:out value="${logout}"/></h2>
  
  <form method='post' action="/login">
  
  <div>
    <input type='text' name='username' placeholder="아이디 입력하시오">
  </div>
  <div>
    <input type='password' name='password' placeholder="비밀번호를 입력하시오">
  </div>

  <div>
    <input type='checkbox' name='remember-me'> Remember Me
  </div>

  <div>
    <input type='submit'>
  </div>
    <input type="hidden" name="${_csrf.parameterName}"
    value="${_csrf.token}" />
  
  </form>
  <!-- 로그인 모달창  -->
  <div>
     <form method="post" action="">
        <div class="hs_us_mainheader_loginmodal">
           <b>오늘의 레슨에 오신것을 환영합니다!</b>
           <span>오늘의 레슨은 서로간의 재능을 연결해 모두의 경쟁력을 높이는 플랫폼 서비스입니다.</span>
           
           <div class="hs_us_mainheader_loginform">
              <label>ID</label>
              <input type='text' name='username' placeholder="아이디 입력하시오" required="required">
              <label>PW</label>
              <input type='password' name='password' placeholder="비밀번호를 입력하시오" required="required">
              <label>
                 <input type='checkbox' name='remember-me'> Remember me
              </label>
              <input type="submit" value="Login">
           </div>
           
           <div class=""></div>
           
           <div>
              <a>카카오</a>
              <a>네이버</a>
              <a>구글</a>
              <a>페이스북</a>
           </div>
           
           <div class="hs_us_mainheader_findidpw">
              <a href="">아이디찾기</a><span>|</span><a href="">비밀번호찾기</a>
           </div>
           
           <div class="hs_us_mainheader_join">
              <span>
                 <span>아직도 오늘의 레슨 회원이 아니신가요?</span><a href="">회원가입</a>
              </span> 
           </div>
   
        </div>   
     </form>
  </div>
  
  
  
  
</body>
</html>






