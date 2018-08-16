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
	<link rel="stylesheet" href="assets/css/popup.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	
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
							<h2>${visiter.name} 님 프로필</h2>
							<p id="checkmsg"></p>
							
							<div class='table-wrapper'>
								<form id="formData" method="post" action="./modifyProfile">
						                                 
                              
								<table>
									<tbody id="profile-table">
									
										<tr>
												<td>이름</td>
												<td><input type="text" id="name" name="name"  value="${visiter.name}" readonly></td>
										</tr>
										<tr>
												<td>이메일</td>
												<td><input type="text" id="email" name="email" value="${visiter.email}" readonly></td>
										</tr>
										<tr>
												<td>패스워드</td>
												<td><input type="password"  id="password" name="password"  maxlength="20"></td>
										</tr>
										<tr>
												<td>패스워드 확인</td>
												<td><input type="password"  id="password-check" name="password-check"  maxlength="20"></td>
										</tr>
										<tr>
												<td>소속</td>
												<td><input type="text" value="${visiter.organization}" id="organization" name="organization"  maxlength="20"></td>
												
										</tr>

									</tbody>
								</table>
								
								    
										<ul class="actions stacked">
											<li><input id="sendBtn" type="button" class="button primary fit" value="수정 하기"></li>
											</ul>
            						
								
								
							</form>
							</div>
					</div>
				</section>
			</div>
	
		
		<%@ include file="/common/footer.jsp"%>		
	</div>
</body>	

<script>
$(document).ready(function(){

	$("#sendBtn").on("click",function(event){
		 
		 event.preventDefault();
			
		 login();
		 
	 });
	
	
	$("#formData").keypress(function (e) {
        if (e.which == 13){
        	login();
        }
    });
	
});



function login(){
	if( isCheckForm() == true )
	 {
		if(confirm("정말 수정 하시겠습니까?") == true )
			$("#formData").submit();
	 }
		 
}
function isCheckForm(){
				
	var password =  $("#password").val();
	var passwordCheck = $("#password-check").val();
	var checkMsgHTML = "";
	var result = true;
	
	$("#checkmsg").html("");
	
	if( password.length <= 0 ){
		checkMsgHTML += " [<strong style='color:#e44c65'>패스워드</strong>] "
		checkMsgHTML += "를 확인해 주세요!";
		startAnimation("#password","shake");
		result = false;
	}
	
	if( password != passwordCheck ){
		checkMsgHTML += " <strong style='color:#e44c65'>비밀번호</strong> ";
		checkMsgHTML += "가 일치 하지 않습니다";
		startAnimation("#password","shake");
		startAnimation("#password-check","shake");
		result = false;
	}
		

	$("#checkmsg").html(checkMsgHTML);
	
	return result;
}
</script>	
</html>


