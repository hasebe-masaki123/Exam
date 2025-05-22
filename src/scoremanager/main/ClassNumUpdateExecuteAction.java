package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassNumUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String oldClassNum = request.getParameter("oldClassNum");
        String newClassNum = request.getParameter("classNum");

        ClassNum classNum = new ClassNum();
        classNum.setSchool(teacher.getSchool());
        classNum.setClassNum(newClassNum);

        Map<String, String> errors = new HashMap<>();

        // 文字数チェック（5文字以内）
        if (newClassNum == null || newClassNum.length() > 5) {
            errors.put("char", "クラス番号は5文字以内で入力してください。");
        }

        ClassNumDao dao = new ClassNumDao();

        // 重複チェック（別のクラス番号として存在していたらNG）
        if (!newClassNum.equals(oldClassNum) && dao.exists(newClassNum, teacher.getSchool().getCd())) {
            errors.put("duplicate", "クラス名が重複しています。");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("classNum", classNum);
            request.getRequestDispatcher("classnum_update.jsp").forward(request, response);
            return;
        }

        dao.save(classNum, oldClassNum);
        request.getRequestDispatcher("classnum_update_done.jsp").forward(request, response);
    }
}
