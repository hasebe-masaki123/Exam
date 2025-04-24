package scoremanager.main;

import tool.Action;

public class SubjectDeleteSelectAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SubjectDao dao = new SubjectDao();
		List<Subject> list = dao.selectAllWithSubjectName();

		request.setAttribute("list, list");

		return "subject_delete.jsp;
	}
}