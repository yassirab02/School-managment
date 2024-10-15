    package usa.harvard.learning.bean;

    import jakarta.persistence.*;
    import org.antlr.v4.runtime.misc.NotNull;

    import java.util.List;

    @Entity
    public class Prof {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @NotNull
        @Column(unique = true)
        private String code;
        private String name;
        private String email;
        private Long numberOfHours;
        private String profPicture;


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

        public Long getNumberOfHours() {
            return numberOfHours;
        }

        public void setNumberOfHours(Long numberOfHours) {
            this.numberOfHours = numberOfHours;
        }

        public String getProfPicture() {
            return profPicture;
        }

        public void setProfPicture(String profPicture) {
            this.profPicture = profPicture;
        }

        public StudentGrp getStudentGrp() {
            return studentGrp;
        }

        public void setStudentGrp(StudentGrp studentGrp) {
            this.studentGrp = studentGrp;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public List<Assignment> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<Assignment> assignments) {
            this.assignments = assignments;
        }

        @ManyToOne
        private StudentGrp studentGrp;
        @OneToOne
        private Subject subject;
        // One professor can have many assignments
        @OneToMany(mappedBy = "prof")
        private List<Assignment> assignments;
    }
