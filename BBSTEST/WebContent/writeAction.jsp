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
	//���� �̸� ��������
	String str = request.getParameter("fileN");
	String[] array = str.split("\\\\");
	String fileName = array[array.length-1];
	//�ߺ� üũ
	if(bda.check(userID,fileName)){
		int result = bda.insert(request,userID);
		
		if(result ==2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('������ �����ϴµ� ������ ������ϴ� �ٽ� �õ��� �ּ���.')");
			script.println("location.href=write.jsp");
			script.println("</script>");
		}else{//����
			String filepath="C:/file/"+userID+"/"+fileName;
		
		 	bda.insertBlock(filepath);
		 	
			response.sendRedirect("./board.jsp");
		}
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�ߺ��Ǵ� �̸��� �ֽ��ϴ�.')");
		script.println("location.href=write.jsp");
		script.println("</script>");
	}
	
%>