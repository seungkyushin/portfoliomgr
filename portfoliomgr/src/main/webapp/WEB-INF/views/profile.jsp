<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
	<title>KYU - MAIN</title>
	<meta charset="utf-8" />
	<meta name="viewport"content="width=device-width, initial-scale=1, user-scalable=no" />		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/action.css" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/naver-style.css"/>
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	
		<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/template.js"></script>
	<script src="assets/js/handlebars.min.js"></script>
 </head>
<body>
<div id="main-page-wrapper"> 
	<%@ include file="/common/header.jsp"%>
		<div id="projectList-wrapper"class="content">
 			<!-- Banner -->
				<section id="banner">
					<div class="content">
					<span class="image animated bounce infinite"><img src="images/pic01.jpg" alt="" /></span>
							<h2>'신승규' 님 프로필</h2>
							
							<div class='table-wrapper'>
								<table>
									<tbody>
									<c:forEach item="list" items="${visiter} ">
										<tr>
												<td>이름</td>
												<td>${list}</td>
												<td>수정불가</td>
										</tr>
									</c:forEach>
	
									</tbody>
								</table>
							</div>
					</div>
				</section>
			</div>
	
		
		<%@ include file="/common/footer.jsp"%>		
	</div>
</body>	

<script>
$(document).ready(function(){
	

});

</script>	
</html>


