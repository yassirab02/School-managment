package usa.harvard.learning.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Grade;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.service.facade.GradeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/grade")
public class GradeProvided {

    @GetMapping("/student/code/{studentCode}")
    public Grade findByStudentCode(@PathVariable String studentCode) {
        return gradeService.findByStudentCode(studentCode);
    }

    @GetMapping("/all-student/{gradeStatus}/{subjectName}")
    public List<Student> findStudentsByGradeStatusAndSubjectName(@PathVariable Grade.GradeStatus gradeStatus, @PathVariable String subjectName) {
        return gradeService.findStudentsByGradeStatusAndSubjectName(gradeStatus, subjectName);
    }

    @DeleteMapping("/student/code/{studentCode}")
    public int deleteByStudentCode(@PathVariable String studentCode) {
        return gradeService.deleteByStudentCode(studentCode);
    }

    @PostMapping("/add-grade")
    public int addGrade(@RequestBody Grade grade) {
        return gradeService.addGrade(grade);
    }

    @PutMapping("/update")
    public int updateGrade(@RequestBody Grade grade) {
        return gradeService.updateGrade(grade);
    }

    @Autowired
    private GradeService gradeService;
}
