<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	String filename = request.getParameter("filename");
	String userID = request.getParameter("userID");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	function submit1(){
		document.form1.action = "./compareAction.jsp?userID="+encodeURI(document.form1.userID.value)+
				"&filename="+encodeURI(document.form1.filename.value);
		document.form1.submit();
	}
</script>
</head>
<body>
파일이름 :	<%=filename %>
유저ID  :<%=userID %>
비교할 파일을 올려주세요
<form  name="form1" method="post" enctype="multipart/form-data">
	<input type="file" name="file" size="50" placeholder="파일" ></input>
	<input type="hidden" name="filename" value="<%=filename%>"></input>
	<input type="hidden" name="userID" value="<%=userID%>"></input>
	<button class="btn btb-primary pull-right" onclick="submit1()">등록</button>
</form>
</body>
</html>