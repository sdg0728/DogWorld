<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("회원탈퇴 실패! 비밀번호를 다시 확인해주세요.");
			location.href = "membership_withdrawal.do";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("회원탈퇴 완료");
			location.href = "../../index.do";
		</script>
		<%
			session.removeAttribute("login");
		%>
	</c:otherwise>
</c:choose>