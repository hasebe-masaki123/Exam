package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//初期値を設定
		String entYearStr = "";
		String classNum ="";
		String subjectCd = "";
		String studentNo = "";

		//指定したフィルターの値を取得する
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectCd = request.getParameter("f3");
		studentNo = request.getParameter("f4");

		request.setAttribute("f1", entYearStr);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectCd);
		request.setAttribute("f4", studentNo);

		StudentDao stuDao = new StudentDao();
		Student student = stuDao.get(studentNo);


		TestListStudentDao tLstuDao = new TestListStudentDao();
		List<TestListStudent> studentList = tLstuDao.filter(student);

		request.setAttribute("student", student);
		request.setAttribute("list", studentList);
		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);

	}
}