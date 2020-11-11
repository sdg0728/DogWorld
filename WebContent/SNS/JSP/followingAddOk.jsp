<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="cnt" value='<%=request.getAttribute("followadd") %>'/>

<c:choose>
	<c:when test = "${cnt == 0 }">
		<script>
			alert("팔로우하고 있는 회원입니다.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			history.back();
		</script>
	</c:otherwise>
</c:choose>




















    
    