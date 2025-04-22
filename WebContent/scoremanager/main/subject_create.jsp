<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>



<form action="SubjectCreateExecute.action">
	<div class="mt-2 text-warning">${errors.get("1")}</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="cd">科目コード</label>
		<input class="form-control" type="text" value="${cd }" id="cd"  name="cd" maxlength=10 required>
	</div>
	<div class="mt-2 text-warning">${errors.get("2")}</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="name" >科目名</label>
		<input class="form-control" type="text" value="${name }" id="name" name="name" maxlength=10 required>
	</div>

	<input class="btn btn-secondary" type="submit" value="登録して終了">
</form>
<a href="SubjectList.action">戻る</a>


</section>
</c:param>
</c:import>