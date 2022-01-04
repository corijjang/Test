<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>doI.jsp</title>
<script>
	var msg = "${sessionScope.msg}";
	if (msg == "success") {
		alert("처리 완료");
	}
</script>
</head>
<body>
	<h1>doI.jsp</h1>
</body>
</html>
<%
	session.removeAttribute("msg");
%>