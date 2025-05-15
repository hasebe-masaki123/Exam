package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//初期値を設定
		String entYearStr = "";
		String classNum ="";
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;

		//日時データを取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		//セッションのユーザーデータから所属している学校の生徒一覧用データを取得
		StudentDao stuDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		List<Student> students = stuDao.filter(teacher.getSchool(), isAttend);
		session.setAttribute("students", students);

		//指定したフィルターの値を取得する
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");

		//指定したフィルターの値を保持する
		request.setAttribute("f1",entYearStr);
		request.setAttribute("f2", classNum);



		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}

		//エラーメッセージを設定
		Map<String, String> errors = new HashMap<>();


		//フィルターの入力に合わせて学生情報を取得する
		if (entYear != 0 && !classNum.equals("0")) {
			students = stuDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			students = stuDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear ==0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			students = stuDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("omission", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);
			students = stuDao.filter(teacher.getSchool(), isAttend);

		}


		List<Integer> entYearSet = new ArrayList<>();
		for (int i= year-10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		//セッションのユーザーデータから所属している学校のクラス用データを取得する
		List<String> classList = cNumDao.filter(teacher.getSchool());


		//取得した値をリクエストパラメータに保持
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", classList);
		request.setAttribute("ent_year_set", entYearSet);


		request.getRequestDispatcher("student_list.jsp").forward(request, response);
	}
}