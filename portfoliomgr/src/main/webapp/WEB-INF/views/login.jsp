<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/common/header.jsp" %>
<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>KYU - 로그인</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/action.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">
		<div id="page-wrapper">


			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>로그인</h2>
                            <p>계정이 없으시다면   <a href="./join">여기</a>에서 생성해주시길 바랍니다.</p>
                         	<p id="errorMsg" style="color:#e44c65">${errorMessage}</p>
                         	<p id="checkmsg"></p>
						</header>

						<!-- Form -->
							<section>
				    				<form id="formData" method="post" action="./logincheck">
									<div class="row gtr-uniform gtr-50">

                   

                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="email" name="email" id="email" value="" placeholder="이메일" />
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                        
                                        
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="password" name="password" id="password" value="" placeholder="비밀번호" />
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
									

                                    <div class="col-5 col-4-medium col-12-xsmall"></div>
                                    <div class="col-2 col-4-medium col-12-xsmall">
										<ul class="actions stacked">
											<li><input id="sendBtn" type="button" class="button primary fit" value="확인"></li>
											</ul>
            
								</form>
							</section>
					</div>
				</div>
		</div>
		
		<script>
		 window.addEventListener("DOMContentLoaded",function(){
			
			 document.querySelector("#sendBtn").addEventListener("click",function(event){
				 
				 event.preventDefault();
					
					if( isCheckForm() == true )
					 {
						document.querySelector("#formData").submit();
					 }
					else{
						document.querySelector("#errorMsg").innerText = "";
					}
				 
			 });
			 
		 });
		 
			function isCheckForm(){
				
				var password =  document.querySelector("#password");
				var email = document.querySelector("#email");
				var checkMsg = document.querySelector("#checkmsg");
				var result = true;
				
				checkMsg.innerHTML = "";
				
			
				if( email.value.length <= 0 || email.value.match(/\w@\w.\w/) == null)
				{
					checkMsg.innerHTML += " [<strong style='color:#e44c65'>이메일</strong>] ";
					startAnimation("#email","shake");
					result = false;
				}
				
				
				if( password.value.length <= 0 ){
					checkMsg.innerHTML += " [<strong style='color:#e44c65'>비밀번호</strong>] ";
					startAnimation("#password","shake");
					result = false;
				}

				checkMsg.innerHTML += "를 확인해 주세요!";
				
				return result;
			}
			
			function startAnimation(elementName, type){
				$(elementName).removeClass().addClass(type + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
				      $(this).removeClass();
				    });
			}
			
		</script>
	</body>
</html>

<%@ include file="/common/footer.jsp" %>