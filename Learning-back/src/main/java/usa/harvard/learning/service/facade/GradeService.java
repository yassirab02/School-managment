package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.Grade;
import usa.harvard.learning.bean.Student;

import java.util.List;

public interface GradeService {

    Grade findByCode(String code);

    Grade findByStudentCode(String studentCode);

    List<Student> findStudentsByGradeStatusAndSubjectName(Grade.GradeStatus gradeStatus, String subjectName);

    int deleteByStudentCode(String studentCode);

    int addGrade(Grade grade);

    int updateGrade(Grade grade);
}
