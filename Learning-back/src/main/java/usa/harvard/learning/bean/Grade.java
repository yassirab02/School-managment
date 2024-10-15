package usa.harvard.learning.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String code;
    private double gradeNote;
    @Enumerated(EnumType.STRING)
    private GradeStatus gradeStatus;
    public enum GradeStatus {
        PASSED,
        FAILED,
        IN_PROGRESS
    }

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

    public double getGradeNote() {
        return gradeNote;
    }

    public void setGradeNote(double gradeNote) {
        this.gradeNote = gradeNote;
    }

    @JsonIgnore
    public GradeStatus getGradeStatus() {
        return gradeStatus;
    }

    public void setGradeStatus(GradeStatus gradeStatus) {
        this.gradeStatus = gradeStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    private Student student;
    @ManyToOne
    private Prof prof;
    @ManyToOne
    private Subject subject;
}
