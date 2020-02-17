<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h3>게시글 목록</h3>
 <a href="boardInsertForm">게시글쓰기</a>
 <div class="searchDiv">
	<form name="searchFrm">
		<input type="hidden" name="page" value="1">
		작성자<input name="poster">
		제목 <input name="subject">
		<button>검색</button>		
	</form> 
 </div>
 <c:forEach items="${list }" var="board">
	<div>
		<span>${board.no }</span> 
		<span>${board.poster}</span>
		<span> ${board.subject }</span>
	</div>
</c:forEach>
<div>
${boardDto }
</div>
<div>
	<c:forEach begin="1" end="${boardDto.lastPage }" var="i">
		<a href="#" onclick="goPage(${i})">${i }</a>
	</c:forEach>
	<script>
		function goPage(p){
			searchFrm.page.value = p;
			searchFrm.submit();
		}
	</script>
</div>
</body>
</html>