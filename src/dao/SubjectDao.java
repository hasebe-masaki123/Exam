package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao  extends Dao{



	public Subject get(String cd, School school) throws Exception {

		Subject subject = new Subject();

		Connection con = getConnection();
		PreparedStatement statement = null;

		try {

			statement = con.prepareStatement(
					"SELECT * FROM SUBJECT WHERE CD = ?"
					);

			statement.setString(1, cd);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {


			subject.setCd(resultSet.getString("cd"));
			subject.setName(resultSet.getString("name"));
			subject.setSchool(school);

			} else {
				subject = null;
			}


		} catch (Exception e){
			throw e;
		} finally {
			if (statement != null) statement.close();
			if (con != null) con.close();

		}

		return subject;

	}

	public List<Subject> filter(School school) throws Exception {

		List<Subject> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? "
			);
			statement.setString(1, school.getCd());


			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setCd(resultSet.getString("cd"));
				subject.setName(resultSet.getString("name"));
				subject.setSchool(school);

				list.add(subject);
			}


		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return list;

	}

	public boolean save(Subject subject) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			if (get(subject.getCd(), subject.getSchool()) == null ) {

				statement = connection.prepareStatement(
						"INSERT INTO STUDENT " +
						"VALUES(?,?,?)"
					);
					statement.setString(1, subject.getCd());
					statement.setString(2,subject.getName());
					statement.setString(3, subject.getSchool().getCd());

					statement.executeUpdate();

			} else {

				statement = connection.prepareStatement(
						"UPDATE STUDENT SET NAME = ? "
						+
						"WHERE Cd = ?"
					);
				statement.setString(1,subject.getName());
				statement.setString(2, subject.getCd());

				statement.executeUpdate();

			}

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return true;
	}

	public boolean delete(Subject subject) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

			statement = connection.prepareStatement(
					"DELETE FROM SUBJECT WHERE CD = ?"
				);

				statement.setString(1, subject.getCd());

				statement.executeUpdate();

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return true;
	}

}
