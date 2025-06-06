<%-- 成績検索JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
<c:param name="title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

<div class="container border mx-3 mb-3 py-2 rounded" id="filter">

	<!-- 科目情報フォーム -->
	<form action="TestListSubjectExcecute.action?f1=${f1}&f2=${f2}&f3=${f3}&f=${f}" method="get" class="mb-4 border-bottom">
		<input type="hidden" name="f" value="sj">
		<div class="row mb-2">
			<div class="col-2 text-center">
				<p>科目情報</p>
			</div>

			<div class="col-md-2">
				<label class="form-label" for="test-f1-select">入学年度</label>
				<select class="form-select" id="test-f1-select" name="f1">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-2">
				<label class="form-label" for="test-f2-select">クラス</label>
				<select class="form-select" id="test-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>

			</div>
			<div class="col-md-4">
				<label class="form-label" for="test-f3-select">科目</label>
				<select class="form-select" id="test-f3-select" name="f3">
					<option value="0">--------</option>
					<c:forEach var="subject" items="${subjects_set}">
						<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.getName()}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-2 text-center mt-3">
				<button class="btn btn-secondary" id="filter-button">検索</button>
			</div>

			<div class="col-12 mt-2 text-warning">${errors.get("sj")}</div>
		</div>
	</form>

	<!-- 学生情報フォーム -->
	<form action="TestListStudentExcecute.action?f4=${f4}&f=${f}" method="get">
		<input type="hidden" name="f" value="st">
		<div class="row mb-2">
			<div class="col-2 text-center">
				<p>学生情報</p>
			</div>

			<div class="col-md-4">
				<label class="form-label" for="test-f4-select">学生番号</label>
				<input type="text" class="form-control" id="test-f4-select" name="f4" value="${f4 }" required>
			</div>

			<div class="col-md-2 d-flex align-items-end">
				<button class="btn btn-secondary w-100" id="filter-button">検索</button>
			</div>

			<div class="col-12 mt-2 text-warning">${errors.get("f1")}</div>
		</div>
	</form>
</div>


</section>
</c:param>
</c:import>