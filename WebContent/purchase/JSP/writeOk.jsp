<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cnt" value='<%= (Integer)request.getAttribute("insert") %>'/>
<c:set var="goods_id" value='<%= (Integer)request.getAttribute("goods_id") %>'/>

<c:if test="${cnt == 0 }">
	<script type="text/javascript">
		alert("리뷰 작성을 다시 해주세요!");
		history.back(); //브라우저가 기억하는 직전 페이지
	</script>
</c:if>

<c:if test="${cnt != 0 }">
	<script>
		location.href = "goods_detail.do?goods_id="+${goods_id};
	</script>
</c:if>