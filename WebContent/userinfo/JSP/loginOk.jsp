<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.userinfo.UserDTO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%UserDTO dto = (UserDTO)request.getAttribute("DTO"); %>

<c:set var="dto" value='<%=dto %>'/>
<c:if test="${dto == null }">
	<script type="text/javascript">
		alert("로그인 실패!");
		history.back(); //브라우저가 기억하는 직전 페이지
	</script>
</c:if>

<c:if test="${ dto != null }">
	<script>
		alert("로그인 성공!");
	<%
		session.setAttribute("login", dto.getId());
		String login = (String)session.getAttribute("login");
		String password = dto.getPw();
		if(login.equals("admin") && password.equals("admin")){
	%>
			location.href = "../../purchase/JSP/admin.do";
	<%
		} else{
	%>
			location.href = "../../index.do";
	<%
		}
	%>
	</script>
</c:if>