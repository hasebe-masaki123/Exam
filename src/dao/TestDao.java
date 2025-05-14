package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    public Test gt(Student student, Subject subject, School school, int no, Connection connection) throws Exception {
        Test test = new Test();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select * from test where student_no = ? and school_cd = ? and subject_cd = ? and no = ?");
            statement.setString(1, student.getNo());
            statement.setString(2, school.getCd());
            statement.setString(3, subject.getCd());
            statement.setInt(4, no);

            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint((Integer) rSet.getObject("point"));
                test.setClassNum(rSet.getString("class_num"));
            } else {
                test = null;
            }
        } finally {
            if (statement != null) statement.close();
        }

        return test;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        SubjectDao subjectDao = new SubjectDao();

        while (rSet.next()) {
            Test test = new Test();

            Student student = new Student();
            student.setNo(rSet.getString("STUDENT.NO"));
            student.setName(rSet.getString("NAME"));
            student.setEntYear(rSet.getInt("ENT_YEAR"));
            student.setClassNum(rSet.getString("STUDENT.CLASS_NUM"));
            student.setSchool(school);
            test.setStudent(student);

            test.setClassNum(rSet.getString("STUDENT.CLASS_NUM"));

            String subjectCd = rSet.getString("TEST.SUBJECT_CD");
            if (subjectCd != null) {
                test.setSubject(subjectDao.get(subjectCd, school));
            }

            test.setSchool(school);
            test.setNo(rSet.getInt("TEST.NO"));
            test.setPoint((Integer) rSet.getObject("POINT"));
            System.out.println(rSet.getObject("TEST.POINT"));

            list.add(test);
        }

        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();

        String sql =
			"SELECT STUDENT.NO, STUDENT.NAME, STUDENT.CLASS_NUM, STUDENT.ENT_YEAR, TEST.SUBJECT_CD, TEST.NO, TEST.POINT FROM TEST "
			+ "LEFT JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO "
			+ "WHERE TEST.SUBJECT_CD = ? AND TEST.NO = ? AND STUDENT.ENT_YEAR = ? AND TEST.CLASS_NUM = ? "
			+ "ORDER BY STUDENT.NO ASC;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getCd());
            statement.setInt(2, num);
            statement.setInt(3, entYear);
            statement.setString(4, classNum);

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } finally {
            connection.close();
        }

        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        try (Connection connection = getConnection()) {
            for (Test test : list) {
                save(test, connection);
            }
        }
        return true;
    }

    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;

        try {
                statement = connection.prepareStatement("update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
                statement.setObject(1, test.getPoint(), Types.INTEGER);
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());

                count = statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }

        return count > 0;
    }



	/**
	 * 学生登録用
	 * @param studentNo
	 * @param subjectList
	 * @param school
	 * @param classNum
	 * @throws Exception
	 */
	public void stuInsert(String studentNo, List<Subject> subjectList, School school, String classNum) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			for (Subject subject : subjectList) {
				for (int no = 1; no <= 2; no++) {

					statement = connection.prepareStatement(
							"INSERT INTO TEST " +
							"VALUES(?,?,?,?,?,?)"
					);

					statement.setString(1, studentNo);
					statement.setString(2, subject.getCd());
					statement.setString(3, school.getCd());
					statement.setInt(4, no);
					statement.setNull(5, Types.INTEGER);
					statement.setString(6, classNum);

					statement.executeUpdate();

				}
			}
		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}


	}

	/**
	 * 科目登録用
	 * @param stuList
	 * @param sub
	 * @throws Exception
	 */
	public void subInsert(List<Student> stuList, Subject subject, School school) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			for (Student student : stuList) {
				for (int no = 1; no <= 2; no++) {

					statement = connection.prepareStatement(
							"INSERT INTO TEST " +
							"VALUES(?,?,?,?,?,?)"
					);

					statement.setString(1, student.getNo());
					statement.setString(2, subject.getCd());
					statement.setString(3, school.getCd());
					statement.setInt(4, no);
					statement.setNull(5, Types.INTEGER);
					statement.setString(6, student.getClassNum());


					statement.executeUpdate();

				}
			}
		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}


	}

	public void update(Student student) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"UPDATE TEST SET CLASS_NUM = ? WHERE STUDENT_NO = ?"
					);
					statement.setString(1, student.getClassNum());
					statement.setString(2, student.getNo());

					statement.executeUpdate();
		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

	}




	public void delete(String studentNo) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"DELETE FROM TEST "
					+
					"WHERE STUDENT_NO = ?"
			);

			statement.setString(1, studentNo);

			statement.executeUpdate();

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

	}
}