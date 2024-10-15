package usa.harvard.learning.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.Subject;

import java.util.Date;
import java.util.List;

@Repository
public interface SubjectDao extends JpaRepository<Subject,Long> {
    Subject findByName(String name);

    Subject findByCode(String code);

    int deleteByName(String name);

    @Query("SELECT s FROM Subject s WHERE :day MEMBER OF s.teachingDays")
    List<Subject> findByTeachingDay(Subject.DayOfWeek day);
}
