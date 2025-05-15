package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザーを取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータ取得
        String[] studentNos = req.getParameterValues("studentNos");
        String[] pointStrings = req.getParameterValues("points");
        String subjectCd = req.getParameter("subjectCd");
        int count = Integer.parseInt(req.getParameter("count"));

        // 科目情報取得
        SubjectDao subDao = new SubjectDao();
        Subject subject = subDao.get(subjectCd, teacher.getSchool());

        // 初期化
        Integer[] points = new Integer[pointStrings.length];
        List<Test> tests = new ArrayList<>();
        Map<String, String> errors = new HashMap<>();

        // 得点バリデーション
        boolean isValidPoint = true;

        for (int i = 0; i < pointStrings.length; i++) {
            String pointStr = pointStrings[i];

            if (pointStr == null || pointStr.trim().isEmpty()) {
                points[i] = null;
            } else {
                int point = Integer.parseInt(pointStr.trim());
                if (point < 0 || point > 100) {
                    errors.put(studentNos[i], "0~100の範囲で入力してください");
                    isValidPoint = false;
                }
                points[i] = point;
            }
        }

        // 得点が全て有効な場合、データ登録処理
        if (isValidPoint) {
            for (int i = 0; i < studentNos.length; i++) {
                Student student = new Student();
                student.setNo(studentNos[i]);

                Test test = new Test();
                test.setSchool(teacher.getSchool());
                test.setStudent(student);
                test.setSubject(subject);
                test.setNo(count);
                test.setPoint(points[i]);

                tests.add(test);
            }

            // 登録実行
            TestDao testDao = new TestDao();
            testDao.save(tests);

            // 登録完了画面へ遷移
            req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
        } else {
            // 入力エラーがある場合、再入力画面へ戻す
            req.setAttribute("f1", req.getParameter("f1"));
            req.setAttribute("f2", req.getParameter("f2"));
            req.setAttribute("f3", req.getParameter("f3"));
            req.setAttribute("f4", req.getParameter("f4"));
            req.setAttribute("select_sub", subject);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
        }
    }
}
