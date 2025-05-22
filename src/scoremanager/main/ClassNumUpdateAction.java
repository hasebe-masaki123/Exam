package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import tool.Action;

public class ClassNumUpdateAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String classNumValue = request.getParameter("no");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNum classNum = new ClassNum();
        classNum.setSchool(teacher.getSchool());
        classNum.setClassNum(classNumValue);

        request.setAttribute("classNum", classNum);
        request.getRequestDispatcher("classnum_update.jsp").forward(request, response);
    }
}
