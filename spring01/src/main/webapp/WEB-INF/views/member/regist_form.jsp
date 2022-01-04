<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>회원 가입 양식</h2>
				<p>
					<a class="btn btn-primary btn-large" href="/member/list_all">회원 목록</a>
				</p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form role="form" action="/member/regist_run" 
				method="post">
				<div class="form-group">
					<label for="userid">아이디</label>
					<input type="text" class="form-control" 
						id="userid" name="userid" />
				</div>
				<div class="form-group">
					<label for="userpw">패스워드</label>
					<input type="password" class="form-control" 
						id="userpw" name="userpw" />
				</div>
				<div class="form-group">
					<label for="username">이름</label>
					<input type="text" class="form-control" 
						id="username" name="username" />
				</div>
				<div class="form-group">
					<label for="email">이메일</label>
					<input type="email" class="form-control" 
						id="email" name="email" />
				</div>
				
				<button type="submit" class="btn btn-primary">
					가입 완료
				</button>
			</form>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>