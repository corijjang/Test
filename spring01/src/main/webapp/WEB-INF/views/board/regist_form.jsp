<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="/css/mystyle.css">
<script src="/js/myscript.js"></script>
<script>
$(function() {
	$("#fileDrop").on("dragenter dragover", function(e) {
		e.preventDefault();
// 		console.log("엔터, 오버");
	});
	$("#fileDrop").on("drop", function(e) {
		e.preventDefault();
		var file = e.originalEvent.dataTransfer.files[0];
		console.log(file);
		// <form method="post" enctype="multipart/form-data">
		// get: 1KB 이내, multipart/form-data: 바이너리
		// processData : false - 데이터를 쿼리스트링(?a=b&c=d)으로 보내지 않겠다
		// -> post
		// contentType: false - 데이터를 텍스트 데이터가 아닌 바이너리로 설정
		// -> enctype="multipart/form-data"
		var formData = new FormData(); // <form>
		formData.append("file", file); // <input type="file" name="file">
		var url = "/upload/uploadAjax"; // Controller의 RequestMapping
		
		$.ajax({
			"processData"	: false,
			"contentType"	: false,
			"method"		: "post",
			"url"			: url,
			"data"			: formData,
			"success"		: function(rData) { 
				console.log(rData);
				if (rData == "fail") {
					alert("잘못된 형식의 파일입니다.");
					return;
				}
				
				var div = $("#uploadedList").prev().clone();
				div.attr("data-filename", rData);
				
				console.log(div);
				var underIndex = rData.indexOf("_");
				var fileName = rData.substring(underIndex + 1);
				div.find("span").text(fileName);
				var result = isImage(fileName); 
				if (result == true) {
					var img = div.find("img");
					img.attr("src", "/upload/displayImage?fileName=" + rData);
				}
				
				var a = div.find("a");
				a.attr("data-filename", rData);
				
				
				$("#uploadedList").append(div);
				div.show(1000);
			}
		});
	}); // $("#fileDrop").on("drop"
			
	$("#frmRegist").submit(function() {
		var divs = $("#uploadedList > .divUploaded");
		console.log("divs:", divs);
		divs.each(function(index) {
			var filename = $(this).attr("data-filename");
			var inputHtml = "<input type='hidden' name='files[" + index +"]' value='" + filename + "'>";
			$("#frmRegist").prepend(inputHtml);
		});
// 		return false;
	});		
	
	$("#uploadedList").on("click", ".a_times", function(e) {
		e.preventDefault();
		var that = $(this);
		var filename = that.attr("data-filename");
		console.log(filename);
		var url = "/upload/deleteFile?fileName=" + filename;
		$.get(url, function(rData) {
			console.log(rData);
			if (rData == "success") {
				that.parent().hide(1000, function() {
					that.parent().remove();
				});
				
			} else {
				alert("파일 삭제에 실패했습니다.");
			}
		});
	});
}); // $(function)
</script>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>글쓰기 양식</h2>
				<p>
					<a class="btn btn-primary btn-large" href="/board/list_all">글목록</a>
				</p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form role="form" action="/board/regist_run" id="frmRegist"
				method="post">
				
				<div class="form-group">
					<label for="userid">아이디</label>
					<input type="text" class="form-control" 
						id="userid" name="userid" required="required"/>
				</div>
				<div class="form-group">
					<label for="title">글제목</label>
					<input type="text" class="form-control" 
						id="title" name="title" required="required"/>
				</div>
				<div class="form-group">
					<label for="content">글내용</label>
					<textarea class="form-control" 
						id="content" name="content"></textarea>
				</div>
				
				
				<!-- 파일 업로드 영역 -->
				<div>
					<label>첨부할 파일을 드래그 &amp; 드롭하세요</label>
					<div id="fileDrop"></div>
				</div>
				
				<!-- 업로드할 항목의 템플릿 -->
				<div style="display:none"
					class="divUploaded">
					<img src="/img/default.png" height="100"><br>
					<span>default.png</span>
					<a href="#" class="a_times">&times;</a>
				</div>
				<!-- 업로드한 파일들 -->
				<div id="uploadedList">
				
				</div>
				
				<div style="clear:both;">
					<button type="submit" class="btn btn-primary">
						작성 완료
					</button>
				</div>
			</form>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>