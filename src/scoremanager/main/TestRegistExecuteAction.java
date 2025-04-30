package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();

		List<Test> testList =(List<Test>) session.getAttribute("test");
		String[] points = req.getParameterValues("points");

		for  (int i = 0; i < testList.size(); i++) {
			testList.get(i).setPoint(Integer.parseInt(points[i]));
		}

		TestDao tesDao = new TestDao();

		boolean success = tesDao.save(testList);

		req.getRequestDispatcher("").forward(req, res);








		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
  }
}
