<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import = "bbs.BbsDAO" %>
<%@ page import = "bbs.Bbs" %>

<%request.setCharacterEncoding("utf-8");%>
<%
	BbsDAO bda = new BbsDAO();
	int result = bda.insert(request);
	if(result == 0){//���� �̸��� �̹� ����
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�̹� �����ϴ� �����Դϴ�.')");
		script.println("</script>");
		response.sendRedirect("./write.jsp");
	}else{//����
		response.sendRedirect("./board.jsp");
	}
%>