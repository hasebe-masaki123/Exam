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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

<div class="container border mx-3 mb-3 py-2 rounded" id="filter">

	<form method="get" class="mb-4 row">

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
				<c:forEach var="subject" items="${subject_set}">
					<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.getName()}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-md-2">
			<label class="form-label" for="test-f4-select">回数</label>
			<select class="form-select" id="test-f4-select" name="f4">
				<option value="0">--------</option>
				<option value="1">1</option>
				<option value="2">2</option>
			</select>
		</div>

		<div class="col-md-2 d-flex align-items-end">
			<button class="btn btn-secondary" id="filter-button">検索</button>
		</div>
	</form>

</div>

<c:choose>
	<c:when test="${list != null }">
		<div><p>科目：${f3 }(${f4 })</p></div>
		<form action="TestRegistExecute.action">
			<table class="table table-hover">
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>点数</th>
			</tr>
			<c:forEach var="item" items="${test}">
				<tr>
					<td>${f1}</td>

					<td>${f2}</td>

					<td>${item.student.no}</td>

					<td>${item.student.name}</td>

					<td>
						<input type="number" name="points"
							<c:if test="${item.point != null}">
								value="${item.point}"
							</c:if> />

							<%-- エラーメッセージ表示 --%>
							<div class="mt-2 text-warning">${errors.get("1") }</div>
					</td>
				</tr>
			</c:forEach>
			</table>
		</form>
	</c:when>
</c:choose>


</section>
</c:param>
</c:import>
