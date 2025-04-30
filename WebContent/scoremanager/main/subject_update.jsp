<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <section class="container mt-4">
      <h2 class="h4 mb-4">科目情報更新</h2>
<form action="SubjectUpdateExecute.action" method="post">

  <!-- 科目コード（表示用） -->
  <div class="d-flex flex-column mb-3">
    <label class="form-label">科目コード</label>
    <div class="form-control border-0 ps-1">${subject.cd}</div>
  </div>

  <!-- 科目コード（hiddenフィールド） -->
  <input type="hidden" name="cd" value="${subject.cd}">

  <div class="mb-3">
    <label for="name" class="form-label">科目名</label>
    <input type="text" class="form-control" id="name" name="name" value="${subject.name}" required>
  </div>
  <c:if test="${not empty errors['1']}">
    <div class="text-danger">${errors['1']}</div>
  </c:if>

  <div class="mt-3">
    <button type="submit" class="btn btn-primary">更新</button>
    <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
  </div>
</form>

    </section>
  </c:param>
</c:import>