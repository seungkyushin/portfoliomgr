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
		<title>KYU - PROJECT INFOMATION</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		
	</head>
	<body class="is-preload">
		<div id="page-wrapper">

	   			<!-- Main -->
				<div id="main" class="wrapper style1">
					<!-- project 내용  -->
				</div>		
				
					<div class="container">
						<div class="review_box">
							<h2>User Comment</h2>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"> <em class="graph_value"
									style="width: 84%;"></em>
								</span> <strong class="text_value"> <span>4.2</span> <em
									class="total">5.0</em>
								</strong> <span class="join_count"><em class="green">52건</em> 등록</span>
							</div>
							
							<ul class="list_short_review">
							<!-- project 덧글 -->
							</ul>
						</div>
						<p class="guide">
							<span> * 실제 방문한 사용자가 남긴 평가입니다.</span>
							
						</p>
						</div>
					</div>
											
				
				
					
	

<script type="template" id="list_item">
<li class="list_item">
		<div class="review_area">
			<h3 class="review-id">{{id}} 님</h3>
			<p class="review">
			{{comment}}
			</p>
			<span class="grade"></span> | <span class="date">{{date}}</span> 등록
		</div>
</li>
</script>


<script type="template" id="template-project-infomation" >
<div class="container">
<header class="major">
	<h2>{{name}}</h2>
    <p>{{subdescription}}</p>
	<p>{{description}}</p>
</header>

	<span class="image fit main"><img src="{{image}}" alt="{{name}}" /></span>
	<div class="col-5 col-4-medium col-12-xsmall"></div>
            <div class="col-2 col-4-medium col-12-xsmall">
				<a href="{{url}}" target="_blank"><input type="button" id="sendBtn" class="button primary fit" value="DEMO"></a>
			</div>
			
</div>

</script>
<script>
window.addEventListener("DOMContentLoaded",function(){
	$.ajax({
		type : "GET",
		url : "./api/project?id=" + ${requestScope.id},
		success : function(response){
				setHTML(response);
			},
		error : function(){
			alert("에러");
		}
		
	}); 
});
function setHTML(responseData){
	var projectInfo = responseData.projectList; 
	var data = {};
	data['name'] = projectInfo.name;
	data['subdescription'] = projectInfo.subdescription;
	data['description']  = projectInfo.description;
	data['image'] = projectInfo.image;
	data['url'] = projectInfo.url;
	
	templateParserAfter("#template-project-infomation",data, "#main");
}
</script>

	</body>
</html>

<%@ include file="/common/footer.jsp" %>