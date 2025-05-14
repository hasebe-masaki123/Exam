package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	//セッションからユーザーデータを取得
    	HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

		//初期値を設定
		String entYearStr = "";
		String classNum ="";
		String subCd = "";
		int entYear = 0;
		String countStr = "";
		int count = 0;
		List<Test> testList = null;

      //日時データを取得
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classList = classNumDao.filter(teacher.getSchool());
        List<Integer> entYearSet = java.util.stream.IntStream.rangeClosed(year - 10, year + 10)
            .boxed()
            .collect(Collectors.toList());

       entYearStr = req.getParameter("f1");
       classNum = req.getParameter("f2");
       subCd = req.getParameter("f3");
       countStr = req.getParameter("f4");

       req.setAttribute("f1", entYearStr);
       req.setAttribute("f2", classNum);
       req.setAttribute("f3", subCd);
       req.setAttribute("f4", countStr);


        SubjectDao subDao = new SubjectDao();
        List<Subject> subject_list = subDao.filter(teacher.getSchool());

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		if (countStr != null) {
			count = Integer.parseInt(countStr);
		}


        if (entYear != 0 && !classNum.equals("0")  && !subCd.equals("0") && count != 0) {
        	Subject select_sub = subDao.get(subCd, teacher.getSchool());

        	TestDao tesDao = new TestDao();
        	testList = tesDao.filter(entYear, classNum, select_sub, count, teacher.getSchool());

        	req.setAttribute("select_sub", select_sub);


        } else if (entYear != 0 || "0".equals(classNum) || "0".equals(subCd) || count !=0) {
        	Map<String, String> errors = new HashMap<>();
        	errors.put("omission", "すべての項目を選択してください");

        	req.setAttribute("errors", errors);
        }


        session.setAttribute("ent_year_set", entYearSet);
        session.setAttribute("class_num_set", classList);
        session.setAttribute("subject_list_set", subject_list);
        session.setAttribute("test_list_set", testList);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}