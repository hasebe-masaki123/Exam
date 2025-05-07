package scoremanager.main;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        LocalDate today = LocalDate.now();
        int year = today.getYear();

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classList = classNumDao.filter(teacher.getSchool());
        List<Integer> entYearSet = java.util.stream.IntStream.rangeClosed(year - 10, year + 10)
            .boxed()
            .collect(Collectors.toList());

        String entYear = req.getParameter("f1");
        String classNum = req.getParameter("f2");

        List<Student> students;
        if (entYear != null && classNum != null) {
            StudentDao studentDao = new StudentDao();
            students = studentDao.filter(
                teacher.getSchool(),
                Integer.parseInt(entYear),
                classNum,
                false
            );
        } else {
            students = java.util.Collections.emptyList();
        }

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("students", students);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
