package usa.harvard.learning.service.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.harvard.learning.bean.StudentGrp;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.dao.StudentDao;
import usa.harvard.learning.dao.StudentGrpDao;
import usa.harvard.learning.dao.SubjectDao;
import usa.harvard.learning.service.facade.StudentGrpService;

import java.util.List;

@Service
public class StudentGrpServiceImpl implements StudentGrpService {

    @Override
    public StudentGrp findByCode(String code) {
        return studentGrpDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        // Find the StudentGroup by code
        StudentGrp studentGroup = studentGrpDao.findByCode(code);

        if (studentGroup == null) {
            throw new RuntimeException("Student group not found with code: " + code);
        }

        // Delete all students associated with the StudentGroup
        studentDao.deleteByStudentGrp(studentGroup);

        // Step 3: Now delete the StudentGroup
        return studentGrpDao.deleteByCode(code);  // Returns 1 if deletion was successful
    }


    public int saveStudentGrp(StudentGrp studentGrp) {
        // Ensure the student group code is provided and not empty
        if (studentGrp.getCode() == null || studentGrp.getCode().isEmpty()) {
            throw new RuntimeException("The student group must have a valid code.");
        }

        // Check if a student group with the same code already exists
        StudentGrp existingStudentGrp = studentGrpDao.findByCode(studentGrp.getCode());
        if (existingStudentGrp != null) {
            throw new RuntimeException("A student group with code " + studentGrp.getCode() + " already exists.");
        }

        // Validate the number of students against the maximum allowed
        if (studentGrp.getNumberOfStudents() > studentGrp.getMaxStudents()) {
            throw new IllegalArgumentException("Number of students (" + studentGrp.getNumberOfStudents()
                    + ") cannot exceed the maximum limit of " + studentGrp.getMaxStudents());
        }

        // Ensure that at least one subject is provided
        if (studentGrp.getSubjects() == null || studentGrp.getSubjects().isEmpty()) {
            throw new RuntimeException("The student group must be associated with at least one valid subject.");
        }

        // Validate each subject and ensure it exists in the database
        for (Subject subject : studentGrp.getSubjects()) {
            if (subject == null || subject.getName() == null || subject.getName().isEmpty()) {
                throw new RuntimeException("Each subject must have a valid name.");
            }

            Subject fetchedSubject = subjectDao.findByName(subject.getName());
            if (fetchedSubject == null) {
                throw new RuntimeException("The subject " + subject.getName() + " does not exist.");
            }

            // Replace the subject in the list with the fetched one from the DB
            studentGrp.getSubjects().set(studentGrp.getSubjects().indexOf(subject), fetchedSubject);
        }

        // Save the student group with associated subjects
        studentGrpDao.save(studentGrp);
        return 1;
    }

    @Override
    public int increaseStudent(String grpCode) {
        // Find the group by code
        StudentGrp studentGrp = studentGrpDao.findByCode(grpCode);

        // Check if the group is full
        if (studentGrp.getNumberOfStudents() >= studentGrp.getMaxStudents()) {
            throw new RuntimeException("Student group is full, please find another group");
        }
        else{
            studentGrp.setNumberOfStudents(studentGrp.getNumberOfStudents()+1);
            // Save the updated group
            studentGrpDao.save(studentGrp);
           return 1;
        }

    }

    @Override
    public int decreaseStudent(String grpCode) {
        // Find the group by code
        StudentGrp studentGrp = studentGrpDao.findByCode(grpCode);

        // Check if there are any students to remove
        if (studentGrp.getNumberOfStudents() <= 0) {
            throw new RuntimeException("No students to remove, the group is already empty");
        } else {
            studentGrp.setNumberOfStudents(studentGrp.getNumberOfStudents() - 1);
            // Save the updated group
            studentGrpDao.save(studentGrp);
            return 1;
        }
    }

    @Override
    public List<StudentGrp> findAll() {
        return studentGrpDao.findAll();
    }

    @Autowired
    private  StudentGrpDao studentGrpDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
}
