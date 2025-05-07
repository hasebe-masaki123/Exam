<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">成績管理</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

      <!-- ✅ 登録完了メッセージ -->
      <div class="alert alert-success mx-4" role="alert">
        登録が完了しました
      </div>

      <!-- ✅ 戻る・成績参照リンク -->
      <div class="px-4">
        <a href="TestRegist.action" class="btn btn-outline-secondary me-3" value="20">成績登録画面へ戻る</a>
        <a href="TestList.action" class="btn btn-outline-primary" value="30">成績参照画面へ進む</a>
      </div>

    </section>

  </c:param>
</c:import>
