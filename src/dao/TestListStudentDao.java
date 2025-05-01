package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{

		List<TestListStudent> list = new ArrayList<>();

		while (rSet.next()) {
			TestListStudent testListStudent = new TestListStudent();
			testListStudent.setSubjectCd(rSet.getString("SUBJECT.CD"));
			testListStudent.setSubjectName(rSet.getString("SUBJECT.NAME"));
			testListStudent.setNum(rSet.getInt("TEST.NO"));
			testListStudent.setPoint((Integer) rSet.getObject("TEST.POINT"));

			list.add(testListStudent);
		}

		return list;

	}

	public  List<TestListStudent> filter(Student student) throws Exception{

		List<TestListStudent> list = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			if (student != null) {


				statement = connection.prepareStatement(
							"SELECT SUBJECT.CD, SUBJECT.NAME, TEST.NO, TEST.POINT FROM SUBJECT "
						+ "JOIN TEST ON SUBJECT.CD = TEST.SUBJECT_CD "
						+ "WHERE TEST.STUDENT_NO = ?"
				);
				statement.setString(1, student.getNo());

				ResultSet resultSet = statement.executeQuery();
				list = postFilter(resultSet);
			}

		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return list;

	}


}