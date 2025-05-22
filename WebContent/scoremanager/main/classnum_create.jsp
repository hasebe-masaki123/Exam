<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス新規作成</c:param>
  <c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス新規作成</h2>
      <form action="ClassNumCreateExecute.action" method="post">

  <c:if test="${not empty errors}">
    <div class="d-flex flex-column mb-3">
      <ul class="mb-0">
        <c:forEach var="err" items="${errors}">
          <li>${err.value}</li>
        </c:forEach>
      </ul>
    </div>
  </c:if>
	<div class="d-flex flex-column mb-3">
		<label class="form-label" for="name" >クラス名</label>
    		<input class="form-control" type="text" value="${classNum.classNum}" id="classNum" name="classNum" maxlength=5 placeholder="クラス名を入力してください" required>
	</div>

	<input class="btn btn-primary" type="submit" value="登録">
</form>
 <a href="ClassNumList.action">戻る</a>

    </section>
  </c:param>
</c:import>