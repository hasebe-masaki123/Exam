

// 各メソッドをインポート
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // バリデーション
        boolean hasError = false;
        java.util.Map<String, String> errors = new java.util.HashMap<>();

        if (name == null || name.isEmpty()) {
            errors.put("1", "科目名を入力してください。");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao dao = new SubjectDao();
        dao.save(subject);

		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);

    }
}
