package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	//セッションからユーザーデータを取得
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("user");

    	//削除する科目の科目コードを取得
        String cd = request.getParameter("cd");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(teacher.getSchool());

        //科目コードに該当する科目情報と紐づけされた成績情報を削除
        SubjectDao subDao = new SubjectDao();
        TestDao       tesDao = new TestDao();

        subDao.delete(subject);
        tesDao.subDelete(cd);


        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}
