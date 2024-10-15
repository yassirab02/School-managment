package usa.harvard.learning.ws.dto;

public class StudentGrpDto {

    private Long Id;
    private String code;
    private double numberOfStudents ;
    private double maxStudent = 10;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(double numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public double getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(double maxStudent) {
        this.maxStudent = maxStudent;
    }
}
