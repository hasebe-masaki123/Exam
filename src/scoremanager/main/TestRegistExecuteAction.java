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

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String[] studentNos = req.getParameterValues("studentNos");
        String[] pointStrings = req.getParameterValues("points");
        String subjectCd = req.getParameter("subjectCd");
        int count = Integer.parseInt(req.getParameter("count"));

        // DAO生成
        TestDao tesDao = new TestDao();
        SubjectDao subDao = new SubjectDao();

        Subject subject = subDao.get(subjectCd, teacher.getSchool());

        // ループして登録
        List<Test> tests = new ArrayList<>();


        Integer[] points = new Integer[pointStrings.length];
        Map<String, String> errors = new HashMap<>();
        boolean isValidPoint = true;

        for (int i = 0; i < pointStrings.length; i++) {
            String pointStr = pointStrings[i];

            if (pointStr == null || pointStr.trim().isEmpty()) {
                points[i] = null;
            } else {
            	int point = Integer.parseInt(pointStr.trim());
                if (point < 0 || point > 100) {
                		errors.put(studentNos[i], "0~100で入力してください");
                		isValidPoint = false;
                    }
                    points[i] = point;
            }
        }

        if (isValidPoint) {
        	for (int i = 0; i < studentNos.length; i++) {


                Test test = new Test();
                Student student = new Student();
                student.setNo(studentNos[i]);

                test.setSchool(teacher.getSchool());
                test.setStudent(student);
                test.setSubject(subject);
                test.setNo(count);
                test.setPoint(points[i]);

                tests.add(test);

            }

            	tesDao.save(tests);

            // 登録完了画面へ
            req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
        } else {

        	// バリデーション失敗などで再表示する場合
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