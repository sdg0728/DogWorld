<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="follower" value='<%=request.getAttribute("follower") %>' />
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />
<c:choose>
	<c:when  test = "${follower == 0 }">
		<script>
			
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			
		location.href = "myfeed.do?user_id=${user_id}";
			</script>
	</c:otherwise>
</c:choose>



















    
    


















    
    