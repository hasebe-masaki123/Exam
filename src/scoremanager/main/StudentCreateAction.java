package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//登録用の日時データを取得、設定
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		List<Integer> entYearSet = new ArrayList<>();

		for (int i= year-10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		//セッションのユーザーデータから所属している学校のクラス一覧用データを取得
		ClassNumDao cNumDao = new ClassNumDao();
		List<String> list = cNumDao.filter(teacher.getSchool());


		session.setAttribute("ent_year_set", entYearSet);
		session.setAttribute("class_num_set", list);


		request.getRequestDispatcher("student_create.jsp").forward(request, response);





	}
}
