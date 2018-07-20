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
					
							</strong> <span class="join_count">총 덧글은 <em id="maxCount">0</em>개</span>
						
							
							<ul class="list_short_review">
							<!-- project 덧글 -->
							</ul>
						</div>
			
						   		<c:choose>
								    <c:when test="${empty sessionScope.email }">
								        <a href="./login" >로그인</a>    
								    </c:when>
								    <c:otherwise>
								         <a href="./comment?projectId=${requestScope.projectId}" >응원하기</a>    
								    </c:otherwise>
								</c:choose>   

						<p class="guide">
							<span> * 실제 방문한 사용자가 남긴 평가입니다.</span>
						</p>
						</div>
					</div>
											
				
				
					
	

<script type="template" id="list_item">
<li class="list_item">
		<div class="review_area">
			<h3 class="review-email">{{visiterEmail}} 님의 한마디</h3>
			<span>종류 : {{type}}</span>
			<p class="review">
				{{content}}
			</p>
			<span class="grade">{{score}}점</span> | <span class="date">{{date}}</span> 등록
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
	var projectId = ${requestScope.projectId};
	
	$.ajax({
		type : "GET",
		url : "./api/project?id=" + projectId,
		success : setHTML,
		error : function(){
			alert("에러");
		}
		
	});
	
	$.ajax({
		type : "GET",
		url : "./api/comment?projectId=" + projectId +"&start=0",
		success : setCommentHTML,
		error : function(){
			alert("에러");
		}
		
	}); 
	

	
});
function setHTML(responseData){
	var projectInfo = responseData.projectList; 
	var data = {};
	data['email'] = projectInfo.name;
	data['subdescription'] = projectInfo.subdescription;
	data['description']  = projectInfo.description;
	data['image'] = projectInfo.image;
	data['url'] = projectInfo.url;
	
	templateParserAfter("#template-project-infomation",data, "#main");
}

function setCommentHTML(responseData){
	var comments = responseData.comments; 
	var avgScore = 0;
	comments.forEach(function(v){
		
		var data = {};
		data['visiterEmail'] = v.visiterEmail;
		data['content'] = v.content;
		data['score'] = v.score;
		data['type'] = v.type;
		data['date']  = v.date;
		
		templateParserAfter("#list_item",data, ".list_short_review");
		
		avgScore += v.score;
	});
	
	avgScore /= responseData.comments.length;
	avgScore = avgScore.toFixed(1);
	
	$(".text_value").text(avgScore);
	$("#maxCount").text(responseData.comments.length);
}

</script>

	</body>
</html>

<%@ include file="/common/footer.jsp" %>