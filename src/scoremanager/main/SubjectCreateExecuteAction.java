package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = request.getParameter("cd");
        String name = request.getParameter("name");

     // エラー表示
        java.util.Map<String, String> errors = new java.util.HashMap<>();

     // 登録処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        // 一覧画面へ
        request.getRequestDispatcher("SubjectList.action").forward(request, response);
    }
}