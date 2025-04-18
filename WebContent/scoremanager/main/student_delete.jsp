<%-- 学生情報削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報削除</h2>

<p class="bg-danger bg-opacity-10">以下の学生情報を削除しますか？</p>

<div class="d-flex flex-column mb-3">
	<label class="form-label" >学生番号</label>
	<div class="form-control border-0 ps-1">${no}</div>
</div>

<div class="d-flex flex-column mb-3">
	<label class="form-label">氏名</label>
	<div class="form-control border-0 ps-1">${name}</div>
</div>
<div class="d-flex flex-column mb-3">
	<label class="form-label">クラス</label>
	<div class="form-control border-0 ps-1">${class_num}</div>
</div>

<div class="d-flex justify-content-end gap-1">
	<form action="StudentDeleteExcite.action" method="post" class="d-inline">
		<input type="hidden" name="no" value="${no }">
		<input class="btn btn-danger" type="submit" value="削除する">
	</form>

	<form action="StudentList.action" method="get">
	    <input class="btn btn-secondary" type="submit" value="キャンセル">
	</form>
</div>


</section>
</c:param>
</c:import>