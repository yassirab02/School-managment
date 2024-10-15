package usa.harvard.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.Grade;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.Subject;

import java.util.List;

@Repository
public interface GradeDao extends JpaRepository<Grade, Long> {

    Grade findByCode(String code);

    Grade findByStudentCode(String studentCode);

//    @Query("SELECT g.student FROM Grade g WHERE g.gradeStatus = :gradeStatus")
//    List<Student> findStudentsByGradeStatus(Grade.GradeStatus gradeStatus);

    @Query("SELECT g.student FROM Grade g JOIN g.subject s WHERE g.gradeStatus = :gradeStatus AND s.name = :subjectName")
   List<Student> findStudentsByGradeStatusAndSubject(Grade.GradeStatus gradeStatus, String subjectName);



    int deleteByStudentCode(String studentCode);
}
