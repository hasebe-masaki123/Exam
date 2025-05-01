package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {

  @Override
  public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    HttpSession session = req.getSession();
    Teacher teacher = (Teacher) session.getAttribute("user");

    ClassNumDao cNDao = new ClassNumDao();
    SubjectDao subjectDao = new SubjectDao();

    List<String> classNumList = cNDao.filter(teacher.getSchool());
    List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

  //日時データを取得
  LocalDate todaysDate = LocalDate.now();
  int year = todaysDate.getYear();

  List<Integer> entYearSet = new ArrayList<>();
  for (int i= year-10; i < year + 10; i++) {
	  entYearSet.add(i);
  }

/**-------ダミーデータ--------*/
Test damyTestData = new Test();


/**--------ここまで-------*/
    req.setAttribute("class_num_set", classNumList);
    req.setAttribute("subject_set", subjectList);
    req.setAttribute("ent_year_set", entYearSet);
    req.getRequestDispatcher("test_regist.jsp").forward(req, res);
  }
}