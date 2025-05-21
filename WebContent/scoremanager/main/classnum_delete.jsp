<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス情報削除確認</c:param>
  <c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4 text-danger">クラス情報削除確認</h2>
      <p>以下のクラス情報を削除しますか？</p>
      <form action="ClassNumDeleteExecute.action" method="post">
      <div class="d-flex flex-column mb-3">
		<label class="form-label" >クラス番号</label>
          <div class="form-control border-0 ps-1">${classNum.classNum}</div>
          <input type="hidden" name="classNum" value="${classNum.classNum}" />
        </div>

	<input class="btn btn-danger" type="submit" value="削除">
	</form>
	<form action="ClassNumList.action" method="get">
    <input class="btn btn-secondary" type="submit" value="キャンセル">
      </form>
    </section>
  </c:param>
</c:import>
