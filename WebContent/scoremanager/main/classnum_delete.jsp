<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス情報削除確認</c:param>
  <c:param name="content">
    <section class="container mt-4">
      <h2 class="h4 mb-4">クラス情報削除確認</h2>
      <p>以下のクラス情報を削除しますか？</p>
      <form action="ClassNumDeleteExecute.action" method="post">
        <div class="mb-3">
          <label class="form-label">クラス番号</label>
          <div class="form-control">${classNum.classNum}</div>
          <input type="hidden" name="classNum" value="${classNum.classNum}" />
        </div>
        <button type="submit" class="btn btn-danger">削除</button>
        <a href="ClassNumList.action" class="btn btn-secondary">戻る</a>
      </form>
    </section>
  </c:param>
</c:import>
