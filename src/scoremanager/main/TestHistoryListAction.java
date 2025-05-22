package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestHistory;
import dao.SubjectDao;
import dao.TestHistoryDao;
import tool.Action;

public class TestHistoryListAction extends Action{

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 // セッションからログインユーザーを取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String studentNo = "";
        String subjectCd = "";

        studentNo = request.getParameter("student_no");
        subjectCd = request.getParameter("subject_cd");

        SubjectDao subDao = new SubjectDao();
        List<Subject> subList = subDao.filter(teacher.getSchool());

        request.setAttribute("subject_list_set", subList);

        TestHistoryDao tesHisDao = new TestHistoryDao();
        List<TestHistory> tesHisList = tesHisDao.filter(studentNo, subjectCd, teacher.getSchool().getCd());

        request.setAttribute("test_history_set_list", tesHisList);

        request.getRequestDispatcher("test_history_list.jsp").forward(request, response);






	}

}
