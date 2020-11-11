<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cnt" value='<%=(int)request.getAttribute("updateGoods") %>' />

<c:if test="">
<script>
	alert("수정하지 못했습니다.");
	history.back();
</script>
</c:if>

<c:if test="">
<script>
	location.href = "admin.do";
</script>
</c:if>