<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value='<%=(int)request.getAttribute("updateGoods") %>' />

<c:if test="${cnt == 0 }">
<script>
	alert("수정 실패");
	history.back();
</script>
</c:if>

<c:if test="${cnt == 1 }">
<script>
	alert("수정 성공");
	location.href = "admin.do";
</script>
</c:if>