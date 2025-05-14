package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		List<TestListSubject> list = new ArrayList<>();

		while(rSet.next()) {
			TestListSubject testListSubject = new TestListSubject();
			 testListSubject.setEntYear(rSet.getInt("STUDENT.ENT_YEAR"));
			 testListSubject.setStudentNo(rSet.getString("STUDENT.NO"));
			 testListSubject.setStudentName(rSet.getString("STUDENT.NAME"));
			 testListSubject.setClassNum(rSet.getString("TEST.CLASS_NUM"));

			 testListSubject.putPoint(1,(Integer) rSet.getObject("POINT1"));
			 testListSubject.putPoint(2,(Integer) rSet.getObject("POINT2"));


			 list.add(testListSubject);

		}

		return list;
	}

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		List<TestListSubject> list = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"SELECT STUDENT.ENT_YEAR, STUDENT.NO, STUDENT.NAME, TEST.CLASS_NUM,"
					+ "MAX(CASE WHEN TEST.NO = 1 THEN TEST.POINT END) AS POINT1, "
					+ "MAX(CASE WHEN TEST.NO = 2 THEN TEST.POINT END) AS POINT2 "
					+ "FROM STUDENT JOIN TEST ON STUDENT.NO = TEST.STUDENT_NO "
					+ "WHERE STUDENT.ENT_YEAR = ? AND TEST.CLASS_NUM = ? AND TEST.SUBJECT_CD = ? AND TEST.SCHOOL_CD = ? "
					+ "GROUP BY STUDENT.ENT_YEAR, STUDENT.NO, STUDENT.NAME, TEST.CLASS_NUM "
					+ "ORDER BY STUDENT.NO ASC;"
			);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());

			ResultSet resultSet = statement.executeQuery();
			list = postFilter(resultSet);


		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}



		return list;
	}

}
