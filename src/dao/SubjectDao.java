package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

	public Subject get(String cd, School school) throws Exception {
		Subject sub = new Subject();

		Connection con = getConnection();

		PreparedStatement statement = null;

		try {

			statement = con.prepareStatement(
					"SELECT * FROM SUBJECT WHERE CD = ?"
					);

			statement.setString(1, cd);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {


			sub.setCd(resultSet.getString("cd"));
			sub.setName(resultSet.getString("name"));

			school.setCd(resultSet.getString("school_cd"));
			sub.setSchool(school);

			} else {
				sub = null;
			}


		} catch (Exception e){
			throw e;
		} finally {
			if (statement != null) statement.close();
			if (con != null) con.close();

		}


		return sub;
}
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement statement = null;

		try {

			statement = con.prepareStatement(
					"SELECT * FROM SUBJECT WHERE SCHOOL_CD=?"
					);
			statement.setString(1, school.getCd());
			ResultSet resultSet = statement.executeQuery();


			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setCd(resultSet.getString("cd"));
				sub.setName(resultSet.getString("name"));

				sub.setSchool(school);

				list.add(sub);
			}
		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (con != null) con.close();
		}

		return list;
	}

	public boolean save(Subject subject) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {

				statement = connection.prepareStatement(
						"INSERT INTO SUBJECT " +
						"VALUES(?,?,?)"
					);
					statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getCd());
					statement.setString(3, subject.getName());

					statement.executeUpdate();



		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}

		return true;

	}

	public boolean delete(Subject subject) throws Exception {
	    Connection con = getConnection();
	    PreparedStatement st = null;
	    try {
	        st = con.prepareStatement("DELETE FROM SUBJECT"
	        		+ "WHERE CD=?");
	        st.setString(1, subject.getCd());

	        st.executeUpdate();
	        return true;
	    } finally {
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }
	}
}
