<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">クラス情報削除完了</c:param>
  <c:param name="content">
    <section class="container mt-4">
      <div class="alert alert-success">クラス情報を削除しました。</div>
      <a href="ClassNumList.action" class="btn btn-primary">クラス一覧へ戻る</a>
    </section>
  </c:param>
</c:import>
