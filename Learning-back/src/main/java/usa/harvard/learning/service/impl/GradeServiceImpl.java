package usa.harvard.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usa.harvard.learning.bean.Grade;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.dao.GradeDao;
import usa.harvard.learning.dao.ProfDao;
import usa.harvard.learning.dao.StudentDao;
import usa.harvard.learning.dao.SubjectDao;
import usa.harvard.learning.service.facade.GradeService;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {


    @Override
    public int addGrade(Grade grade) {
        if(findByCode(grade.getCode()) != null){
            throw new RuntimeException("this grade already exists");
        }

        Subject gradeSubject = subjectDao.findByName(grade.getSubject().getName());
        if(gradeSubject == null){
            throw new RuntimeException("Error finding the subject with the name " + grade.getSubject().getName());
        }

        Prof gradeProf =profDao.findByCode(gradeSubject.getProf().getCode());
        if(gradeProf == null){
            throw new RuntimeException("Error finding the prof with the code " + grade.getProf().getCode());
        }

        Student gradeStudent = studentDao.findByCode(grade.getStudent().getCode());
        if(gradeStudent == null){
            throw new RuntimeException("Error finding the student with the code " + grade.getStudent().getCode());
        }

        double gradeNote = grade.getGradeNote();

        if(gradeNote >10){
            grade.setGradeStatus(Grade.GradeStatus.PASSED);
        } else if (gradeNote <5){
            grade.setGradeStatus(Grade.GradeStatus.FAILED);
        }else{
            grade.setGradeStatus(Grade.GradeStatus.IN_PROGRESS);
        }

        //saving the params to the grade
        grade.setProf(gradeProf);
        grade.setSubject(gradeSubject);
        grade.setStudent(gradeStudent);

        gradeDao.save(grade);
        return 1;
    }

    @Override
    public int updateGrade(Grade grade) {
        if(findByCode(grade.getCode()) == null){
            throw new RuntimeException("this grade does not exists");
        }
        Grade existingGrade = gradeDao.findByCode(grade.getCode());
        if(existingGrade == null){
            throw new RuntimeException("Error finding the grade with the code " + grade.getCode());
        }

        //update the Prof,Student,Subject if provided
        if(grade.getProf() !=null &&grade.getProf().getCode() != null && grade.getProf().getCode().isEmpty()){
            Prof gradeProf =profDao.findByCode(existingGrade.getProf().getCode());
            if(gradeProf == null){
                throw new RuntimeException("Error finding the prof with the code " + existingGrade.getProf().getCode());
            }
            existingGrade.setProf(gradeProf);
           }
         if(grade.getStudent() !=null &&grade.getStudent().getCode() != null && grade.getStudent().getCode().isEmpty()){
             Student gradeStudent = studentDao.findByCode(existingGrade.getStudent().getCode());
             if(gradeStudent == null){
                 throw new RuntimeException("Error finding the student with the code " + existingGrade.getStudent().getCode());
             }
             existingGrade.setStudent(gradeStudent);
         }

         if(grade.getSubject() !=null &&grade.getSubject().getName() != null && grade.getSubject().getName().isEmpty()){
             Subject gradeSubject = subjectDao.findByName(existingGrade.getSubject().getName());
             if(gradeSubject == null){
                 throw new RuntimeException("Error finding the subject with the name " + existingGrade.getSubject().getName());
             }
             existingGrade.setSubject(gradeSubject);
         }

         // updating the other attributes
            if(grade.getGradeNote() != 0.0){
               existingGrade.setGradeNote(grade.getGradeNote());
                if(grade.getGradeNote() >10){
                    existingGrade.setGradeStatus(Grade.GradeStatus.PASSED);
                } else if (grade.getGradeNote() <5){
                    existingGrade.setGradeStatus(Grade.GradeStatus.FAILED);
                }else{
                    existingGrade.setGradeStatus(Grade.GradeStatus.IN_PROGRESS);
                }
            }

            gradeDao.save(existingGrade);
            return 1;
    }

    @Override
    public Grade findByStudentCode(String studentCode) {
        return gradeDao.findByStudentCode(studentCode);
    }

    @Override
    public List<Student> findStudentsByGradeStatusAndSubjectName(Grade.GradeStatus gradeStatus, String subjectName) {
        // Retrieve the Subject based on subjectName
        Subject gradeSubject = subjectDao.findByName(subjectName);

        // Check if the Subject was found
        if (gradeSubject == null) {
            throw new RuntimeException("Error finding the subject with the name: " + subjectName);
        }

        // Fetch students based on gradeStatus and the found subject
        return gradeDao.findStudentsByGradeStatusAndSubject(gradeStatus, gradeSubject.getName());
    }
    @Override
    public Grade findByCode(String code) {
        return gradeDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByStudentCode(String studentCode) {
        return gradeDao.deleteByStudentCode(studentCode);
    }

    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private ProfDao profDao;
}
