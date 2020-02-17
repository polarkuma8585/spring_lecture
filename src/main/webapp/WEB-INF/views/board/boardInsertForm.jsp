<%@ page contentType="text/html; charset=EUC-KR" %>
<html>
<head><title>게시판</title></head>
<body>

<form method="post" action="boardInsert" encType="multipart/form-data">
<table width="80%" align="center" border=1 cellspacing=0 >
	<tr height=30>
		<td align="center" bgcolor="#FFFF99" width=64>글쓴이</td> 
		<td>&nbsp<input type="text" name="poster" size="20"></td></tr> <!-- 실제 db의 컬럼명과 동일하게 --> 	
	<tr height=30>
		<td align="center" bgcolor="#FFFF99">제목</td>  
		<td>&nbsp<input type="text" name="subject" size="70"></td></tr>
	<tr> 
		<td align="center" bgcolor="#FFFF99">내용</td>  
		<td>&nbsp<textarea name=contents rows="10" cols="70"></textarea></td></tr>	   
	<tr> 
		<td align="center" bgcolor="#FFFF99">첨부파일</td>  
		<td><input type="file" name="attachfile" /></td></tr>
	<tr>
		<td align="center" colspan="2"><input type="submit" value="저장"></td></tr>
	
</table>
</form>
</body>
</html>