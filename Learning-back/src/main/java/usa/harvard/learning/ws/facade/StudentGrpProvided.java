package usa.harvard.learning.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.StudentGrp;
import usa.harvard.learning.service.facade.StudentGrpService;

import java.util.List;

@RestController
@RequestMapping("api/v1/groupe")
public class StudentGrpProvided {

    @GetMapping("/code/{code}")
    public StudentGrp findByCode(@PathVariable String code) {
        return studentGrpService.findByCode(code);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<StudentGrp>> findAll() {
        List<StudentGrp> studentGroups = studentGrpService.findAll();
        return ResponseEntity.ok(studentGroups);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return studentGrpService.deleteByCode(code);
    }

    @PostMapping("/save")
    public int saveStudentGrp(@RequestBody StudentGrp studentGrp) {
        return studentGrpService.saveStudentGrp(studentGrp);
    }


    @Autowired
    private StudentGrpService studentGrpService;
}
