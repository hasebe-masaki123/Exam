package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassNumUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String oldClassNum = request.getParameter("oldClassNum");
        String newClassNum = request.getParameter("classNum");

        ClassNum classNum = new ClassNum();
        classNum.setSchool(teacher.getSchool());
        classNum.setClassNum(newClassNum);

        ClassNumDao dao = new ClassNumDao();
        dao.save(classNum, oldClassNum);
        request.getRequestDispatcher("classnum_update_done.jsp").forward(request, response);
    }
}
