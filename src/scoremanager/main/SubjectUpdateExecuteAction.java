// 各メソッドをインポート
package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//セッションからユーザーデータを取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 入力値が空白または未入力の場合に true となるエラーフラグ
        boolean hasError = false;

        Map<String, String> errors = new HashMap<>();


        if (name == null || name.isEmpty()) {
            errors.put("1", "科目名を入力してください。");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);

        } else {
	        Subject subject = new Subject();
	        subject.setCd(cd);
	        subject.setName(name);
	        subject.setSchool(teacher.getSchool());

	        SubjectDao dao = new SubjectDao();
	        dao.save(subject);

			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        }
    }
}
