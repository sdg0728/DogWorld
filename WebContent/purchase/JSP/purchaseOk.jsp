<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value='<%=(int)request.getAttribute("update_purchase") %>'/>

<c:if test="${cnt == 0 }">
	<script>
		alert("결제하지 못했습니다.");
		history.back();
	</script>
</c:if>

<c:if test="${cnt != 0 }">
	<script>
		alert("결제 했습니다.");
		location.href = "goods_list.do?cate=1";
	</script>
</c:if>