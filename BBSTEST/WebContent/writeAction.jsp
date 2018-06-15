<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import = "bbs.BbsDAO" %>
<%@ page import = "bbs.Bbs" %>

<%
	request.setCharacterEncoding("utf-8");%>
<%

	String userID="";
	BbsDAO bda = new BbsDAO();
	System.out.println("userID="+session.getAttribute("userID")+",nickname="+session.getAttribute("nickname"));
	
	
	if(session.getAttribute("nickname")==null){//nickname null이라는 것은 카카오로 접속 하지 않음을 의미
		userID = (String)session.getAttribute("userID");
	}
	else	//nickname이 null이 아니라는 것은 카카오로 접속했다는 것을 의미  참고: ./test.jsp 21~22라인
		userID = (String)session.getAttribute("nickname");////==> id
	
		
	System.out.println(userID);
	//파일 이름 가져오기
	String str = request.getParameter("fileN");
	String[] array = str.split("\\\\");
	String fileName = array[array.length-1];
	//중복 체크
	if(bda.check(userID,fileName)){
		int result = bda.insert(request,userID);
		
		if(result ==2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('파일을 삽입하는데 문제가 생겼습니다 다시 시도해 주세요.')");
			script.println("location.href=write.jsp");
			script.println("</script>");
		}else{//성공
			String filepath="C:/file/"+userID+"/"+fileName;
		
		 	bda.insertBlock(filepath);
		 	
			response.sendRedirect("./board.jsp");
		}
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('중복되는 이름이 있습니다.')");
		script.println("location.href=write.jsp");
		script.println("</script>");
	}
	
%>