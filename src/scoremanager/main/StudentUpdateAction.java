package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//セッションのユーザーデータから所属している学校のクラス一覧用データを取得
		ClassNumDao cNumDao = new ClassNumDao();
		List<String> classList = cNumDao.filter(teacher.getSchool());

		//学生一覧から送付された学生番号から学生情報を取得し、セッションに保存する
		String student_no = request.getParameter("no");
		StudentDao stuDao = new StudentDao();
		Student student = stuDao.get(student_no);


		request.setAttribute("class_num_set", classList);
		request.setAttribute("student", student);

		request.getRequestDispatcher("student_update.jsp").forward(request, response);

	}


}