<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cnt" value='<%=(int)request.getAttribute("cardReg") %>' />

<c:if test="${cnt == 0 }">
	<script>
		alert("등록하지 못했습니다.");
		history.back();
	</script>
</c:if>

<c:if test="${cnt != 0 }">
	<script>
		alert("카드를 등록했습니다.");
		history.back();
	</script>
</c:if>
