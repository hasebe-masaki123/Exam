package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
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
        SubjectDao subDao = new SubjectDao();
        /*
		 * 入力されを情報を基に登録
		 * 学生番号の重複、入力の不備がある場合エラーメッセージを設定し再入力を実行
		 */
		if (cd.length() != 3)  {
			errors.put("1", "科目コードは3文字で入力してください");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);

		} else {
			if (subDao.get(cd, teacher.getSchool()) != null) {
				errors.put("2", "科目コードが重複しています”");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("subject_create.jsp").forward(request, response);

		} else {
     // 登録処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        subDao.save(subject);

      //TESTテーブルを作成
        StudentDao stuDao = new StudentDao();
        List<Student> stuList = stuDao.filter(teacher.getSchool(), true);


        TestDao testDao =new TestDao();
        testDao.subInsert(stuList, subject, teacher.getSchool());


        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
		}
		}
	}
}