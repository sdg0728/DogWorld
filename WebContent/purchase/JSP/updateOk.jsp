<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value='<%=(int)request.getAttribute("update") %>' />
<c:set var="goods_id" value='<%=(int)request.getAttribute("goods_id") %>' />

<c:if test="${cnt == 0 }">
	<script>
		alert("수정에 실패했습니다.");
		history.back();
	</script>
</c:if>

<c:if test="${cnt != 0 }">
	<script>
		location.href = "goods_detail.do?goods_id="+${goods_id };
	</script>
</c:if>
