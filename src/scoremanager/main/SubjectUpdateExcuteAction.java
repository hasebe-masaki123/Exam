package scoremanager.main;

import javax.servlet.http.HttpServletRequest;

public class SubjectUpdateExcuteAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String subject=request.gerParameter("subject");
		Subject s = new Subject();
		s.setSubject(subject);
		SubjectDAO dao = new SubjectDAO();
		List<Subject> subject_list dao.selectSubjectName(s);
		String subject_name="";
		for (Subject st : subject_list) {
			subject_name = st.getSubject_name();
		}
		List<Subject> subject_name_list = dao.selectOtherSubjectName(s);

		request.setAttribute("subject_name", subject_name);
		request.setAttribute("subject_name_list", subject_name_list);

		return "subject_update_done.jsp";
	}
}
