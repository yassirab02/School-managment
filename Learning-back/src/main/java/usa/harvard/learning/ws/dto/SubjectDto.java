package usa.harvard.learning.ws.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import usa.harvard.learning.bean.Subject;

import java.time.LocalTime;

public class SubjectDto {

    private long id;
    private String code;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING) // Store the enum as a String in the database
    private Subject.DayOfWeek teachingDay;
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
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

    public Subject.DayOfWeek getTeachingDay() {
        return teachingDay;
    }

    public void setTeachingDay(Subject.DayOfWeek teachingDay) {
        this.teachingDay = teachingDay;
    }
}
