<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<link rel="stylesheet" href="css/bootstrap.css">
<title>계약서 웹페이지</title>
</head>
<body>

	<!--  -->


	<!-- 여기부터는 내 게시판 -->
	<nav class="navbar navbar-default"></nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
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
					<input type="submit" class="btn btn-primary form-control"
						value="로그인">
					<div class="form=group">
						<a id="kakao-login-btn"></a> <a
							href="http://developers.kakao.com/logout"></a>
	
						<script type='text/javascript'>
							Kakao.init('asbdjsdlkfjaslkfjsadlfk;ajsflk;asjfl;asjdflkasjfsl;j');

							// 카카오 로그인 버튼을 생성합니다.
							Kakao.Auth.createLoginButton({
								container : '#kakao-login-btn',
								success : function(authObj) {
									alert(JSON.stringify(authObj)); //여기까지
									Kakao.API.request({
										url: '/v1/user/me',
										success: function(res){
											  document.frm.id.value = JSON.stringify(res.id);
											  document.frm.kaccount_email.value = JSON.stringify(res.kaccount_email); 
											  document.frm.nickname.value = JSON.stringify(res.properties.nickname); 
											  document.frm.submit();
										},
										fail: function(error) {
											alert(JSON.stringify(error));
										}
									});
								},
								fail : function(err) {
									alert(JSON.stringify(err));
								}
							});
						</script>
						<script>
							function formSubmit()
							{
								document.getElementById('frm').submit();
							}
						</script>
		
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

	<form action="./test.jsp" method="post" name="frm">
		<input type="hidden" name="id" value="">
		<input type="hidden" name="kaccount_email" value="">
		<input type="hidden" name="nickname" value="">
	</form>
	


</body>
</html>