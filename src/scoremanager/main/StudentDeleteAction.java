package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//学生一覧から取得した学生番号から学生情報を取得し、リクエストパラメータに保存する
		String student_no = request.getParameter("no");
		StudentDao stuDao = new StudentDao();
		Student student = stuDao.get(student_no);

		request.setAttribute("student", student);


		request.getRequestDispatcher("student_delete.jsp").forward(request, response);

	}
}