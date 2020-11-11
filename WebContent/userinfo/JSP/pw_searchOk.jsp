<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("다시 입력해 주세요. !!!");
			history.back();
		</script>
	</c:when>

	<c:otherwise>
		<script>
			alert("Email에서 PW를 확인하실 수 있습니다 !!!");
			location.href = "../../index.do";
		</script>
	</c:otherwise>
</c:choose>
