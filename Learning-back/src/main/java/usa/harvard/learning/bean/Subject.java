package usa.harvard.learning.bean;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.LocalTime;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @NotNull
    @Column(unique = true)
    private String code ;
    @NotNull
    @Column(unique = true)
    private String name ;
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)// Store the enum as a String in the database
    @ElementCollection // This allows you to store a collection of enum values
//    @CollectionTable(name = "subject_teaching_days", joinColumns = @JoinColumn(name = "subject_id"))
    private Set<DayOfWeek> teachingDays;
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Set<DayOfWeek> getTeachingDays() {
        return teachingDays;
    }

    public void setTeachingDays(Set<DayOfWeek> teachingDays) {
        this.teachingDays = teachingDays;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    @OneToOne
    private Prof prof;
}
