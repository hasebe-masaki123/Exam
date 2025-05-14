package bean;

import java.util.HashMap;
import java.util.Map;

public class TestListSubject {

	int entYear;
	String studentNo;
	String studentName;
	String classNum;
	Map<Integer, Integer> points = new HashMap<>();


	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public String getPoint(int key) {
		Integer value = points.get(key);
		return (value != null) ? value.toString() : null;

	}

	public void putPoint(int key, Integer value) {
		points.put(key, value);
	}

}