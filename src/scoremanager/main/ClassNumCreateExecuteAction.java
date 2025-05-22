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

public class ClassNumCreateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNumStr = request.getParameter("classNum");

        ClassNum classNum = new ClassNum();
        classNum.setClassNum(classNumStr);
        classNum.setSchool(teacher.getSchool());

        // 文字数チェック（5文字以内）
        Map<String, String> errors = new HashMap<>();
        if (classNumStr == null || classNumStr.length() > 5) {
            errors.put("char", "クラス番号は5文字以内で入力してください。");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("classNum", classNum);
            request.getRequestDispatcher("classnum_create.jsp").forward(request, response);
            return;
        }

        ClassNumDao dao = new ClassNumDao();
        dao.save(classNum, classNumStr);

        request.getRequestDispatcher("classnum_create_done.jsp").forward(request, response);
    }
}
