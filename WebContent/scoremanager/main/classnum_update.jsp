<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス情報編集</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

    <section class="me-4">
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報編集</h2>

      <form action="ClassNumUpdateExecute.action" method="post">
        <input type="hidden" name="oldClassNum" value="${classNum.classNum}" />

        <c:if test="${not empty errors}">
          <div class="alert alert-danger">
            <ul class="mb-0">
              <c:forEach var="err" items="${errors}">
                <li>${err.value}</li>
              </c:forEach>
            </ul>
          </div>
        </c:if>

        <div class="d-flex flex-column mb-3">
          <label for="classNum" class="form-label">クラス番号</label>
          <input type="text" class="form-control" id="classNum" name="classNum"
                 value="${classNum.classNum}" required placeholder="クラス番号を入力してください" maxlength="5" />
        </div>

        <div class="mb-3">
          <button type="submit" class="btn btn-primary">変更</button>
 			<a href="ClassNumList.action">戻る</a>
        </div>
      </form>
    </section>

  </c:param>
</c:import>