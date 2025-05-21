package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassNumDeleteExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNumValue = request.getParameter("classNum");

        ClassNum classNum = new ClassNum();
        classNum.setSchool(teacher.getSchool());
        classNum.setClassNum(classNumValue);

        ClassNumDao dao = new ClassNumDao();
        dao.delete(classNum);

        request.getRequestDispatcher("classnum_delete_done.jsp").forward(request, response);
    }
}
