<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<!--
	Landed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	</head>
	<body class="is-preload">
		<div id="page-wrapper">

				<!-- Header -->
                <header id="header">
                        <h1 id="logo"><a href="./main">KYU</a></h1>
                        <nav id="nav">
                            <ul>
                                <li><a href="./main">Home</a></li>
                                <li><a href="./description">Description</a></li>

								<c:choose>
								    <c:when test="${empty sessionScope.email }">
								        <li><a href="./login" class="button primary">Login</a></li>    
								    </c:when>
								    <c:otherwise>
								         <li><a href="./logout" class="button primary">Logout</a></li>    
								    </c:otherwise>
								</c:choose>
					             
                            </ul>
                        </nav>
                    </header>
    

		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script> 
			<script src="assets/js/main.js"></script>
			<script src="assets/js/template.js"></script>
			<script src="assets/js/handlebars.min.js"></script>
	</body>
</html>