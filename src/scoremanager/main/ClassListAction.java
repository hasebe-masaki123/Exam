package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassListAction extends Action {

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		ClassNumDao cNDao = new ClassNumDao();
		List<String> list = cNDao.filter(teacher.getSchool());

		request.setAttribute("class_list_set", list);

		request.getRequestDispatcher("class_list.jsp").forward(request, response);
	}
}
