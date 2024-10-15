package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.Subject;

import java.util.Date;
import java.util.List;

public interface SubjectService {

    int deleteByName (String name);

    Subject findByName(String name);

    Subject findByCode(String code);

    int saveSubject(Subject subject);

    int updateSubject(Subject subject);

    List<Subject> findByTeachingDay(Subject.DayOfWeek day);
}
