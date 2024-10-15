package usa.harvard.learning.ws.dto;


public class ProfDto {

    private long id;
    private String code;
    private String name;
    private String email;
    private Long numberOfHours;

    public Long getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Long numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
