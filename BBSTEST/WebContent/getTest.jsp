<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bbs.BbsDAO" %>
<%
	request.setCharacterEncoding("utf-8");

	BbsDAO db =new BbsDAO();
	String userID = (String)session.getAttribute("userID");
	db.getBlock("2장-데이터타입.pdf", userID);
%>