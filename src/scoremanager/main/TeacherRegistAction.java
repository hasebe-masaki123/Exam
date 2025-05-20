package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TeacherRegistAction extends Action{

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//セッションからユーザーデータを取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
	}

}
