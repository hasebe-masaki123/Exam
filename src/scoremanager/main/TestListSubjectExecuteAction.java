package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{

	@Override
	public void  execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//初期値を設定
		String entYearStr = "";
		String classNum ="";
		String subjectCd = "";
		int entYear = 0;

		//指定したフィルターの値を取得する
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectCd = request.getParameter("f3");

		request.setAttribute("f1", entYearStr);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectCd);

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		School school = teacher.getSchool();

		SubjectDao subDao = new SubjectDao();
		Subject subject = subDao.get(subjectCd, school);



		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		if (subject != null) {
			request.setAttribute("subject", subject);
		}

		if (entYear != 0 && !classNum.equals("0") && subject != null && !subject.getCd().equals("0") ) {

			TestListSubjectDao tlsDao = new TestListSubjectDao();
			List<TestListSubject> subjectList = tlsDao.filter(entYear, classNum, subject, school);
			System.out.println(1);

			request.setAttribute("list", subjectList);

			request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
		} else {
			//エラーメッセージを設定
			System.out.println(2);
			Map<String, String> errors = new HashMap<>();
			errors.put("sj", "入学年度とクラスと科目を指定してください");
			request.setAttribute("errors", errors);

			request.getRequestDispatcher("test_list.jsp").forward(request, response);

		}


	}
}