<%@page import="beans.SNS.CommentDTO"%>
<%@page import="beans.SNS.WriteDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="post" value='<%=(WriteDTO[])request.getAttribute("list") %>'/>
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />
<c:choose>
	<c:when test = "${post == null}">
		<script>
			alert("없는 정보입니다.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			location.href = "search.do?user_id=${user_id}";
		</script>
	</c:otherwise>
</c:choose>




















    
    