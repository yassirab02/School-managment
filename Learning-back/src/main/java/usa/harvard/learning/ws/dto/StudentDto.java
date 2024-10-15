package usa.harvard.learning.ws.dto;

public class StudentDto {

    private Long id;
    private String code;
    private String name;
    private double age;
    private String gender;
    private String email;
    private double note1;
    private double note2;
    private double noteFinal;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setAge(double age) {
        this.age = age;
    }

}
