<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<header id="header">
	<h1 id="logo">
		<a href="./main">KYU</a>
	</h1>
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

