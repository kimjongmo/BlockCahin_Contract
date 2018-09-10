<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
      String userID = null;
      if (session.getAttribute("userID") != null) {
         userID = (String) session.getAttribute("userID");
      }
%>
<%@page import="user.KakaoMember" %> <!-- 카카오 id불러오기 위한 것  -->
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
   function pageing(page) {
      document.readFrm.nowPage.value = page;
      document.readFrm.submit();
   }
   function block(value) {
      document.readFrm.nowPage.value = <%=pagePerBlock%> * (value - 1) + 1;
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
   function go_pop(userID, filename){
	   window.name="검증 하기";
	  window.open("compare.jsp?userID="+userID+"&filename="+filename,"new","width=500 ,height=360, resizable=no,scrollbars=no, status=no,location=no,directories=no;");
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
<%if(userID == null) { %>
            
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="login.jsp" role="button">로그인</a>
            </li>

<%}else {%>
         <li class="nav-item">
              <a class="btn  btn btn-sm" href="board.jsp" role="button">계약서 작성</a>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="myPage.jsp" role="button">검증하기</a>
            </li>
            <li class="nav-item">
              <font size="2em" color="orange"><%=session.getAttribute("userID")%> 님</font>
            </li>
            <li class="nav-item">
              <a class="btn  btn btn-sm" href="logoutAction.jsp" role="button">로그아웃</a>
            </li>
          </ul>
<% }%>
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
                     <th style="background-color: #eeeeee; text-align: center;">검증</th>
                  </tr>
                  <%
                     bMgr = new BbsDAO();
                      vlist = bMgr.getBoardList2(keyField, keyWord, start, end);
                     listSize = vlist.size();//브라우저 화면에 보여질 게시물갯수
                     if (vlist.isEmpty()) {
                  %>
                  <tr> <td colspan="4" align="center">등록된 게시물이 없습니다.</td> </tr>
                  <%
                     } else {
                     for(int i = 0; i < vlist.size(); i++) {
                        Bbs bean = vlist.get(i);
                        int bbsID = bean.getBbsID();//순번
                        String filename = bean.getFileName();//파일 명
                         userID = bean.getUserID();//유저 아이디
                        String bbsDate = bean.getBbsDate();//일자
                  %>
                  <tr>
                  <th style="background-color: #eeeeee; text-align: center;"> <%=bbsID%> </th>
                  <th style="background-color: #eeeeee; text-align: center;"> <%=filename%> </th>
                  <th style="background-color: #eeeeee; text-align: center;"> <%=userID%> </th>
                  <th style="background-color: #eeeeee; text-align: center;"> <%=bbsDate%> </th>
                  <th style="background-color: #eeeeee; text-align: center;"><button onclick="go_pop('<%=userID%>','<%=filename%>')" class="btn btn-primary">검증</button></th>
                  </tr>
                  <%
                     } // for 
                  } //if
                  %>
               </thead>
            </table>

            
</body>
</html>