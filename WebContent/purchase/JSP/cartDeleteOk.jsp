<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cnt" value='<%=request.getAttribute("cartDelete") %>'/>

<c:if test="${cnt == 0 }">
<script>
	alert("삭제에 실패했습니다.");
	history.back();
</script>
</c:if>

<c:if test="${cnt != 0 }">
<script>
	location.href = "basket.do";
</script>
</c:if>