<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:useBean id="dao" class="beans.SNS.WriteDAO"/> <%-- DAO bean 생성 --%>
<c:set var="user_id" value='<%=session.getAttribute("login") %>' />
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="dto" class="beans.SNS.WriteDTO"/>
<jsp:setProperty property="*" name="dto"/>

<% // dao 를 사용한 트랜잭션
	//int cnt = dao.insert(subject, content, name);
int cnt = (Integer)request.getAttribute("write");
%>
<c:choose>
	<c:when test = "${cnt == 0 }">
		<script>
			alert("업로드가 실패되었습니다.");
			history.back();
		</script>
	</c:when>
	
	<c:otherwise>
		<script>
			alert("업로드가 완료되었습니다.");
			location.href = "myfeed.do?user_id=${user_id}";
		</script>
	</c:otherwise>
</c:choose>



















    
    