//package scoremanager.main;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import bean.Test;
//import dao.TestDao;
//import tool.Action;
//
//public class TestRegistExecuteAction extends Action {
//  @Override
//  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//    String studentNo = req.getParameter("student_no");
//    String subjectCd = req.getParameter("subject_cd");
//    String pointStr = req.getParameter("point");
//
//    if (studentNo == null || subjectCd == null || pointStr == null) {
//      req.setAttribute("error", "入力値に不備があります。");
//      req.getRequestDispatcher("test_regist.jsp").forward(req, res);
//      return;
//    }
//
//    int point = Integer.parseInt(pointStr);
//
//    Test test = new Test();
//    test.getStudent().setNo(studentNo);
//    test.getSubject().setCd(subjectCd);
//    test.setPoint(point);
//
//    TestDao testDao = new TestDao();
//    boolean success = testDao.save(test);
//
//    if (success) {
//      req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
//    } else {
//      req.setAttribute("error", "登録に失敗しました。");
//      req.getRequestDispatcher("test_regist.jsp").forward(req, res);
//    }
//  }
//}
