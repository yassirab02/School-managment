package usa.harvard.learning.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usa.harvard.learning.bean.Assignment;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.dao.AssignmentDao;
import usa.harvard.learning.dao.ProfDao;
import usa.harvard.learning.dao.StudentDao;
import usa.harvard.learning.dao.SubjectDao;
import usa.harvard.learning.service.facade.AssignmentService;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Override
    public int saveAssignment(Assignment assignment) {
        if (findByCode(assignment.getCode()) != null) {
            throw new RuntimeException("Assignment already exists");
        }

        if (assignment.getProf() == null || assignment.getProf().getCode() == null || assignment.getProf().getCode().isEmpty()) {
            throw new RuntimeException("The assignment prof is empty");
        }

        Prof assignmentProf = profDao.findByCode(assignment.getProf().getCode());
        if (assignmentProf == null){
            throw new RuntimeException("Assignment prof with the code "+assignmentProf.getCode()+ " not found");
        }
        Student assignmentStudent = studentDao.findByCode(assignment.getStudent().getCode());

        if (assignmentStudent == null){
            throw new RuntimeException("Assignment student with the code "+assignmentStudent.getCode()+ " not found");
        }

        Subject assignmentSubject = subjectDao.findByName(assignment.getSubject().getName());
        if (assignmentSubject == null){
            throw new RuntimeException("Assignment subject with the code "+assignmentSubject.getCode()+ " not found");
        }
        //setting the prof,subject and student
        assignment.setProf(assignmentProf);
        assignment.setStudent(assignmentStudent);
        assignment.setSubject(assignmentSubject);

        assignmentDao.save(assignment);
        return 1;
    }

    @Override
    public int updateAssignment(Assignment assignment) {
        if (assignment.getCode() == null || assignment.getCode().isEmpty()) {
            throw new RuntimeException("Assignment code is empty");
        }

        //fetching the assignment
        Assignment existingAssignment = assignmentDao.findByCode(assignment.getCode());
        if (existingAssignment == null) {
            throw new RuntimeException("Assignment with the code "+assignment.getCode()+ " not found");
        }

        //update the subject ,prof , student if provided
        if (assignment.getSubject() != null && assignment.getSubject().getName() != null && !assignment.getSubject().getName().isEmpty()) {
            Subject subject = subjectDao.findByName(assignment.getSubject().getName());
            if (subject == null) {
                throw new RuntimeException("Invalid subject name.");
            }
            existingAssignment.setSubject(subject);
        }

        if (assignment.getProf() != null && assignment.getProf().getName() != null && !assignment.getProf().getName().isEmpty()) {
            Prof prof = profDao.findByCode(assignment.getProf().getCode());
            if (prof == null) {
                throw new RuntimeException("Assignment prof with the code "+assignment.getProf().getCode()+ " not found");
            }
            existingAssignment.setProf(prof);
        }

        if (assignment.getStudent() != null && assignment.getStudent().getName() != null && !assignment.getStudent().getName().isEmpty()) {
            Student student = studentDao.findByCode(assignment.getStudent().getCode());
            if (student == null) {
                throw new RuntimeException("Assignment student with the code "+assignment.getStudent().getCode()+ " not found");
            }
            existingAssignment.setStudent(student);
        }

        // Update Title, dates, status point if provided
        if (assignment.getTitle() != null && !assignment.getTitle().isEmpty()) {
            existingAssignment.setTitle(assignment.getTitle());
        }
        if (assignment.getDueDate() != null){
            existingAssignment.setDueDate(assignment.getDueDate());
        }
        if (assignment.getSubmissionDate() != null){
            existingAssignment.setSubmissionDate(assignment.getSubmissionDate());
        }
        if (assignment.getDescription() != null && !assignment.getDescription().isEmpty()) {
            existingAssignment.setDescription(assignment.getDescription());
        }
        if (assignment.getStatus() != null) {
            existingAssignment.setStatus(assignment.getStatus());
        }
        if (assignment.getPoint() != 0.0){
            existingAssignment.setPoint(assignment.getPoint());
        }

        //save the updated assignment
        assignmentDao.save(existingAssignment);
        return 1;
    }

    @Override
    public List<Assignment> findAll() {
        return assignmentDao.findAll();
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return assignmentDao.deleteByCode(code);
    }

    @Override
    public Assignment findByCode(String code) {
        return assignmentDao.findByCode(code);
    }

    @Autowired
    private AssignmentDao assignmentDao;
    @Autowired
    private ProfDao profDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
}
