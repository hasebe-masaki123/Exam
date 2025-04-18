package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao{


	public Student get(String no) throws Exception {

		Student stu = new Student();

		Connection con = getConnection();

		PreparedStatement statement = null;

		try {

			statement = con.prepareStatement(
					"SELECT * FROM STUDENT WHERE NO = ?"
					);

			statement.setString(1, no);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {


			stu.setNo(resultSet.getString("no"));
			stu.setName(resultSet.getString("name"));
			stu.setEntYear(resultSet.getInt("ent_year"));
			stu.setClassNum(resultSet.getString("class_num"));
			stu.setAttend(resultSet.getBoolean("is_attend"));

			School school = new School();
			school.setCd(resultSet.getString("school_cd"));
			stu.setSchool(school);

			} else {
				stu = null;
			}


		} catch (Exception e){
			throw e;
		} finally {
			if (statement != null) statement.close();
			if (con != null) con.close();

		}

		return stu;
	}

	private List<Student> postFilter(ResultSet resultSet, School school) throws SQLException {
		List<Student> list = new ArrayList<>();

		while (resultSet.next()) {
			Student student = new Student();
			student.setNo(resultSet.getString("no"));
			student.setName(resultSet.getString("name"));
			student.setEntYear(resultSet.getInt("ent_year"));
			student.setClassNum(resultSet.getString("class_num"));
			student.setAttend(resultSet.getBoolean("is_attend"));
			student.setSchool(school);

			list.add(student);
		}

		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception{
		List<Student> list = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"SELECT * FROM  STUDENT WHERE ENT_YEAR = ? AND CLASS_NUM = ?  AND (IS_ATTEND IS TRUE OR IS_ATTEND IS ?)"
			);
			statement.setInt(1,entYear);
			statement.setString(2, classNum);
			statement.setBoolean(3, isAttend);

			ResultSet resultSet = statement.executeQuery();

			list = postFilter(resultSet, school);


		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return list;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception{
		List<Student> list = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
				"SELECT * FROM  STUDENT WHERE ENT_YEAR = ? AND  (IS_ATTEND IS TRUE OR IS_ATTEND IS ?)"
			);
			statement.setInt(1,entYear);
			statement.setBoolean(2, isAttend);
			ResultSet resultSet = statement.executeQuery();

			list = postFilter(resultSet, school);


		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return list;
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception {

		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"SELECT * FROM  STUDENT "
					+
					"WHERE IS_ATTEND IS TRUE OR IS_ATTEND IS ?"
			);
			statement.setBoolean(1, isAttend);

			ResultSet resultSet = statement.executeQuery();
			list = postFilter(resultSet, school);

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		return list;
	}


	public boolean save(Student stu) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			if (get(stu.getNo()) == null ) {

				statement = connection.prepareStatement(
						"INSERT INTO STUDENT " +
						"VALUES(?,?,?,?,?,?)"
					);
					statement.setString(1, stu.getNo());
					statement.setString(2,stu.getName());
					statement.setInt(3, stu.getEntYear());
					statement.setString(4,stu.getClassNum());
					statement.setBoolean(5,stu.isAttend());
					statement.setString(6, stu.getSchool().getCd());

					statement.executeUpdate();


			} else {

				statement = connection.prepareStatement(
						"UPDATE STUDENT SET NAME = ?, CLASS_NUM = ? "
						+
						"WHERE NO = ? AND SCHOOL_CD = ?"
					);
				statement.setString(1, stu.getName());
				statement.setString(2,stu.getClassNum());
				statement.setString(3, stu.getNo());
				statement.setString(4, stu.getSchool().getCd());

				statement.executeUpdate();

			}


		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return true;

	}



	public boolean delete(String no) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
				"DELETE FROM STUDENT  "
				+
				"WHERE NO = ?"
			);
			statement.setString(1, no);

			statement.executeUpdate();


		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return true;
	}
}
