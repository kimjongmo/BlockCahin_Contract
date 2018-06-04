<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<!-- 2. 부트스트랩에서 가져온 css  -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Contract Master</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/creative.min.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<span style="display: none;"></span>
<!-- git 키 숨기기 -->
<script src="./js/config.js" type="text/javascript"></script>
<script src="./js/script.js" type="text/javascript"></script>


<!-- 부트스트랩 -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">


<script>

	function logoutWithKakao() {
		Kakao.Auth.logout();
		alert('카카오 로그아웃 완료!');
		setCookie("kakao_login", "", -1); // 쿠키삭제 (로그아웃)
		//deleteCookie( "kakao_login" ); 쿠키삭제 다른 방법
		window.location.href = "./login.jsp";
	}
</script>


<!--  -->
<title>계약서 웹페이지</title>
</head>

<body>
	<!-- 여기부터는 내 게시판 -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<a class="navbar-brand js-scroll-trigger" href="index.jsp">
		<span style="font-sizw: 1.0em; color: orange;">Contract Master</span></a>
	</nav>


	<div class="container">


		<div class="col-lg-4"></div>
		<div class="col-lg-6">
			<div class="jumbotron" style="padding-top: 10px;">
				<form method="post" action="loginAction.jsp">
					<h3 style="text-align: center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디"
							name="userID" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="userPassword" maxlength="20">
					</div>
					<br> <input type="submit" class="btn btn-warning form-control"
						value="로그인">
					<div class="form=group" collign>
					<center>
						<br> 
						<a id="kakao-login-btn"></a> 
						<a href="http://developers.kakao.com/logout"></a>
					</center>
						<script type='text/javascript'>
							Kakao.init(mykey);

							// 카카오 로그인 버튼을 생성합니다.
							Kakao.Auth.createLoginButton({
										container : '#kakao-login-btn',
										success : function(authObj) {
											alert(JSON.stringify(authObj)); //여기까지
											Kakao.API.request({
														url : '/v1/user/me',
														success : function(res) {
															document.frm.id.value = JSON.stringify(res.id);
															document.frm.kaccount_email.value = JSON.stringify(res.kaccount_email);
															document.frm.nickname.value = JSON.stringify(res.properties.nickname);
															document.frm.submit();
														},
														fail : function(error) {
															alert(JSON.stringify(error));
														}
													});
										},
										fail : function(err) {
											alert(JSON.stringify(err));
										}
									});
						</script>
						
					</div>
				</form>
			<center><font size="2px" color="orange" face="bold"> 아직 저희 회원이 아니세요? <input type="button" onclick="location.href = 'join.jsp'" value="회원가입"></input></font></center>
			</div><!-- 여기까지 점보트론 -->
		</div>
		<div class="col-lg-4"></div>
	</div>
	<input type="button" onclick="logoutWithKakao()" value = "카카오 쿠키제거">		

	<form action="./test.jsp" method="post" name="frm">
		<input type="hidden" name="id" value=""> 
		<input type="hidden"name="kaccount_email" value=""> 
		<input type="hidden"name="nickname" value="">
	</form>
</body>
</html>