package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class StudentCreateExciteAction extends Action{

	@Override
	public void execute(
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {

		//セッションからユーザーデータを取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//入力された登録用の学生情報を取得
		String ent_year_str = request.getParameter("ent_year");
		int ent_year = Integer.parseInt(ent_year_str);
		String student_no = request.getParameter("no");
		String student_name = request.getParameter("name");
		String class_num = request.getParameter("class_num");

		School school = teacher.getSchool();

		/*
		 *	学生ごとのTESTテーブルを作成
		  */
		SubjectDao subDao = new SubjectDao();
		List<Subject> subjectList = subDao.filter(teacher.getSchool());

		TestDao tesDao = new TestDao();
		tesDao.insert(student_no, subjectList, school, class_num);



		//再入力用に学生情報をリクエストパラメータに保持
		request.setAttribute("ent_year", ent_year);
		request.setAttribute("no", student_no);
		request.setAttribute("name", student_name);
		request.setAttribute("class_num", class_num);

		Map<String, String> errors = new HashMap<>();
		StudentDao stuDao = new StudentDao();

		/*
		 * 入力されを情報を基に登録
		 * 学生番号の重複、入力の不備がある場合エラーメッセージを設定し再入力を実行
		 */
		if (ent_year  == 0) {
			errors.put("1", "入学年度を入力してください");
			request.setAttribute("errors", errors);

			request.getRequestDispatcher("student_create.jsp").forward(request, response);

		} else {
			if (stuDao.get(student_no) != null) {
				errors.put("2", "学生番号が重複しています”");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("student_create.jsp").forward(request, response);


			} else {
				//登録するデータをStudentクラスに保存
				Student stu = new Student();
				stu.setNo(student_no);
				stu.setName(student_name);
				stu.setEntYear(ent_year);
				stu.setClassNum(class_num);
				stu.setAttend(true);
				stu.setSchool(school);

				//DBに登録する
				stuDao.save(stu);

				request.getRequestDispatcher("student_create_done.jsp").forward(request, response);

			}


		}


	}

}
