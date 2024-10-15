package usa.harvard.learning.ws.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.bean.StudentGrp;
import usa.harvard.learning.service.facade.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentProvided {

    @GetMapping("/code/{code}")
    public Student findByCode(@PathVariable String code) {
        return studentService.findByCode(code);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return studentService.deleteByCode(code);
    }

    @PostMapping("/save")
    public int saveStudent( @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/update")
    public int updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/all-students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("grp/all-students")
    public List<Student> findByStudentGrp(@RequestBody StudentGrp studentGrp) {
        return studentService.findByStudentGrp(studentGrp);
    }

    @DeleteMapping("/groupe/code/{code}")
    public int deleteByStudentGrp(StudentGrp studentGrp) {
        return studentService.deleteByStudentGrp(studentGrp);
    }



    @Autowired
     private StudentService studentService;
}
