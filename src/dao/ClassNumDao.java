package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{

	public ClassNum get(String class_num, School school) throws Exception {

		ClassNum classNum = new ClassNum();

		return classNum;
	}

	public  List<String> filter(School school) throws Exception {

		List<String> list = new ArrayList();
		Connection con = getConnection();
		PreparedStatement sta = null;

		try {
			sta = con.prepareStatement(
					"SELECT CLASS_NUM FROM CLASS_NUM "
					+
					"WHERE SCHOOL_CD = ?"
					);
			sta.setString(1, school.getCd());

			ResultSet resSet = sta.executeQuery();


			while(resSet.next()) {
				list.add(resSet.getString("CLASS_NUM"));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (sta != null) sta.close();
			if (con != null) con.close();
		}

		return list;
	}

	public boolean save(ClassNum classNum) throws Exception {

		return true;
	}

	public boolean save(ClassNum classNum, String newClassNum) throws Exception {

		return true;
	}

}
