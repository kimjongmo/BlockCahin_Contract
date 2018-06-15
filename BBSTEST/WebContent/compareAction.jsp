<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bbs.BbsDAO"%>

<%
	request.setCharacterEncoding("UTF-8");

	String userID = request.getParameter("userID");
	String filename = request.getParameter("filename");
	
	BbsDAO db = new BbsDAO();
	String result = db.validate(request,userID,filename);
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('"+result+"')");
	script.println("</script>");
	
%>