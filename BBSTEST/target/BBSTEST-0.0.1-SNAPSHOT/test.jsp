<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="user.KakaoMember"%>
<%
   request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String kaccount_email = request.getParameter("kaccount_email");
	String nickname = request.getParameter("nickname");
	KakaoMember km = new KakaoMember();
	
	km.setId(id);
	km.setKaccount_email(kaccount_email);
	km.setNickname(nickname);
	
    UserDAO ud = new UserDAO();
	ud.insertMember(km); 
	
	if(session.getAttribute("nickname") == null)
	{
		session.setAttribute("userID", nickname);
    	response.sendRedirect("index.jsp");
    }
	
%>