package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SchoolDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {


		SchoolDao stuDao = new SchoolDao();

		String student_no = request.getParameter("no");

		stuDao.get(student_no);

		request.getRequestDispatcher("student_delete_done.jsp").forward(request, response);

	}

}
