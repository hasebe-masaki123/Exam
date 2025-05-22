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

	<form action="TestRegist.action" class="mb-4 row">

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
				<c:forEach var="subject" items="${subject_list_set}">
					<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.name}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-md-2">
			<label class="form-label" for="test-f4-select">回数</label>
			<select class="form-select" id="test-f4-select" name="f4">
				<option value="0">--------</option>
				<c:forEach var="i" begin="1" end="2">
					<option value="${i}" <c:if test="${i==f4}">selected</c:if>>${i}</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-md-2 d-flex align-items-end">
			<button class="btn btn-secondary" id="filter-button">検索</button>
		</div>

		<div class="col-12 mt-2 text-warning">${errors.get("omission")}</div>
	</form>

</div>

<c:choose>
	<c:when test="${test_list_set.size() > 0 }">
		<div><p>科目：${select_sub.name}(${f4 }回)</p></div>
		<form action="TestRegistExecute.action" method="post">

			<input type="hidden" name="f1" value="${f1}">
			<input type="hidden" name="f2" value="${f2}">
			<input type="hidden" name="f3" value="${f3}">
			<input type="hidden" name="f4" value="${f4}">

			<table class="table table-hover">
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>点数</th>
			</tr>
			<c:forEach var="test" items="${test_list_set}">
				<tr>
					<td>${test.student.entYear}</td>

					<td>${test.classNum}</td>

					<td>${test.student.no}</td>

					<td>${test.student.name}</td>

					<td>
						<input type="hidden" name="studentNos" value="${test.student.no }">
						<input type="number" name="points"
							<c:if test="${test.point != null }">value="${test.point}"</c:if>/>

							<%-- 学生番号をキーにしてエラーがあるか確認 --%>
							<c:if test="${errors.get(test.student.no) != null}">
								<div class="mt-2 text-warning">${errors.get(test.student.no)}</div>
							</c:if>
					</td>
				</tr>
			</c:forEach>
			</table>

			<input type="hidden" name="subjectCd" value="${select_sub.cd}">
			<input type="hidden" name="count" value="${param.f4 }">

			<div class="col-md-2 d-flex align-items-end">
				<button class="btn btn-secondary">登録して終了</button>
			</div>
		</form>
	</c:when>
	<c:when test="${test_list_set.size() == 0 }">
		<div>学生情報が存在しませんでした</div>
	</c:when>
</c:choose>


</section>
</c:param>
</c:import>