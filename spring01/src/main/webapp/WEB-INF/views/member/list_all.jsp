<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>
					회원 목록
				</h2>
				
				<p>
					<a class="btn btn-primary btn-large" href="/member/regist_form">회원 가입</a>
				</p>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
								<th>아이디</th>
								<th>패스워드</th>
								<th>이름</th>
								<th>이메일</th>
								<th>가입일</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="memberVo">
							<tr>
								<td>${memberVo.userid}</td>
								<td>${memberVo.userpw}</td>
								<td>${memberVo.username}</td>
								<td>${memberVo.email}</td>
								<td>${memberVo.regdate}</td>
								<td>${memberVo.updatedate}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>