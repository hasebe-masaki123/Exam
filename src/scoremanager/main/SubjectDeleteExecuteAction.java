package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(teacher.getSchool());

        SubjectDao dao = new SubjectDao();
            dao.delete(subject);
            request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}
