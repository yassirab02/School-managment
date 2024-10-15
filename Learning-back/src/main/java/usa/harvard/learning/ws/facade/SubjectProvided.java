package usa.harvard.learning.ws.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Subject;
import usa.harvard.learning.service.facade.SubjectService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectProvided {

    @PostMapping("/save")
    public int saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @PutMapping("/update")
    public int updateSubject(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    @GetMapping("/get-all/day/{day}")
    public List<Subject> findByTeachingDay(@PathVariable Subject.DayOfWeek day) {
        return subjectService.findByTeachingDay(day);
    }



    @Autowired
    private SubjectService subjectService;
}
