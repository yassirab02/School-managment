package usa.harvard.learning.bean;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotNull
    @Column(unique = true)
    private String code;
    private String name;
    private int age;
    private String gender;
    private String email;
    private double note1;
    private double note2;
    private double noteFinal;
    private String studentPicture;
    private String studentGrpCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getNote1() {
        return note1;
    }

    public void setNote1(double note1) {
        this.note1 = note1;
    }

    public double getNote2() {
        return note2;
    }

    public void setNote2(double note2) {
        this.note2 = note2;
    }

    public double getNoteFinal() {
        return noteFinal;
    }

    public void setNoteFinal(double noteFinal) {
        this.noteFinal = noteFinal;
    }

    public String getStudentGrpCode() {
        return studentGrpCode;
    }

    public void setStudentGrpCode(String studentGrpCode) {
        this.studentGrpCode = studentGrpCode;
    }

    public String getStudentPicture() {
        return studentPicture;
    }

    public void setStudentPicture(String studentPicture) {
        this.studentPicture = studentPicture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentGrp getStudentGrp() {
        return studentGrp;
    }

    public void setStudentGrp(StudentGrp studentGrp) {
        this.studentGrp = studentGrp;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    @ManyToOne
    private StudentGrp studentGrp;
    @OneToMany
    private List<Grade> gradeList;
}
