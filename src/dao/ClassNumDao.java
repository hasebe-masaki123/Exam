package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
    public ClassNum get(String class_num, School school) throws Exception {
        ClassNum classNum = new ClassNum();
        classNum.setClassNum(class_num);
        classNum.setSchool(school);
        return classNum;
    }


    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement sta = null;

        try {
            sta = con.prepareStatement(
                "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM ASC"
            );
            sta.setString(1, school.getCd());

            ResultSet resSet = sta.executeQuery();

            while (resSet.next()) {
                list.add(resSet.getString("CLASS_NUM"));
            }
        } finally {
            if (sta != null) sta.close();
            if (con != null) con.close();
        }

        return list;
    }


    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        try {
            boolean exists = false;

            st = con.prepareStatement(
                "SELECT COUNT(*) FROM CLASS_NUM WHERE SCHOOL_CD = ? AND CLASS_NUM = ?"
            );
            st.setString(1, classNum.getSchool().getCd());
            st.setString(2, newClassNum);
            ResultSet rs = st.executeQuery();
            if (rs.next()) exists = rs.getInt(1) > 0;
            rs.close();
            st.close();

            if (exists) {
                st = con.prepareStatement(
                    "UPDATE CLASS_NUM SET CLASS_NUM = ? WHERE SCHOOL_CD = ? AND CLASS_NUM = ?"
                );
                st.setString(1, classNum.getClassNum());
                st.setString(2, classNum.getSchool().getCd());
                st.setString(3, newClassNum);
            } else {
                st = con.prepareStatement(
                    "INSERT INTO CLASS_NUM (SCHOOL_CD, CLASS_NUM) VALUES (?, ?)"
                );
                st.setString(1, classNum.getSchool().getCd());
                st.setString(2, classNum.getClassNum());
            }

            int result = st.executeUpdate();
            return result > 0;

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }


    public boolean delete(ClassNum classNum) throws Exception {
        Connection con = getConnection();
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(
                "DELETE FROM CLASS_NUM WHERE SCHOOL_CD = ? AND CLASS_NUM = ?"
            );
            statement.setString(1, classNum.getSchool().getCd());
            statement.setString(2, classNum.getClassNum());

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } finally {
            if (statement != null) statement.close();
            if (con != null) con.close();
        }
    }
}
