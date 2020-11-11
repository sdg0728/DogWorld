<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="cnt" value='<%=request.getAttribute("delete") %>' />
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />

<c:choose>
	<c:when test = "${cnt == 0 }">
		<script>
			alert("다시 시도해주세요.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			alert("삭제가 완료되었습니다.");
			location.href = "myfeed.do?user_id=${user_id}";
		</script>
	</c:otherwise>
</c:choose>



















    
    