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
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>계약서 웹페이지</title>
<script type="text/javascript" src="./js/check.js"></script>
<script>
function goPopup(){
	var pop = window.open("./jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	document.f.userAddress1.value = roadAddrPart1;
	document.f.userAddress.value = addrDetail;
}
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<a class="navbar-brand js-scroll-trigger" href="index.jsp">
		<span style="font-sizw: 1.0em; color: orange;">Contract Master</span></a>
	</nav>
	
	
		
		
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form name = "f" method="post" action="joinAction.jsp">
					<h3 style="text-align: center;">회원가입 화면</h3>
					
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
						<font size="1"><span style="color:red">*아이디는 4~12사이 대소문자와 숫자로만 만들어주세요</span></font>
					</div>
					
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
						<font size="1"><span style="color:red">*비밀번호는 4~12사이로 입력해주세요</span></font>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호 확인" name="userPasswordck" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="userName" maxlength="10">
						<font size="1"><span style="color:red">*이름은 2자 이상 입력해주세요</span></font>
					</div>
					
					<div class="form-group" style="text-align: center;">
						<input type="button" onclick="goPopup()" class="btn btn-primary active" value="주소검색"></input>
					</div>
					<div class="form-group">
						 <input type="text" class="form-control" placeholder="주소 입력" name="userAddress1" maxlength="100" readonly>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="상세 주소" name="userAddress" maxlength="100" readonly>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="전화번호" name="userPhone" maxlength="13">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이메일" name="userEmail" maxlength="50">
					</div>
					<input type="button" class="btn btn-primary form-control" value="회원가입" onClick="sendIt()">
					<br>
					<br>
					<center><font size="2px" color="orange" face="bold"> 저희 회원이세요? <input type="button" onclick="location.href = 'login.jsp'" value="로그인"></input></font></center>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
 	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script src="js/bootstrap.js"></script>
</body>
</html>