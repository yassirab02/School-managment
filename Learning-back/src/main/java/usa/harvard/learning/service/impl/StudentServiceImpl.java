package usa.harvard.learning.service.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.StudentGrp;
import usa.harvard.learning.dao.StudentDao;
import usa.harvard.learning.dao.StudentGrpDao;
import usa.harvard.learning.service.facade.StudentGrpService;
import usa.harvard.learning.service.facade.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    @Override
    public Student findByCode(String code) {
        return studentDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByStudentGrp(StudentGrp studentGrp) {
        return studentDao.deleteByStudentGrp(studentGrp);
    }

    @Override
    public int saveStudent(Student student) {
        // Get the group code from the student object
        String grpCode = student.getStudentGrpCode();

        // Retrieve the student group from the database using the group code
        StudentGrp studentGrp = studentGrpService.findByCode(grpCode);

        // Check if the student already exists
        if (findByCode(student.getCode()) != null) {
            throw new RuntimeException("Student already exists, cannot save duplicate");
        }

        // Get the current number of students and the maximum allowed students in the group
        int numberOfStudents = studentGrp.getNumberOfStudents();
        int maxOfStudents = studentGrp.getMaxStudents();

        // Check if the student group is full
        if (numberOfStudents >= maxOfStudents) {
            throw new RuntimeException("Student group is full, please find another student group");
        }

        // Calculate noteFinal inside the save method
        double note1 = student.getNote1();
        double note2 = student.getNote2();
        student.setNoteFinal((note1 + note2) / 2);

        // Set the student group and save the student
        student.setStudentGrp(studentGrp);
        studentDao.save(student);

        // Increase the number of students in the group
        studentGrpService.increaseStudent(grpCode);

        return 1;
    }



    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> findByStudentGrp(StudentGrp studentGrp) {
        String grpCode = studentGrp.getCode();

        // Check if the group code is invalid or does not exist in the database
        StudentGrp existingGroup = studentGrpDao.findByCode(grpCode);

        if (existingGroup == null || grpCode.isEmpty()) {
            throw new RuntimeException("There is no group with this code: " + grpCode);
        }

        // Check if the group has no students
        if (existingGroup.getNumberOfStudents() == 0) {
            throw new RuntimeException("This group is empty.");
        }

        // Fetch the list of students associated with the group
        List<Student> students = studentDao.findByStudentGrp(existingGroup);

        // Check if the student list is empty after fetching from the database
        if (students.isEmpty()) {
            throw new RuntimeException("No students found in the group with the code: " + grpCode);
        }

        return students;
    }


    @Transactional
    public int deleteByStudentGrpCode(String studentGrpCode) {
        return studentDao.deleteByStudentGrpCode(studentGrpCode);
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        // Find the student by code
        Student student = findByCode(code);

        // Check if the student exists
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        // Get the group code from the student object
        String grpCode = student.getStudentGrp().getCode();

        // Find the student group by code
        StudentGrp studentGrp = studentGrpService.findByCode(grpCode);

        // Delete the student by code
        studentDao.deleteByCode(code);

        // Decrease the student count in the group
        studentGrpService.decreaseStudent(grpCode);

        return 1;
    }

    @Override
    public int updateStudent(Student student) {
        // Check if the student exists
        if (findByCode(student.getCode()) == null) {
            throw new RuntimeException("Student not found.");
        }

        // Fetch the existing student by code
        Student existingStudent = studentDao.findByCode(student.getCode());

        // Update the student group only if a new group is provided
        if (student.getStudentGrp() != null && student.getStudentGrp().getCode() != null && !student.getStudentGrp().getCode().isEmpty()) {
            StudentGrp grp = studentGrpDao.findByCode(student.getStudentGrp().getCode());
            if (grp == null) {
                throw new RuntimeException("Invalid student group code.");
            }
            existingStudent.setStudentGrp(grp); // Update the group if a valid one is provided
        }

        // Update note1 and note2 only if they are non-default values
        if (student.getNote1() != 0.0) {
            existingStudent.setNote1(student.getNote1()); // Update note1 if provided
        }

        if (student.getNote2() != 0.0) {
            existingStudent.setNote2(student.getNote2()); // Update note2 if provided
        }

        // Recalculate final note if either note1 or note2 has been updated
        existingStudent.setNoteFinal((existingStudent.getNote1() + existingStudent.getNote2()) / 2);

        // Update age only if it's not 0 (assuming 0 is an invalid age)
        if (student.getAge() != 0) {
            existingStudent.setAge(student.getAge()); // Update age if provided
        }

        // Update name if a non-empty string is provided
        if (student.getName() != null && !student.getName().isEmpty()) {
            existingStudent.setName(student.getName()); // Update name if provided
        }

        // Update email if a non-empty string is provided
        if (student.getEmail() != null && !student.getEmail().isEmpty()) {
            existingStudent.setEmail(student.getEmail()); // Update email if provided
        }

        // Update gender if a non-empty string is provided
        if (student.getGender() != null && !student.getGender().isEmpty()) {
            existingStudent.setGender(student.getGender()); // Update gender if provided
        }

        // Save the updated student
        studentDao.save(existingStudent);
        return 1;
    }




    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentGrpService studentGrpService;
    @Autowired
    private StudentGrpDao studentGrpDao;


}
