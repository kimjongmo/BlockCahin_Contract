<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>계약서 웹페이지</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- <a class="navbar-brand" href="main.jsp">[계약서 웹페이지 로고 넣을 부분]</a>-->
		</div>
		<div class="container" href="main.jsp" style="text-align:center">
			<h2 location="center">Contract Master</h2>
		<!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">-->
			<ul class="nav nav-tabs">
				<li class="active"><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			<ul class="nav nav-tabs navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li> 
						<li><a href="join.jsp">회원가입</a></li> 
					</ul>
					</ul>
				</li>		
			</ul>
		</div> 
	</nav>
	
	<div class="container">
		<div id="myCarousel" class="carousel" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide="0" class="active"></li>
				<li data-target="#myCarousel" data-slide="1"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item active">
					<p style="text-align: center;"><img src="images/con2.jpg" class="img-reponsive img-circle"></p>
				</div>
				
			</div>
			</div>
		</div>
	
	<div class="container">
		<div class="jumbotron" >
			<div class="container">
				<h3>증명을 위한 계약서, </h3>
				<h3>이젠 이곳에 등록하세요.</h3>
				<p><h5>이 웹 사이트는 스마트 계약서 웹사이트 입니다. 블록체인 기술을 사용하여 안전한 계약서를 쓸 수 있게 되었습니다.</h5></p>
				<a class="btn btn-danger pull-right" href="#" role="button">계약서 작성></a>
			</div>
		</div>
	</div>
	
 	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script src="js/bootstrap.js"></script>
</body>
</html>