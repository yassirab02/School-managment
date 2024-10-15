package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.StudentGrp;

import java.util.List;

public interface StudentService {

  Student findByCode(String code);

  List<Student> findAll();

  List<Student> findByStudentGrp(StudentGrp studentGrp);

  int deleteByCode(String code);

  // Delete all students by their associated StudentGrp
  int deleteByStudentGrp(StudentGrp studentGrp);

  int saveStudent(Student student);

  int updateStudent(Student student);
}
