<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import = "bbs.BbsDAO" %>
<%@ page import = "bbs.Bbs" %>

<%request.setCharacterEncoding("utf-8");%>
<%
	BbsDAO bda = new BbsDAO();
	int result = bda.insert(request);
	if(result == 0){//파일 이름이 이미 존재
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 파일입니다.')");
		script.println("</script>");
		response.sendRedirect("./write.jsp");
	}else{//성공
		response.sendRedirect("./board.jsp");
	}
%>