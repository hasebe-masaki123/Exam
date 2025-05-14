package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import dao.TestDao;
import tool.Action;

public class StudentUpdateExecuteAction  extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");


		String isAttendStr = "";
		boolean isAttend = false;

		//送信された変更する学生情報を取得
		String student_no = request.getParameter("no");
		String update_name = request.getParameter("name");
		int ent_year = Integer.parseInt(request.getParameter("ent_year"));
		String update_class_num = request.getParameter("class_num");
		isAttendStr = request.getParameter("is_attend");
		School school = teacher.getSchool();


		if (isAttendStr != null) {
			isAttend = true;
		}

		Student student = new Student();
		student.setNo(student_no);
		student.setName(update_name);
		student.setEntYear(ent_year);
		student.setClassNum(update_class_num);
		student.setAttend(isAttend);
		student.setSchool(school);


		//DBに更新する
		StudentDao stuDao = new StudentDao();
		stuDao.save(student);

		TestDao tesDao = new TestDao();
		tesDao.update(student);



		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);





	}
}