package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		ClassNumDao cNumDao = new ClassNumDao();

		//セッションのユーザーデータから所属している学校のクラス用データを取得する
		List<String> classList = cNumDao.filter(teacher.getSchool());

		//日時データを取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		List<Integer> entYearSet = new ArrayList<>();
		for (int i= year-10; i < year + 10; i++) {
			entYearSet.add(i);
		}


		SubjectDao subDao = new SubjectDao();
		List<Subject> subjects = subDao.filter(teacher.getSchool());

		System.out.println(subjects);

		session.setAttribute("class_num_set", classList);
		session.setAttribute("ent_year_set", entYearSet);
		session.setAttribute("subjects_set", subjects);



		request.getRequestDispatcher("test_list.jsp").forward(request, response);

	}
}
