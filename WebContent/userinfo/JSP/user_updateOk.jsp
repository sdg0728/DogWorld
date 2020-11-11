<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:set var="login" value='<%=(String)session.getAttribute("login")%>' />

<c:choose>
	<c:when test="${change == 0 }">
		<script>
			alert("수정 실패 !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			location.href = "../../index.do";
		</script>
	</c:otherwise>
</c:choose>
