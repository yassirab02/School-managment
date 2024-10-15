package usa.harvard.learning.ws.dto;

public class GradeDto {
    private Long id;
    private String code;
    private double gradeNte;
    private String gradeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getGradeNte() {
        return gradeNte;
    }

    public void setGradeNte(double gradeNte) {
        this.gradeNte = gradeNte;
    }

    public String getGradeStatus() {
        return gradeStatus;
    }

    public void setGradeStatus(String gradeStatus) {
        this.gradeStatus = gradeStatus;
    }
}
