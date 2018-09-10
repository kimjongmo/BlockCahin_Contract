<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	%>
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
<script>
	function submit1(){
		document.form1.action = "./writeAction.jsp?fileN="+encodeURI(document.form1.filename.value);
		document.form1.submit();
	}
</script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
		<a class="navbar-brand js-scroll-trigger" href="../../../resources/templates/index.jsp">
		<span style="font-sizw: 1.0em; color: orange;">Contract Master</span></a>
	
		<div class="collapse navbar-collapse" id="navbarResponsive">
          	<ul class="navbar-nav ml-auto">
          	
			<li class="nav-item">
              <a class="btn  btn btn-sm" href="main.jsp" role="button">계약서 작성</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="validation.jsp" role="button">검증하기</a>
            </li>
            <li class="nav-item">
              <%=session.getAttribute("userID")%> 님
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="logoutAction.jsp" role="button">로그아웃</a>
            </li>
          </ul>

		</div>
	</nav>
	
	<!-- 2. 문서파일 올리는 게시판 부분  -->
	<div class="jumbotron" style="padding-top: 20px;">
		<div class="container">
			
			<!-- 문서파일 등록 부분 -->
				<form name = "form1" method="post" enctype="multipart/form-data">
					<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
						<thead>
							<tr>
								<th colspan="2" style="background-color: #eeeeee; text-align: center;">문서 올리기 양식</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>아이디 : </td>
									<!-- <input type = "text" name = "userID"  readonly> -->
									<td><input type="text" class="form-control" value=<%=session.getAttribute("userID")%>  readonly name="userID" > </td>
								</tr>
								<tr>
									<td>문서 제목 : </td>
									<td><input type="text" class="form-control" placeholder="문서 제목" name="bbsTitle" maxlength="50"></td>
								</tr>
								<tr>
									<td>문서파일 찾기 : </td>
									<td><input type="file" name="filename" size="50" maxlength="50"></td>
								</tr>
						</tbody>
					</table>
						<button class="btn btn-primary pull-right" onclick="submit1()">등록</button>
				</form>
			</div>			
	</div>
			
</body>
</html>