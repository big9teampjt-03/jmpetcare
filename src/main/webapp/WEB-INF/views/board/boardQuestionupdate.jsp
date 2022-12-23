<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<div class = "container">
<br>
<h3>${board.username } 의 글 수정하기</h3>
<br>
<form action ="boardQuestionupdate" method="post"  enctype="multipart/form-data">
<input type="hidden" name="questionID" id="questionID" value="${board.questionID }" />
<input type="hidden" name="questionimage" id="questionimage" value="${board.questionimage }" />
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.username }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content">${board.content }</textarea>
	</div>
	<div class="form-group">
      <label for="uploadquestion">파일</label>
      <input type="file" class="form-control" id="uploadquestion" placeholder="Enter upload" name="uploadquestion" >
    </div>
    <input type="text" class="form-control"
			id="questionimage" name="questionimage" value="기존파일명:${board.questionimage }" readonly="readonly">
	<div class="form-group text-right">
		<button type="button" class="btn btn-secondary btn-sm" id="btnModify">수정하기</button>
	</div>
</form>
</div>

<script>
$("#btnModify").click(function(){
	data={
			"questionID":$("#questionID").val(),
			"title":$("#title").val(),
			"content":$("#content").val(),
			//"uploadquestion":$("#uploadquestion").val(),  
			//"questionimage":$("#questionimage").val()
	}
	$.ajax({
		type:'put',
		url:"/board/boardQuestionupdate",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		success:function(resp){
			alert("수정완료")
			location.href="/board/boardQuestion";
		},
		error:function(e){
			alert("수정실패")
		}
	})
})

</script>
<%@ include file="../includes/footer.jsp"%>