<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>HOME</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/action.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload landing">
		<div id="main-page-wrapper">
             
		
			<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<h2>웹개발자 하고싶다..</h2>
							<p>웹개발자로 일하고싶은 1인<br />
							 공부하면서 만들어본 포트폴리오를 정리했습니다.</p>
						</header>
						<span class="image animated pulse infinite"><img src="images/pic01.jpg" alt="" /></span>
					</div>
					<a href="#info" class="goto-next scrolly">Next</a>
				</section>

			<!-- One -->
		<section id="info" class="spotlight style1 bottom">
					<span class="image fit main"><img src="images/pic02.jpg" alt="" /></span>
					<div class="content">
						<div class="container">
							<div class="row">
								<div class="col-4 col-12-medium">
									<header>
										<h2>제작 이유</h2>
										<p>내가 만든 포트폴리오를 어떻게 하면 다른 사람들에게 간단하고 빠르게 보여줄수 있을까?</p>
									</header>
								</div>
								<div class="col-2 col-12-medium">
									<p>홈페이지 개발에 사용된 기술 및 언어  : HTML, JSP, MYSQL, SPRING</p>
								</div>
								<div class="col-4 col-12-medium">
									<p>제가 만든 프로젝트를 올리고 있으며 공부를 하면서 작업한 프로젝트라 퀄리티가 많이 떨어지고 버그가 많을 수 있습니다.
									  그런 부분은 귀엽게 봐주시고 수정이 필요한 부분은 덧글로 알려주시면 감사하겠습니다. (욕은 예비 개발자를 슬프게 합니다.)
									</p>
								</div>
							</div>
						</div>
					</div>
					<a href="#two" class="goto-next scrolly">Next</a>
				</section> 
				
				

	<!-- Two -->
		</div>
		
		
			<script type="template" id="template-project">
			<section id="project-{{id}}" class="spotlight style1 right">
			
					<span class="image fit main"><img src="{{image}}" alt="{{name}}" /></span>
					<div class="content">
						<header>
							<h2>{{name}}</h2>
							<p>{{subdescription}}</p>
						</header>
						<p>{{description}}</p>
						<ul class="actions">
							<li><a href="./descriptionProject?id={{id}}" class="button">자세히 보기</a></li>
						</ul>
					</div>
					<a href="#three" class="goto-next scrolly">Next</a>
			</section>
			</script>
			
			
		<script>
			$(document).ready(function(){
				
				 $.ajax({
					type : "GET",
					url : "./api/project?id=0",
					success : function(response){
							setProjectInfomation(response);
						},
					
					error : function(){
						console.log("에러");
					}
				}); 
				
			});
			
			function setProjectInfomation(responseData){
				responseData.projectList.forEach(function(v,i){
						setProjectHTML(v,i);
							
				}); 
				
				//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
				 $.getScript("assets/js/main.js", function(data, textStatus, jqxhr) {
				});
					 
			}
			
			
			function setProjectHTML(responseData,childNum){
				var projectInfo = responseData; 
				var data = {};
				
				data['id'] = projectInfo.id;
				data['name'] = projectInfo.name;
				data['subdescription'] = projectInfo.subdescription;
				data['description']  = projectInfo.description;
				data['image'] = projectInfo.image;
				data['url'] = projectInfo.url;
				
				var resultHTML = templateParserAfter("#template-project",
						data,"#main-page-wrapper");
	
			}
			
		</script>
	</body>
</html>


			<!-- Two -->
			<!-- 	<section id="todo" class="spotlight style2 right">
					<span class="image fit main"><img src="#" alt="#" /></span>
					<div class="content">
						<header>
							<h2>NAME</h2>
							<p>SUB DESCRIPTION</p>
						</header>
						<p>DESCRIPTION</p>
						<ul class="actions">
							<li><a href="#" class="button">자세히 보기</a></li>
						</ul>
					</div>
					<a href="#three" class="goto-next scrolly">Next</a>
				</section> -->
<!-- 
			Three
				<section id="three" class="spotlight style3 left">
					<span class="image fit main bottom"><img src="images/pic04.jpg" alt="" /></span>
					<div class="content">
						<header>
							<h2>Interdum felis blandit praesent sed augue</h2>
							<p>Accumsan integer ultricies aliquam vel massa sapien phasellus</p>
						</header>
						<p>Feugiat accumsan lorem eu ac lorem amet ac arcu phasellus tortor enim mi mi nisi praesent adipiscing. Integer mi sed nascetur cep aliquet augue varius tempus lobortis porttitor lorem et accumsan consequat adipiscing lorem.</p>
						<ul class="actions">
							<li><a href="#" class="button">Learn More</a></li>
						</ul>
					</div>
					<a href="#four" class="goto-next scrolly">Next</a>
				</section>

			Four
				<section id="four" class="wrapper style1 special fade-up">
					<div class="container">
						<header class="major">
							<h2>Accumsan sed tempus adipiscing blandit</h2>
							<p>Iaculis ac volutpat vis non enim gravida nisi faucibus posuere arcu consequat</p>
						</header>
						<div class="box alt">
							<div class="row gtr-uniform">
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-area-chart"></span>
									<h3>Ipsum sed commodo</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-comment"></span>
									<h3>Eleifend lorem ornare</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-flask"></span>
									<h3>Cubilia cep lobortis</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-paper-plane"></span>
									<h3>Non semper interdum</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-file"></span>
									<h3>Odio laoreet accumsan</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-lock"></span>
									<h3>Massa arcu accumsan</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
							</div>
						</div>
						<footer class="major">
							<ul class="actions special">
								<li><a href="#" class="button">Magna sed feugiat</a></li>
							</ul>
						</footer>
					</div>
				</section>

			Five
				<section id="five" class="wrapper style2 special fade">
					<div class="container">
						<header>
							<h2>Magna faucibus lorem diam</h2>
							<p>Ante metus praesent faucibus ante integer id accumsan eleifend</p>
						</header>
						<form method="post" action="#" class="cta">
							<div class="row gtr-uniform gtr-50">
								<div class="col-8 col-12-xsmall"><input type="email" name="email" id="email" placeholder="Your Email Address" /></div>
								<div class="col-4 col-12-xsmall"><input type="submit" value="Get Started" class="fit primary" /></div>
							</div>
						</form>
					</div>
				</section> -->
				
 <%@ include file="/common/footer.jsp" %>