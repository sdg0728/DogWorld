<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:choose>
	<c:when test="${sign == 0 }">
		<script>
			alert("회원실패 !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			alert("회원 성공 !");
			location.href = "../../index.do";
		</script>
	</c:otherwise>
</c:choose>
