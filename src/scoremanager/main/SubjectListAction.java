package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{

	public void execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//初期値を設定
		String cd = "";
		String name ="";

		//日時データを取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();


		//セッションのユーザーデータから所属している学校の生徒一覧用データを取得
		SubjectDao subject= new SubjectDao();

		session.setAttribute("sub", subject);

		//指定したフィルターの値を取得する
		cd = request.getParameter("f1");
		name = request.getParameter("f2");

		//指定したフィルターの値を保持する
		request.setAttribute("f1",cd);
		request.setAttribute("f2", name);


		//エラーメッセージを設定
		Map<String, String> errors = new HashMap<>();




		//取得した値をリクエストパラメータに保持
		request.setAttribute("sub", subject);


		request.getRequestDispatcher("subject_list.jsp").forward(request, response);
	}
}
