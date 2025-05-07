<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">成績参照</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

    <section class="me-4">
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

      <!-- ✅ 科目情報検索フォーム -->
      <form action="TestList.action" method="post" class="mb-4">
        <p class="fw-bold">科目情報</p>
        <div class="row px-4 align-items-end">
          <div class="col-2">
            <label class="form-label">入学年度</label>
            <select class="form-select" name="f1">
              <option value="">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" ${year == param.f1 ? "selected" : ""}>${year}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-2">
            <label class="form-label">クラス</label>
            <select class="form-select" name="f2">
              <option value="">--------</option>
              <c:forEach var="num" items="${class_num_set}">
                <option value="${num}" ${num == param.f2 ? "selected" : ""}>${num}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-3">
            <label class="form-label">科目</label>
            <select class="form-select" name="f3">
              <option value="">--------</option>
              <!-- 仮置き -->
              <option value="Python1" ${param.f3 == 'Python1' ? 'selected' : ''}>Python1</option>
              <option value="Java" ${param.f3 == 'Java' ? 'selected' : ''}>Java</option>
            </select>
          </div>
          <div class="col-2 text-end">
            <input type="hidden" name="f" value="sj" />
            <button class="btn btn-secondary" type="submit" value="31">検索</button>
          </div>
        </div>
      </form>

      <!-- ✅ 学生情報検索フォーム -->
      <form action="TestList.action" method="post" class="mb-4">
        <p class="fw-bold">学生情報</p>
        <div class="row px-4 align-items-end">
          <div class="col-3">
            <label class="form-label">学生番号</label>
            <input type="text"
                   class="form-control"
                   name="f4"
                   maxlength="10"
                   placeholder="学生番号を入力してください"
                   value="${param.f4}"
                   required />
          </div>
          <div class="col-2 text-end">
            <input type="hidden" name="f" value="st" />
            <button class="btn btn-secondary" type="submit" value="32">検索</button>
          </div>
        </div>
      </form>

      <!-- ✅ 案内メッセージ -->
      <div class="px-4 text-primary small">
        科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
      </div>

<!-- ✅ 検索結果テーブル -->
<c:if test="${not empty testResults}">
  <table class="table mt-4">
    <thead>
      <tr>
        <th>入学年度</th>
        <th>クラス</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>科目</th>
        <th>回数</th>
        <th>点数</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="result" items="${testResults}">
        <tr>
          <td>${result.entYear}</td>
          <td>${result.classNum}</td>
          <td>${result.studentNo}</td>
          <td>${result.studentName}</td>
          <td>${result.subjectName}</td>
          <td>${result.testNo}</td>
          <td>
            <c:choose>
              <c:when test="${empty result.point}">
                -
              </c:when>
              <c:otherwise>
                ${result.point}
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>


    </section>

  </c:param>
</c:import>
