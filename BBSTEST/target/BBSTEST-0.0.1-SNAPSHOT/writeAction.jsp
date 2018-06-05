<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "bbs.BbsDAO" %>
<%@ page import = "bbs.Bbs" %>

<%request.setCharacterEncoding("utf-8");%>
<%
	BbsDAO bda = new BbsDAO();
	bda.insert(request);
	response.sendRedirect("./board.jsp");
%>