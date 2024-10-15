package usa.harvard.learning.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.StudentGrp;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.dao.ProfDao;
import usa.harvard.learning.dao.StudentGrpDao;
import usa.harvard.learning.dao.SubjectDao;
import usa.harvard.learning.service.facade.ProfService;
import usa.harvard.learning.service.facade.SubjectService;

import java.util.List;

@Service
public class ProfServiceImpl implements ProfService {

    @Override
    public List<Prof> findAll() {
        return profDao.findAll();
    }

    @Override
    public int saveProf(Prof prof) {

        if (findByCode(prof.getCode()) != null) {
            throw new RuntimeException("A professor with code " + prof.getCode() + " already exists.");
        }

        // Check if a professor with the same email exists
        if (profDao.findByEmail(prof.getEmail()) != null) {
            throw new RuntimeException("Professor with this email already exists: " + prof.getEmail());
        }

        // Ensure the student group code is provided
        if (prof.getStudentGrp() == null || prof.getStudentGrp().getCode() == null || prof.getStudentGrp().getCode().isEmpty()) {
            throw new RuntimeException("The professor must be associated with a valid student group code.");
        }


        // Fetch the existing StudentGrp from the database using its code
        StudentGrp existingStudentGrp = studentGrpDao.findByCode(prof.getStudentGrp().getCode());

        // Check if the StudentGrp exists in the database
        if (existingStudentGrp == null) {
            throw new RuntimeException("There is no group with the code: " + prof.getStudentGrp().getCode());
        }

        // Fetch the existing Subject from the database using its name
        Subject existingSubject= subjectDao.findByName(prof.getSubject().getName());

//        // Check if the Subject exists in the database
         if (existingSubject == null) {
             throw new RuntimeException("There is no subject with the name: " + prof.getSubject().getName());
          }

        // Set the fetched StudentGrp and Subject to the Prof
        prof.setStudentGrp(existingStudentGrp);
        prof.setSubject(existingSubject);

        // Save the professor
         profDao.save(prof);
         return 1;
    }

    @Override
    public int updateProf(Prof prof) {
        if (prof.getCode() == null || prof.getCode().isEmpty()) {
            throw new RuntimeException("The professor must be associated with a valid code.");
        }

        // Fetch the existing professor by code
        Prof existingProf = profDao.findByCode(prof.getCode());
        if (existingProf == null) {
            throw new RuntimeException("Professor not found with the provided code.");
        }

        // Update the student group if provided
        if (prof.getStudentGrp() != null && prof.getStudentGrp().getCode() != null && !prof.getStudentGrp().getCode().isEmpty()) {
            StudentGrp grp = studentGrpDao.findByCode(prof.getStudentGrp().getCode());
            if (grp == null) {
                throw new RuntimeException("Invalid student group code.");
            }
            existingProf.setStudentGrp(grp);
        }

        // Update the subject if provided
        if (prof.getSubject() != null && prof.getSubject().getName() != null && !prof.getSubject().getName().isEmpty()) {
            Subject subject = subjectDao.findByName(prof.getSubject().getName());
            if (subject == null) {
                throw new RuntimeException("Invalid subject name.");
            }
            existingProf.setSubject(subject);
        }

        // Update name, email, and numberOfHours if provided
        if (prof.getName() != null && !prof.getName().isEmpty()) {
            existingProf.setName(prof.getName());
        }
        if (prof.getEmail() != null && !prof.getEmail().isEmpty()) {
            existingProf.setEmail(prof.getEmail());
        }
        if (prof.getNumberOfHours() > 0) {
            existingProf.setNumberOfHours(prof.getNumberOfHours());
        }

        // Save the updated professor
        profDao.save(existingProf);
        return 1;
    }



    @Override
    @Transactional
    public int deleteByCode(String code) {
        return profDao.deleteByCode(code);
    }


    @Override
    public Prof findByCode(String code) {
        Prof prof = profDao.findByCode(code);
            return prof;
    }


    @Autowired
    private ProfDao profDao;
    @Autowired
    private StudentGrpDao studentGrpDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private SubjectService subjectService;

}
