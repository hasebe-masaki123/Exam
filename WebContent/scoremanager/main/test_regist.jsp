<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">成績管理</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

      <!-- ✅ 検索フォーム -->
      <form action="TestRegist.action" method="post">
        <div class="row px-4 align-items-end">
          <div class="col-2">
            <label class="form-label">入学年度</label>
            <select class="form-select" name="f1">
              <option value="">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" ${year == param.f1 ? 'selected' : ''}>${year}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-2">
            <label class="form-label">クラス</label>
            <select class="form-select" name="f2">
              <option value="">--------</option>
              <c:forEach var="num" items="${class_num_set}">
                <option value="${num}" ${num == param.f2 ? 'selected' : ''}>${num}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-3">
            <label class="form-label">科目</label>
            <select class="form-select" name="f3">
              <option value="">--------</option>
              <option value="Python1" ${param.f3 == 'Python1' ? 'selected' : ''}>Python1</option>
              <option value="Java" ${param.f3 == 'Java' ? 'selected' : ''}>Java</option>
            </select>
          </div>
          <div class="col-2">
            <label class="form-label">回数</label>
            <select class="form-select" name="f4">
              <option value="">--------</option>
              <c:forEach begin="1" end="10" var="i">
                <option value="${i}" ${param.f4 == i ? 'selected' : ''}>${i}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-2 text-end">
            <button class="btn btn-secondary" type="submit">検索</button>
          </div>
        </div>
      </form>

      <!-- ✅ 科目名と回数表示 -->
      <div class="ps-4 my-3">
        科目：${param.f3}（${param.f4}回）
      </div>

      <!-- ✅ 成績登録フォーム -->
      <form action="TestRegistExecute.action" method="post">
        <c:forEach var="student" items="${students}">
          <input type="hidden" name="regist" value="${student.no}" />
        </c:forEach>
        <input type="hidden" name="count" value="${param.f4}" />
        <input type="hidden" name="subject" value="${param.f3}" />

        <table class="table mt-4">
          <thead>
            <tr>
              <th>入学年度</th>
              <th>クラス</th>
              <th>学生番号</th>
              <th>氏名</th>
              <th>点数</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="student" items="${students}">
              <tr>
                <td>${student.entYear}</td>
                <td>${student.classNum}</td>
                <td>${student.no}</td>
                <td>${student.name}</td>
                <td>
                  <input type="text"
                         class="form-control"
                         name="point_${student.no}"
                         value=""
                         maxlength="3"
                         pattern="^(100|[1-9]?[0-9])$"
                         title="0〜100の数値を入力してください" />
                  <div class="text-danger small">0〜100の範囲で入力してください</div>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <div class="text-center mt-4">
          <button class="btn btn-primary" type="submit">登録して終了</button>
        </div>
      </form>

    </section>

  </c:param>
</c:import>
