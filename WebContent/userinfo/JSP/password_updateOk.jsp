<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:choose>
	<c:when test="${change == 0 }">
		<script>
			alert("수정 실패 !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			alert("패스워드 변경 완료 !");
			location.href = "../../index.do";
		</script>
	</c:otherwise>
</c:choose>
