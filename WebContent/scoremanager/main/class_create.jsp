<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス新規作成</c:param>
  <c:param name="content">
    <section class="container mt-4">
      <h2 class="h4 mb-4">クラス新規作成</h2>
      <form action="ClassNumCreateExecute.action" method="post">
        <div class="mb-3">
          <label for="classNum" class="form-label">クラス名</label>
          <input type="text" class="form-control" id="classNum" name="classNum" required>
        </div>
        <button type="submit" class="btn btn-primary">登録</button>
        <a href="ClassNumList.action" class="btn btn-secondary">戻る</a>
      </form>
    </section>
  </c:param>
</c:import>
