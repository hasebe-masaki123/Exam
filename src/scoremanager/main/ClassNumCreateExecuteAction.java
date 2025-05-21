package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassNumCreateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNumStr = request.getParameter("classNum");

        ClassNum classNum = new ClassNum();
        classNum.setClassNum(classNumStr);
        classNum.setSchool(teacher.getSchool());

        ClassNumDao dao = new ClassNumDao();
        dao.save(classNum, classNumStr);

        request.getRequestDispatcher("classnum_create_done.jsp").forward(request, response);
    }
}
