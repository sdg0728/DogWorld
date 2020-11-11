<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value='<%=(int)request.getAttribute("cartGo") %>'/>

<c:if test="${cnt == 0 }">
<script>
	history.back();
</script>
</c:if>

<c:if test="${cnt != 0 }">
<script>
	location.href = "purchase.do";
</script>
</c:if>