package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestResult;
import dao.TestDao;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 検索区分取得（"sj"：科目検索 / "st"：学生検索）
        String searchMode = req.getParameter("f");

        // 入学年度・クラス番号セット（セレクトボックス用）
        List<String> entYearSet = new ArrayList<>();
        entYearSet.add("2023");
        entYearSet.add("2022");
        entYearSet.add("2021");

        List<String> classNumSet = new ArrayList<>();
        classNumSet.add("101");
        classNumSet.add("102");
        classNumSet.add("131");

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);

        // 成績リスト（検索結果）
        List<TestResult> testResults = new ArrayList<>();

        TestDao dao = new TestDao();

        // 検索処理
        if ("sj".equals(searchMode)) {
            // 科目情報検索
            String entYear = req.getParameter("f1");
            String classNum = req.getParameter("f2");
            String subject = req.getParameter("f3");

            if (entYear != null && classNum != null && subject != null) {
                testResults = dao.filterBySubject(teacher.getSchool().getCd(), entYear, classNum, subject);
            }

        } else if ("st".equals(searchMode)) {
            // 学生情報検索
            String entYear = req.getParameter("f1");
            String classNum = req.getParameter("f2");
            String studentNo = req.getParameter("f4");

            if (entYear != null && classNum != null && studentNo != null) {
                testResults = dao.filterByStudent(entYear, classNum, studentNo);
            }
        }

        // 検索結果をセット
        req.setAttribute("testResults", testResults);

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
