package scoremanager.main;

import javax.servlet.http.*;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("no");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, teacher.getSchool());

        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}