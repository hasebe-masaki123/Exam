package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("no");


        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 対象の科目を取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, teacher.getSchool());

        // フォワード
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }
}
