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
		<title>KYU - 방문자 가입</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<link rel="stylesheet" href="assets/css/action.css" />
	</head>
	<body class="is-preload">
		<div id="page-wrapper">


			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>방문자 가입</h2>
                            <p>홈페이지 관리자는 해당 정보를 매일 삭제 합니다.</p>
                            <p>형식 및 빈칸없이 모두 알맞게 작성해주시길 바랍니다.</p>
                            <p id="checkmsg"></p>
						</header>

						<!-- Form -->
							<section>
				    				<form id="formData" method="post" action="./addvisiter">
									<div class="row gtr-uniform gtr-50">

                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="text" name="name" id="name" value="" placeholder="성함" />
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                     


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
									
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="text" name="organization" id="organization" value="" placeholder="소속" />
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                                                

                                    <div class="col-5 col-4-medium col-12-xsmall"></div>
                                    <div class="col-2 col-4-medium col-12-xsmall">
										<ul class="actions stacked">
											<li><input type="button" id="sendBtn" class="button primary fit" value="가입"></li>
											</ul>
            
								</form>
							</section>
					</div>
				</div>
		</div>
		
		<script>
		$(document).ready(function(){
			
			$("#sendBtn").on("click",function(event){
				
				event.preventDefault();
				
				if( isCheckForm() == true )
				 {
					$("#formData").submit();
				 }
	  
			});

		});
		function isCheckForm(){
						
			var name = $("#name").val();
			var password =  $("#password").val();
			var email = $("#email").val();
			var checkMsgHTML = "";
			var result = true;
			
			$("#checkmsg").html("");
			
			if( name.length <= 0 ){
				checkMsgHTML += " [<strong style='color:#e44c65'>이름</strong>] "
				startAnimation("#name","shake");
				result = false;
			}
			
			if( password.length <= 0 ){
				checkMsgHTML += " [<strong style='color:#e44c65'>비밀번호</strong>] ";
				startAnimation("#password","shake");
				result = false;
			}
			
					
			if( email.length <= 0 || email.match(/\w@\w.\w/) == null)
			{
				checkMsgHTML += " [<strong style='color:#e44c65'>이메일</strong>] ";
				startAnimation("#email","shake");
				result = false;
			}

			checkMsgHTML += "를 확인해 주세요!";
			$("#checkmsg").html(checkMsgHTML);
			
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