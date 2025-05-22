package bean;

import java.sql.Timestamp;

public class TestHistory {

	private int id;
    private String studentNo;
    private String subjectCd;
    private String schoolCd;
    private int testNo;
    private Integer oldPoint;
    private Integer newPoint;
    private String teacherId;
    private Timestamp updatedAt;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getSubjectCd() {
		return subjectCd;
	}
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	public String getSchoolCd() {
		return schoolCd;
	}
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}
	public int getTestNo() {
		return testNo;
	}
	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}
	public Integer getOldPoint() {
		return oldPoint;
	}
	public void setOldPoint(Integer oldPoint) {
		this.oldPoint = oldPoint;
	}
	public Integer getNewPoint() {
		return newPoint;
	}
	public void setNewPoint(Integer newPoint) {
		this.newPoint = newPoint;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
