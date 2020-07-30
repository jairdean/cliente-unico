<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%
	request.getSession().invalidate();
%>
<%
	if(request.getParameter("error")!=null){
		response.sendRedirect(request.getContextPath()+"/pages/index.jsf?error=sin");
	}else{
		response.sendRedirect(request.getContextPath()+"/pages/index.jsf");
	}
%>