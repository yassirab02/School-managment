package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.StudentGrp;

import java.util.List;

public interface StudentGrpService {

    StudentGrp findByCode(String code);

    List<StudentGrp> findAll();

    int deleteByCode(String code);

     int saveStudentGrp(StudentGrp studentGrp);

     int increaseStudent(String grpCode);

     int decreaseStudent(String grpCode);
}
