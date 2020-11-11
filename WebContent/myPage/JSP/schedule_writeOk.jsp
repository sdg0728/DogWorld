<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:choose>
	<c:when test="${wirte == 0 }">
		<script>
			alert("등록 실패 !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			location.href = "schedule.do";
		</script>
	</c:otherwise>
</c:choose>
