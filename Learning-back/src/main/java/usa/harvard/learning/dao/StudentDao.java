package usa.harvard.learning.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.StudentGrp;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    Student findByCode(String code);

    int deleteByCode(String code);

    // Delete all students by their associated StudentGrp
    int deleteByStudentGrp(StudentGrp studentGrp);

    //find all the student in a groupe
    List<Student> findByStudentGrp(StudentGrp studentGrp);

    //delete all the students in a groupe
    int deleteByStudentGrpCode(String studentGrpCode);
}
