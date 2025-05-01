package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.TestDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {


		StudentDao stuDao = new StudentDao();
		TestDao tesDao = new TestDao();

		String student_no = request.getParameter("no");

		stuDao.delete(student_no);
		tesDao.delete(student_no);

		request.getRequestDispatcher("student_delete_done.jsp").forward(request, response);

	}

}