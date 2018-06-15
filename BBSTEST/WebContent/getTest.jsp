<%@page import="java.io.PrintWriter"%>
<%@page import="bbs.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	BbsDAO db = new BbsDAO();
	String[] str=db.getBlock("2장연습문제.pptx", "kfmd1008");
	String[] str1 = str[2].split("\\\"");
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('"+str1[3]+"')");
	script.println("</script>");
%>
