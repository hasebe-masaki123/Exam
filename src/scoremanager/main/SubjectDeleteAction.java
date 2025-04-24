package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String subject = request.getParameter("subject");
		Subject s = new Subject();
		s.setSubject(subject);
		SubjectDao dao = new SubjectDao();
		int line = dao.deleteSubject(s);

		request.setAttribute("line". line);

		return "subject_delete_done.jsp";
	}
}