package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestHistory;

public class TestHistoryDao extends Dao {

	public void insert(TestHistory history) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
			statement = connection.prepareStatement(
				"INSERT INTO SCORE_HISTORY (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, TEST_NO, OLD_POINT, NEW_POINT, UPDATED_BY_TEACHER) "
				+
				"VALUES (?, ?, ?, ?, ?, ?, ?)"
			);
			statement.setString(1, history.getStudentNo());
			statement.setString(2, history.getSubjectCd());
			statement.setString(3, history.getSchoolCd());
			statement.setInt(4, history.getTestNo());
			statement.setObject(5, history.getOldPoint());
			statement.setObject(6, history.getNewPoint());
			statement.setObject(7, history.getTeacherId());

			statement.executeUpdate();


		} finally {
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		}
    }

	public List<TestHistory> filter(String studentNo, String subjectCd, String schoolCd) throws Exception {
		List<TestHistory> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			statement = connection.prepareStatement(
				"SELECT * FROM TEST_HISTORY WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ?"
			);
			statement.setString(1, studentNo);
			statement.setString(2, subjectCd);
			statement.setString(3, schoolCd);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				TestHistory testHistory = new TestHistory();
				testHistory.setId(rs.getInt("ID"));
				testHistory.setStudentNo(studentNo);
				testHistory.setSubjectCd(subjectCd);
				testHistory.setSchoolCd(schoolCd);
				testHistory.setTestNo(rs.getInt("TEST_NO"));
				testHistory.setOldPoint((Integer) rs.getObject("OLD_POINT"));
				testHistory.setNewPoint((Integer) rs.getObject("NEW_POINT"));
				testHistory.setTeacherId(rs.getString("TEACHER_ID"));
				testHistory.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

				list.add(testHistory);
			}


		} finally {
			if(statement != null) statement.close();
			if(connection != null) connection.close();
		}

		return list;
	}
}
