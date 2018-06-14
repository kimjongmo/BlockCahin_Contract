<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import = "bbs.BbsDAO" %>
<%@ page import = "bbs.Bbs" %>

<%
	request.setCharacterEncoding("utf-8");%>
<%
	BbsDAO bda = new BbsDAO();
	String userID = (String)session.getAttribute("userID");
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
			String filepath = "C:/file/"+userID+"/"+fileName;
			//블록 만들어서 저장 하는 부분 bda.insertBlock();
			bda.insertBlock(filepath);
			//test
			String[] json = bda.getBlock(fileName, userID);
			System.out.println("BlockName:"+json[0]+",\tDate:"+json[1]+",\tHash:"+json[2]);
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