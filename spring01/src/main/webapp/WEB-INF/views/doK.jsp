<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>doK.jsp</title>
<script>
	var msg = "${msg}";
	if (msg == "success") {
		alert("처리 완료");
	}
</script>
</head>
<body>
	<h1>doK.jsp</h1>
</body>
</html>
