package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;
import bean.TestResult;

public class TestDao extends Dao {

    // 🔵 成績データをINSERTする
    public void insert(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchoolCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    // 🔵 科目情報で成績を検索する
    public List<TestResult> filterBySubject(String entYear, String classNum, String subjectCd, String testNo) throws Exception {
        List<TestResult> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, "
                       + "t.subject_cd, t.no AS test_no, t.point "
                       + "FROM student s "
                       + "LEFT JOIN test t ON s.no = t.student_no "
                       + "AND t.subject_cd = ? AND t.no = ? "
                       + "WHERE s.ent_year = ? AND s.class_num = ? "
                       + "ORDER BY s.no";

            statement = connection.prepareStatement(sql);
            statement.setString(1, subjectCd);
            statement.setString(2, testNo);
            statement.setString(3, entYear);
            statement.setString(4, classNum);

            rSet = statement.executeQuery();

            while (rSet.next()) {
                TestResult result = new TestResult();
                result.setEntYear(rSet.getString("ent_year"));
                result.setClassNum(rSet.getString("class_num"));
                result.setStudentNo(rSet.getString("student_no"));
                result.setStudentName(rSet.getString("student_name"));
                result.setSubjectName(rSet.getString("subject_cd"));
                result.setTestNo(rSet.getInt("test_no"));
                result.setPoint(rSet.getObject("point") != null ? rSet.getInt("point") : null);
                list.add(result);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    // 🔵 学生情報で成績を検索する
    public List<TestResult> filterByStudent(String entYear, String classNum, String studentNo) throws Exception {
        List<TestResult> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT no, name, ent_year, class_num "
                       + "FROM student "
                       + "WHERE ent_year = ? "
                       + "AND class_num = ? "
                       + "AND no = ? "
                       + "ORDER BY no";

            statement = connection.prepareStatement(sql);
            statement.setString(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, studentNo);

            rSet = statement.executeQuery();

            while (rSet.next()) {
                TestResult result = new TestResult();
                result.setEntYear(rSet.getString("ent_year"));
                result.setClassNum(rSet.getString("class_num"));
                result.setStudentNo(rSet.getString("no"));
                result.setStudentName(rSet.getString("name"));
                result.setSubjectName(""); // 学生番号検索だから科目情報は空
                result.setTestNo(null);
                result.setPoint(null);
                list.add(result);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
}
