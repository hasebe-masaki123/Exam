package bean;

import java.io.Serializable;

// 成績検索結果を格納するBean
public class TestResult implements Serializable {
    private String entYear;     // 入学年度
    private String classNum;    // クラス番号
    private String studentNo;   // 学生番号
    private String studentName; // 学生名
    private String subjectName; // 科目名（または科目コード）
    private Integer testNo;     // 実施回数
    private Integer point;      // 得点（null許容）

    // Getter & Setter
    public String getEntYear() {
        return entYear;
    }
    public void setEntYear(String entYear) {
        this.entYear = entYear;
    }
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
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
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public Integer getTestNo() {
        return testNo;
    }
    public void setTestNo(Integer testNo) {
        this.testNo = testNo;
    }
    public Integer getPoint() {
        return point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }
}
