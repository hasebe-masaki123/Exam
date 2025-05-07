package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String[] studentNos = req.getParameterValues("regist");
        String subjectCd = req.getParameter("subject");
        int count = Integer.parseInt(req.getParameter("count"));

        // DAO生成
        TestDao dao = new TestDao();

        // ループして登録
        for (String studentNo : studentNos) {
            String pointStr = req.getParameter("point_" + studentNo);

            if (pointStr == null || pointStr.isEmpty()) {
                continue; // 空欄なら登録スキップ
            }

            int point = Integer.parseInt(pointStr);

            Test test = new Test();
            test.setSchoolCd(teacher.getSchool().getCd());
            test.setStudentNo(studentNo);
            test.setSubjectCd(subjectCd);
            test.setNo(count);
            test.setPoint(point);

            dao.insert(test); // 登録
        }

        // 登録完了画面へ
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
