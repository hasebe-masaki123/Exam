<%@ page contentType="text/html; charset=UTF-8" %>

<form action = "SubjectChange" method = "get">
<p>科目情報変更</p>
<select name = "name">
	<c:forEach var = "subject" items = "$ {list}">
		<option value = "${Subject.name }">${Subject.name }</option>
	</c:forEach>
</select>
<input type = "submit" value = "変更">
</form>