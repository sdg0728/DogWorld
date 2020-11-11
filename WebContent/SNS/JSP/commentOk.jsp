<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="cnt" value='<%=request.getAttribute("p_list") %>'/>
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />
<c:choose>
	<c:when test = "${cnt == 0 }">
		<script>
			alert("수정이 실패되었습니다.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			location.href = "myfeed.do?user_id=${user_id}";
		</script>
	</c:otherwise>
</c:choose>




















    
    