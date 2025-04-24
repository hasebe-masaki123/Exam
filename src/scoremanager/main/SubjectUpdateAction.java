package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectUpdateAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SubjectDAO dao = new SubjectDAO();
		List<Subject> list = dao.selectAllWithSubjectName();

		request.setAttribute("list", list);

		return "subject_update.jsp";
	}
}
