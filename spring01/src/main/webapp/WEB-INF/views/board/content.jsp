<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="/WEB-INF/tlds/myfunc.tld" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<link rel="stylesheet" href="/css/mystyle.css">
<script src="/js/myscript.js"></script>

<script>
// $(document).ready(function())
$(function() {
	var message = "${message}";
	if (message == "modify_success") {
		alert("글 수정이 완료 되었습니다.");
	}
	
	// 삭제 버튼
	$("#btnDelete").click(function() {
		var bno = $(this).attr("data-bno");
		console.log("bno:", bno);
// 		var input_bno = '<input type="hidden" name="bno" value="' + bno + '">';
// 		$("#frmPaging").append(input_bno);
		$("#frmPaging > input[name=bno]").val(bno);
		$("#frmPaging").attr("action", "/board/deleteBoard");
		$("#frmPaging").submit();
	});
	$("#btnModify").click(function() {
// 		console.log($(".modify"));
		// class가 modify(글제목, 글내용)에 대해서 읽기 전용 해제
		$(".modify").prop("readonly", false);
		$("#btnModifyOk").fadeIn(500); // show, slideDown
		$(this).fadeOut(500); // hide, slideUp
	});
	
	// 글목록 버튼
	$("#btnList").click(function(e) {
		e.preventDefault();
		$("#frmPaging").submit();
	});
	
	// 답글 버튼
	$("#btnReply").click(function() {
		var re_group = "${boardVo.re_group}";
		var re_seq = "${boardVo.re_seq}";
		var re_level = "${boardVo.re_level}";
		var loc = "/board/reply_form?";
		loc += "re_group=" + re_group;
		loc += "&re_seq=" + re_seq;
		loc += "&re_level=" + re_level;
		location.href = loc;
	});
	
	// 댓글보기 버튼
	$("#btnCommentList").click(function() {
		var url = "/comment/commentList/${boardVo.bno}";
		$.get(url, function(rData) {
			console.log(rData); // [{}, {}]
			$("#commentList").empty();
			$.each(rData, function() {
				/*
				var tr = "<tr>";
				tr += "<td>" + this.cno + "</td>";
				tr += "<td>" + this.content + "</td>";
				tr += "<td>" + this.userid + "</td>";
				tr += "<td>" + this.regdate + "</td>";
				tr += "</tr>";
				$("#commentList").append(tr);
				*/
				var tr = $("#cloneTable tr").clone();
				tr.find("td").eq(0).text(this.cno);
				tr.find("td").eq(1).text(this.content);
				tr.find("td").eq(2).text(this.userid);
				tr.find("td").eq(3).text(changeDateString(this.regdate));
				tr.find(".btnCommentDelete").attr("data-cno", this.cno);
				$("#commentList").append(tr);
			});
			$("#commentTable").show(1000);
			
		});
	});
	
	// 댓글 쓰기 버튼
	$("#btnCommentInsert").click(function() {
		var content = $("#c_content").val();
		var userid = $("#c_userid").val();
		var sData = {
				"content"	: content,
				"userid"	: userid,
				"bno"		: "${boardVo.bno}"
		};
		console.log("sData", sData);
		var url = "/comment/insertComment";
		$.post(url, sData, function(rData) {
			console.log(rData); // success
			if (rData == "success") {
				// trigger - 방아쇠(연쇄반응)
				// 댓글보기 버튼을 클릭 시킴
				$("#btnCommentList").trigger("click");
				$("#c_content").val("");
				$("#c_userid").val("");
			}
		});
	});
	
	// 최초 로딩시(document.ready)에 화면에 없는 위젯에 대해서 이벤트를 설정할 때는
	// 최초 로딩시에 알고 있는 부모 위젯을 대상으로 이벤트 설정
	// 메서드는 on을 사용
	
	// 댓글 수정 버튼
	$("#commentList").on("click", ".btnCommentModify", function() {
		var cno = $(this).parent().parent().find("td").eq(0).text();
		var content = $(this).parent().parent().find("td").eq(1).text();
		var userid = $(this).parent().parent().find("td").eq(2).text();
		$("#modalCno").val(cno);
		$("#modalContent").val(content);
		$("#modalUserid").val(userid);
		$("#modal-331105").trigger("click");
	});
	
	// 댓글 삭제 버튼
	$("#commentList").on("click", ".btnCommentDelete", function() {
		var cno = $(this).attr("data-cno");
		console.log(cno);
		var url = "/comment/deleteComment/" + cno + "/${boardVo.bno}";
		$.get(url, function(rData) {
			console.log(rData); // success
			$("#btnCommentList").trigger("click");
		});
	});
	
	// 모달창 수정 버튼
	$("#btnModalModify").click(function() {
		var cno = $("#modalCno").val();
		var content = $("#modalContent").val();
		var userid = $("#modalUserid").val();
		
		var url = "/comment/updateComment";
		var sData = {
			"cno"	  : cno,
			"content" : content,
			"userid"  : userid
		};
		
		$.post(url, sData, function(rData) {
			console.log(rData);
			if (rData == "success") {
				$("#btnModalClose").trigger("click");
				$("#btnCommentList").trigger("click");
			}
		});
	});
});
</script>

