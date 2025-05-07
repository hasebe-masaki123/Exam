<%-- 学生情報更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

<form action="StudentUpdateExecute.action" method="post">
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="ent_year">入学年度</label>
		<input class="form-control border-0" type="text" id="ent_year" name="ent_year" value="${ent_year }" readonly>

	</div>

	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="no">学生番号</label>
		<input class="form-control border-0" type="text"  id="no" name="no" value="${no }" readonly>
	</div>

	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="name" >氏名</label>
		<input class="form-control" type="text"  id="name" name="name" value="${name }" maxlength=10 required>
	</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="class_num">クラス</label>
		<select class="form-control" id="class_num" name="class_num">
			<c:forEach var="num" items="${class_num_set }">
				<option value="${num }" <c:if test="${num==class_num }">selected</c:if>>${num }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-check">
		<label class="form-check-label" for="student-check">在学中
			<input class="form-check-input" type="checkbox" id="student-check"  value="true"
			<c:if test="${is_attend }">checked</c:if> />
		</label>
	</div>

	<input class="btn btn-secondary" type="submit" name="login" value="登録して終了">
</form>

</section>
</c:param>
</c:import>