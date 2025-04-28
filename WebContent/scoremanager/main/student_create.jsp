<%-- 学生情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>



<form action="StudentCreateExecute.action">
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="ent_year">入学年度</label>
		<select class="form-control" id="ent_year" name="ent_year">
			<option value="0">--------</option>
			<c:forEach var="year" items="${ent_year_set}">
				<option value=${year } <c:if test="${year==ent_year }">selected</c:if>>${year }</option>
			</c:forEach>
		</select>
	</div>
	<div class="mt-2 text-warning">${errors.get("1")}</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="no">学生番号</label>
		<input class="form-control" type="text" value="${no }" id="no"  name="no" maxlength=10 required>
	</div>
	<div class="mt-2 text-warning">${errors.get("2")}</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="name" >氏名</label>
		<input class="form-control" type="text" value="${name }" id="name" name="name" maxlength=10 required>
	</div>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="class_num">クラス</label>
		<select class="form-control" id="class_num" name="class_num">
			<c:forEach var="num" items="${class_num_set }">
				<option value="${num }" <c:if test="${num==class_num }">selected</c:if>>${num }</option>
			</c:forEach>
		</select>
	</div>

	<input class="btn btn-secondary" type="submit" value="登録して終了">
</form>
<a href="StudentList.action">戻る</a>


</section>
</c:param>
</c:import>