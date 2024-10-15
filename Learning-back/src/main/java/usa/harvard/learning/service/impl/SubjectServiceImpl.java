package usa.harvard.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.dao.ProfDao;
import usa.harvard.learning.dao.SubjectDao;
import usa.harvard.learning.service.facade.ProfService;
import usa.harvard.learning.service.facade.SubjectService;

import java.util.Date;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Override
    public Subject findByName(String name) {
        return subjectDao.findByName(name);
    }

    @Override
    public Subject findByCode(String code) {
        return subjectDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByName (String name){
        return subjectDao.deleteByName(name);
    }

    @Override
    public int saveSubject(Subject subject) {

        // Check if the subject code is provided
        if (subject.getCode() == null || subject.getCode().isEmpty()) {
            throw new RuntimeException("Subject code must not be empty.");
        }

        // Check if the subject name is provided
        if (subject.getName() == null || subject.getName().isEmpty()) {
            throw new RuntimeException("Subject name must not be empty.");
        }

        // Check if the subject code already exists
        if (findByCode(subject.getCode()) != null) {
            throw new RuntimeException("A Subject with code " + subject.getCode() + " already exists.");
        }

        // Check if the subject name already exists
        if (findByName(subject.getName()) != null) {
            throw new RuntimeException("A Subject with the name " + subject.getName() + " already exists.");
        }

        if(subject.getProf() == null){
            throw new RuntimeException("Subject prof must not be empty.");
        }

        Prof subjectProf = profDao.findByCode(subject.getProf().getCode());
        if (subjectProf == null) {
            throw new RuntimeException("Professor with code " + subject.getProf().getCode() + " does not exist.");
        }

        // Optionally validate teaching day, start time, and end time
        if (subject.getTeachingDays() == null) {
            throw new RuntimeException("Teaching day must not be null.");
        }

        if (subject.getStartTime() == null) {
            throw new RuntimeException("Start time must not be null.");
        }

        if (subject.getEndTime() == null) {
            throw new RuntimeException("End time must not be null.");
        }

        if (subject.getStartTime().isAfter(subject.getEndTime())) {
            throw new RuntimeException("Start time must be before end time.");
        }

        subject.setProf(subjectProf);

        // Save the subject
        subjectDao.save(subject);

        // Automatically assign the subject to the professor
        subjectProf.setSubject(subject);
        profDao.save(subjectProf);  // Save the updated professor with the new subject

        return 1;
    }

//    @Override
//    public int saveSubject(Subject subject) {
//        // Check if the subject code is provided
//        if (subject.getCode() == null || subject.getCode().isEmpty()) {
//            throw new RuntimeException("Subject code must not be empty.");
//        }
//
//        // Check if the subject name is provided
//        if (subject.getName() == null || subject.getName().isEmpty()) {
//            throw new RuntimeException("Subject name must not be empty.");
//        }
//
//        // Check if the subject code already exists
//        if (findByCode(subject.getCode()) != null) {
//            throw new RuntimeException("A Subject with code " + subject.getCode() + " already exists.");
//        }
//
//        // Check if the subject name already exists
//        if (findByName(subject.getName()) != null) {
//            throw new RuntimeException("A Subject with the name " + subject.getName() + " already exists.");
//        }
//
//        if(subject.getProf() == null){
//            throw new RuntimeException("Subject prof must not be empty.");
//        }
//
//        Prof subjectProf = profDao.findByCode(subject.getProf().getCode());
//
//        // Optionally validate teaching day, start time, and end time
//        if (subject.getTeachingDays() == null) {
//            throw new RuntimeException("Teaching day must not be null.");
//        }
//
//        if (subject.getStartTime() == null) {
//            throw new RuntimeException("Start time must not be null.");
//        }
//
//        if (subject.getEndTime() == null) {
//            throw new RuntimeException("End time must not be null.");
//        }
//
//        if (subject.getStartTime().isAfter(subject.getEndTime())) {
//            throw new RuntimeException("Start time must be before end time.");
//        }
//
//        subject.setProf(subjectProf);
//
//        // Save the subject
//        subjectDao.save(subject);
//        return 1;
//    }

    @Override
    public int updateSubject(Subject subject) {
        // Validate the subject code
        if (subject.getCode() == null || subject.getCode().isEmpty()) {
            throw new RuntimeException("Subject code must not be empty.");
        }

        // Fetching the existing subject based on the provided code
        Subject existingSubject = subjectDao.findByCode(subject.getCode());
        if (existingSubject == null) {
            throw new RuntimeException("Subject with code " + subject.getCode() + " does not exist.");
        }

        // Update the professor if provided
        if (subject.getProf() != null && !subject.getProf().getCode().isEmpty()) {
            // Fetch the professor using the provided professor code
            Prof subjectProf = profDao.findByCode(subject.getProf().getCode());
            if (subjectProf == null) {
                throw new RuntimeException("Professor with code " + subject.getProf().getCode() + " does not exist.");
            }
            existingSubject.setProf(subjectProf); // Set the new professor
        }

        // Update other attributes only if provided
        if (subject.getName() != null && !subject.getName().isEmpty()) {
            existingSubject.setName(subject.getName());
        }
        if (subject.getTeachingDays() != null) {
            existingSubject.setTeachingDays(subject.getTeachingDays());
        }
        if (subject.getStartTime() != null) {
            existingSubject.setStartTime(subject.getStartTime());
        }
        if (subject.getEndTime() != null) {
            existingSubject.setEndTime(subject.getEndTime());
        }

        // Saving the changes to the subject
        subjectDao.save(existingSubject);
        return 1; // Indicate success
    }


    @Override
    public List<Subject> findByTeachingDay(Subject.DayOfWeek day) {
        return subjectDao.findByTeachingDay(day);
    }

    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private ProfDao profDao;
    @Autowired
    private ProfService profService;

}