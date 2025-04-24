package scoremanager.main;

import java.util.List;
import javax.servlet.http.*;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
  @Override
  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    HttpSession session = req.getSession();
    Teacher teacher = (Teacher) session.getAttribute("user");

    StudentDao studentDao = new StudentDao();
    SubjectDao subjectDao = new SubjectDao();

    List<Student> studentList = studentDao.filter(teacher.getSchool(), true);
    List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

    req.setAttribute("student_list", studentList);
    req.setAttribute("subject_list", subjectList);
    req.getRequestDispatcher("test_regist.jsp").forward(req, res);
  }
}
