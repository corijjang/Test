<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script>
$(function() {
	var msg = "${msg}"; // fail
	if (msg == "fail") {
		alert("로그인 실패. 아이디와 비밀번호를 다시 확인해주세요.");
	}
	
	$("#btnCheckDupId").click(function() {
		var url = "/checkDupId/" + $("#userid").val();
		$.get(url, function(rData) {
			console.log(rData);
			if (rData == "used") {
				$("#btnCheckDupId").next().html("사용중인 아이디입니다.");
				$("#btnCheckDupId").next().css("color", "red");
			} else {
				$("#btnCheckDupId").next().html("사용가능한 아이디입니다.");
				$("#btnCheckDupId").next().css("color", "blue");
			}
		});
	});
});
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>로그인</h2>
				<p>아이디와 패스워드를 정확히 입력해 주세요.</p>
				<p><a class="btn btn-primary btn-large" href="#">회원가입</a></p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form role="form" action="/login_run" method="post">
				<div class="form-group">
					<label for="userid">아이디</label>
					<input type="text" class="form-control" 
						id="userid" name="userid" required="required"/>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-warning"
						id="btnCheckDupId">아이디 중복체크</button>
					<span></span>
				</div>
				<div class="form-group">
					<label for="userpw">패스워드</label>
					<input type="password" class="form-control" 
						id="userpw" name="userpw"/>
				</div>
				
				<div class="checkbox">
					<label>
						<input type="checkbox" 
							name="saveId"/> 아이디 저장
					</label>
				</div> 
				<button type="submit" class="btn btn-primary">로그인</button>
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>