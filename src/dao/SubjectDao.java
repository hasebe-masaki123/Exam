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
        Subject sub = null;

        Connection con = getConnection();
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sub = new Subject();
                sub.setCd(resultSet.getString("cd"));
                sub.setName(resultSet.getString("name"));
                sub.setSchool(school);
            }
        } catch (Exception e) {
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
            statement = con.prepareStatement("SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? ORDER BY CD ASC");
            statement.setString(1, school.getCd());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Subject sub = new Subject();
                sub.setCd(resultSet.getString("cd"));
                sub.setName(resultSet.getString("name"));
                sub.setSchool(school);
                list.add(sub);
            }
        } catch (Exception e) {
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
        ResultSet resultSet = null;

        try {
            // すでに存在するか確認（cd + school_cd）
            statement = connection.prepareStatement(
                "SELECT COUNT(*) FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?"
            );
            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getSchool().getCd());
            resultSet = statement.executeQuery();

            boolean exists = false;
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
            }

            resultSet.close();
            statement.close();

            if (exists) {
                // UPDATE 処理
                statement = connection.prepareStatement(
                    "UPDATE SUBJECT SET NAME = ? WHERE CD = ? AND SCHOOL_CD = ?"
                );
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getSchool().getCd());

            } else {
                // INSERT 処理
                statement = connection.prepareStatement(
                    "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)"
                );
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd()); // ← null でないことを確認
                statement.setString(3, subject.getName());
            }

            int result = statement.executeUpdate();
            return result > 0;

        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("DELETE FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchool().getCd());

            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;
        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }
}
