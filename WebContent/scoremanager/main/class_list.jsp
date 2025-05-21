<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
<div class="my-2 text-end px-4">
	<a href="ClassNumCreate.action">新規登録</a>
</div>
		<table class="table table-hover">
		<tr>
			<th>クラス名</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="classCd" items="${class_list_set}">
			<tr>
				<td>${classCd}</td>
				<td><a href="ClassNumUpdate.action?no=${classCd }">変更</a></td>
				<td><a href="ClassNumDelete.action?no=${classCd }">削除</a></td>
			</tr>
		</c:forEach>
	</table>
</section>
</c:param>
</c:import>