<%@ include file="/WEB-INF/views/include/paging_form.jsp" %>


<div class="container-fluid">
	<!-- 댓글 수정 모달 창 -->
	<div class="row">
		<div class="col-md-12">
			 <a id="modal-331105" href="#modal-container-331105" role="button" class="btn" data-toggle="modal"
			 	style="display:none">Launch demo modal</a>
			
			<div class="modal fade" id="modal-container-331105" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">
								댓글 수정
							</h5> 
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" id="modalCno">
							<label for="modalContent">댓글내용</label>
							<input type="text" class="form-control"
								id="modalContent">
							<label for="modalUserid">아이디</label>
							<input type="text" class="form-control"
								id="modalUserid">
						</div>
						<div class="modal-footer">
							 
							<button type="button" class="btn btn-primary"
								id="btnModalModify">수정완료</button> 
							<button type="button" class="btn btn-secondary" data-dismiss="modal"
								id="btnModalClose">닫기</button>
						</div>
					</div>
					
				</div>
				
			</div>
			
		</div>
	</div>
	<!-- // 댓글 수정 모달 창 -->


	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글 상세보기</h2>
				<p>
					<a class="btn btn-primary btn-large" id="btnList"
					   href="#">글목록</a>
				</p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form id="myform" role="form" action="/board/modify_run" 
				method="post">
				<input type="hidden" name="bno" value="${boardVo.bno}">
				<input type="hidden" name="page" value="${param.page}">
				<input type="hidden" name="perPage" value="${param.perPage}">
				<input type="hidden" name="searchType" value="${param.searchType}">
				<input type="hidden" name="keyword" value="${param.keyword}">
				<div class="form-group">
					<label for="userid">아이디</label>
					<input type="text" class="form-control" 
						id="userid" required="required"
						value="${boardVo.userid}" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label for="title">글제목</label>
					<input type="text" class="form-control modify" 
						id="title" name="title" required="required"
						value="${boardVo.title}" readonly="readonly"/>
				</div>
				<div class="form-group">
					<label for="content">글내용</label>
					<textarea class="form-control modify" 
						id="content" name="content"
						readonly="readonly">${boardVo.content}</textarea>
				</div>
				<div class="form-group">
					<label for="title">작성일</label>
					<input type="text" class="form-control" 
						id="title" required="required"
						value="${boardVo.regdate}" readonly="readonly"/>
				</div>
				
				<div id="uploadedList">
				<c:forEach items="${boardVo.files}" var="filename">
					<div class="divUploaded">
						<img height="100" class="img-rounded"
						<c:choose>
							<c:when test="${myfn:isImage(filename)}">
								src="/upload/displayImage?fileName=${filename}"
							</c:when>
							<c:otherwise>
								src="/img/default.png"
							</c:otherwise>
						</c:choose>
						><br>
						<span><a href="#">${myfn:getShortName(filename)}</a></span>
					</div>
				</c:forEach>
				</div>
				<div style="clear:both">
					<button type="button" class="btn btn-warning"
						id="btnModify">수정</button>
					<button type="submit" class="btn btn-success"
						id="btnModifyOk" style="display:none">수정완료</button>
					<button type="button" class="btn btn-danger"
						id="btnDelete" data-bno="${boardVo.bno}">삭제</button>
					<button type="button" class="btn btn-info"
						id="btnReply" data-bno="${boardVo.bno}">답글</button>
					<span style="font-size:50px;cursor:pointer;"
						id="spanHeart">♥</span>
				</div>	
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<button type="button" class="btn btn-sm btn-primary"
				id="btnCommentList">댓글보기</button>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-8">
			<input type="text" class="form-control"
				placeholder="댓글을 입력하세요" id="c_content">
		</div>
		<div class="col-md-2">
			<input type="text" class="form-control"
				placeholder="아이디를 입력하세요" id="c_userid">
		</div>
		<div class="col-md-2">
			<button type="button" class="btn btn-sm btn-primary"
				id="btnCommentInsert">완료</button>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<table style="display:none" id="cloneTable">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><button type="button" class="btn btn-sm btn-warning btnCommentModify">수정</button></td>
					<td><button type="button" class="btn btn-sm btn-danger btnCommentDelete">삭제</button></td>
				</tr>
			</table>
			<table class="table" style="display:none" id="commentTable">
				<thead>
					<th>#</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정</th>
					<th>삭제</th>
				</thead>
				<tbody id="commentList">
					
				</tbody>
			</table>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>