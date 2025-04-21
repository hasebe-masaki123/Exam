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
	private List<Subject> Filter(School school) throws Exception {
		List<Subject> list = new ArrayList();
		Connection con = getConnection();
		PreparedStatement statement = null;
		try {

			statement = con.prepareStatement(
					"SELECT * FROM  SUBJECT WHERE CD=? AND NAME=?"
					);
			statement.setString(1, school.getCd());
			statement.setString(2, school.getName());
			ResultSet resultSet = statement.executeQuery();


		} catch (Exception e){
			throw e;

		} finally {
			if (statement != null) statement.close();
			if (con != null) con.close();
		}

		return list;
	}
}
