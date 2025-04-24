<%@ page contentType="text/html; charset=UTF-8"

<form action="SubjectUpdateExcute.action"  method="get">
<h2>科目情報変更</h2>
<label for="cd">科目コード</label>
<select subject="subject">
<c:forEach var="subject" items="${list }">
<option value="${subject.subject }">${subject.subject </option>
</c:forEach>
</select>
<input type="submit" value="変更">
</form>