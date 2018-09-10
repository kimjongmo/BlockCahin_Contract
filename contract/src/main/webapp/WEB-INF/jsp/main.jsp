<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<!-- 1. 부트스트랩에서 가져온 css  -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Contract Master</title>

    <!-- Bootstrap core CSS -->
    <link href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/css/creative.min.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/css/bootstrap.css">
<title>계약서 웹페이지</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
		<a class="navbar-brand js-scroll-trigger" href="../../../resources/templates/index.jsp">
		<span style="font-sizw: 1.0em; color: orange;">Contract Master</span></a>
	
		<div class="collapse navbar-collapse" id="navbarResponsive">
          	<ul class="navbar-nav ml-auto">
          	<li class="nav-item">
              <a class="btn  btn btn-sm" href="test.jsp" role="button">누르시오우예~</a>
            </li>
			<li class="nav-item">
              <a class="btn  btn btn-sm" href="board.jsp" role="button">계약서 작성</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="myPage.jsp" role="button">마이페이지</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm"><%=session.getAttribute("userID")%> 님</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="logoutAction.jsp" role="button">로그아웃</a>
            </li>
          </ul>

		</div>
	</nav>
	
</body>
</html>