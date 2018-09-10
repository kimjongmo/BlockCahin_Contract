<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bbs.Bbs"%>
<%@page import="java.util.Vector"%>
<%@page import="bbs.BbsDAO" %>
<%

	BbsDAO bMgr = new BbsDAO();
	
	request.setCharacterEncoding("utf-8");
	int totalRecord = 0; //전체레코드수
	int numPerPage = 10; // 페이지당 레코드 수
	int pagePerBlock = 15; //블럭당 페이지수
	
	int totalPage = 0; //전체 페이지 수
	int totalBlock = 0; //전체 블럭수
	
	int nowPage = 1; // 현재페이지
	int nowBlock = 1; //현재블럭
	
	int start = 0; //디비의 select 시작번호
	int end = 10; //시작번호로 부터 가져올 select 갯수
	int listSize = 0; //현재 읽어온 게시물의 수
	String keyWord = "", keyField = "";
	Vector<Bbs> vlist = null;//getBoardList()메소드의 리턴 타입을 vector<boradBean>으로 선언
	if (request.getParameter("keyWord") != null) {
		keyWord = request.getParameter("keyWord");
		keyField = request.getParameter("keyField");
	}
	if (request.getParameter("reload") != null) {
		if (request.getParameter("reload").equals("true")) {
			keyWord = "";
			keyField = "";
		}
	}
	if (request.getParameter("nowPage") != null) {
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	start = (nowPage * numPerPage) - numPerPage;
	end = start + numPerPage;
	totalRecord = bMgr.getTotalCount(keyField, keyWord);
	totalPage = (int) Math.ceil((double) totalRecord / numPerPage); //전체페이지수
	nowBlock = (int) Math.ceil((double) nowPage / pagePerBlock); //현재블럭 계산
	totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock); //전체블럭계산
%>

<!DOCTYPE html>
<html>
<script type="text/javascript">
	function list() {
		document.listFrm.action = "./list.jsp";
		document.listFrm.submit();
	}
	function pageing(page) {
		document.readFrm.nowPage.value = page;
		document.readFrm.submit();
	}
	function block(value) {
		document.readFrm.nowPage.value = <%=pagePerBlock%> * (value - 1) + 1;
		document.readFrm.submit();
	}
	function read(idx) {
		document.readFrm.idx.value = idx;
		document.readFrm.pagefile.value = "./read.jsp";
		document.readFrm.action = "./main.jsp";
		document.readFrm.submit();
	}
	function check() {
		if (document.searchFrm.keyWord.value == "") {
			alert("검색어를 입력하세요.");
			document.searchFrm.keyWord.focus();
			return;
		}
		document.searchFrm.submit();
	}
</script>

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
              <a class="btn  btn btn-sm" href="board.jsp" role="button">계약서 작성</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/validation.jsp" role="button">검증하기</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm"><%=session.getAttribute("userID")%> 님</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/logoutAction.jsp" role="button">로그아웃</a>
            </li>
          </ul>

		</div>
	</nav>
	
	<!-- 2. 문서파일 올리는 게시판 부분  -->
	<div class="jumbotron" style="padding-top: 20px;">
		<div class="container">
			<div class="row">
			<table>
				
			</table>
			<table class="table table-striped" style="text-align: center; border:1px solid #dddddd">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">번호</th>
							<th style="background-color: #eeeeee; text-align: center;">문서 제목</th>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
						<%
								bMgr = new BbsDAO();
							if(session.getAttribute("nickname")==null){
						    	vlist = bMgr.getBoardList(keyField, keyWord, start, end, (String)session.getAttribute("userID"));
						    }else{
						    	vlist = bMgr.getBoardList(keyField, keyWord, start, end, (String)session.getAttribute("nickname"));
						    }
							listSize = vlist.size();//브라우저 화면에 보여질 게시물갯수
							
							if (vlist.isEmpty()) {
						%>
						<tr> <td colspan="4" align="center">등록된 게시물이 없습니다.</td> </tr>
						<%
							} else {
							for(int i = 0; i < vlist.size(); i++) {
								Bbs bean = vlist.get(i);
								int bbsID = bean.getBbsID();
								String bbsTitle = bean.getBbsTitle();
							 	String userID = bean.getUserID();
								String bbsDate = bean.getBbsDate();
						%>
						<tr>
						<th style="background-color: #eeeeee; text-align: center;"> <%=bbsID%> </th>
						<th style="background-color: #eeeeee; text-align: center;"> <%=bbsTitle%> </th>
						<th style="background-color: #eeeeee; text-align: center;"> <%=userID%> </th>
						<th style="background-color: #eeeeee; text-align: center;"> <%=bbsDate%> </th>
						</tr>
						<%
							} // for 
						} //if
						%>
					</thead>
				</table>
				<a href="../../../../../../../Desktop/BlockCahin_Contract-master/BlockCahin_Contract-master/BBSTEST/WebContent/write.jsp" class="btn btn-primary pull-right">+</a>
				
			</div>			
		</div>
	</div>

		<div align="center">
			<div class="paging">
				<ol>
				<%
					int pageStart = (nowBlock - 1) * pagePerBlock + 1; //하단 페이지 시작번호
					int pageEnd = ((pageStart + pagePerBlock) < totalPage) ? (pageStart + pagePerBlock) : totalPage + 1;
					//하단 페이지 끝번호
					if (totalPage != 0) {
					if (nowBlock > 1) {
				%>
				
				<a href="javascript:block('<%=nowBlock - 1%>')">[이전]</a> <%} %> 
					<% for (; pageStart < pageEnd; pageStart++) { %>
				<a href="javascript:pageing('<%=pageStart%>')"> 
					<% if (pageStart == nowPage) { %>
				<font color="orange"><Strong>
					<% } %> [<%=pageStart%>]
					<% if (pageStart == nowPage) {%></Strong></font>
					<%} %></a>
					<% } //for
					%>
					<% if (totalBlock > nowBlock) { %>
				<a href="javascript:block('<%=nowBlock + 1%>')">[다음]</a>
					<%} %>
					<%}%>
				</ol>
				</div>
					<!-- <form name="listFrm" method="post">
					<div class="boardSearch">
						<select name="keyField">
						<option value="title" selected="selected">제목</option>
						<option value="mem_name">글쓴이</option>
						</select>
						<input type="text" id="txt" name="keyWord" />
						<input type="button" value="검색" onClick="javascript:check()" />
						<input type="hidden" name="nowPage" value="1">
					
						<input type="hidden" name="reload" value="true">
						<input type="hidden" name="nowPage" value="1">

						</div>
					</form> -->
					<form name="readFrm" method="get">
						<input type="hidden" name="pagefile" value="board">
						<input type="hidden" name="idx"> <input type="hidden" name="nowPage" value="<%=nowPage%>">
						<input type="hidden" name="keyField" value="<%=keyField%>">
						<input type="hidden" name="keyWord" value="<%=keyWord%>">
					</form>
			</div>
</body>
</html>