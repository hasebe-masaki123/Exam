<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス情報編集</c:param>
  <c:param name="content">
    <section class="container mt-4">
      <h2 class="h4 mb-4">クラス情報編集</h2>
      <form action="ClassNumUpdateExecute.action" method="post">
        <input type="hidden" name="oldClassNum" value="${classNum.classNum}" />
        <div class="mb-3">
          <label for="classNum" class="form-label">クラス番号</label>
          <input type="text" class="form-control" id="classNum" name="classNum" value="${classNum.classNum}" required />
        </div>
        <button type="submit" class="btn btn-primary">更新</button>
        <a href="ClassNumList.action" class="btn btn-secondary">戻る</a>
      </form>
    </section>
  </c:param>
</c:import>
