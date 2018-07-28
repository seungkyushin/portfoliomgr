<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>
<body>
<header id="header">
	<h1 id="logo">
		<a href="./main">KYU</a>
	</h1>
	<nav id="nav">
		<ul>
			<li><a href="./description">설명</a></li>
			
			<c:choose>
				<c:when test="${empty sessionScope.email}">
					<li><a href="./join">가입하기</a></li>
					<li><a href="./login" class="button primary">로그인</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="./checkProfile">프로필</a></li>
					<li><a href="./logout" class="button primary">로그 아웃</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</nav>
</header>
<div class="popup_booking_wrapper" style="display:none">
			<div class="dimm_dark" style="display:block"></div>
			<div class="popup_booking refund">
			
				<div class="nomember_alert">
			
					<c:if test="${!empty ResultMessage}">
						<p style="color:white">${ResultMessage}</p>
					</c:if>
				
					<a href="javascript:disablePopup('${url}')" class="button small"><span>확인</span></a>
				</div>
		</div>
</div>
</body>

<script>
$(document).ready(function(){
	 //< 팝업 메시지가 있다면 출력
	 setPopup("${ResultMessage}");
});
</script>
</html>


	


