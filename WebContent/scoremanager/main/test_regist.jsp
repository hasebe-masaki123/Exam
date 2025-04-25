<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
  <c:param name="title">成績登録</c:param>
  <c:param name="content">
    <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2">成績登録</h2>
    <form action="TestRegistExecute.action" method="post" class="px-4">
      <div class="mb-3">
        <label>学生：</label>
        <select name="student_no" class="form-select">
          <c:forEach var="student" items="${student_list}">
            <option value="${student.no}">${student.no}：${student.name}</option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label>科目：</label>
        <select name="subject_cd" class="form-select">
          <c:forEach var="subject" items="${subject_list}">
            <option value="${subject.cd}">${subject.name}</option>
          </c:forEach>
        </select>
      </div>
      <div class="mb-3">
        <label>得点：</label>
        <input type="number" name="score" class="form-control" min="0" max="100" required />
      </div>
      <c:if test="${not empty error}">
        <p class="text-danger">${error}</p>
      </c:if>
      <div class="text-center">
        <button type="submit" class="btn btn-primary">登録</button>
      </div>
    </form>
  </c:param>
</c:import>