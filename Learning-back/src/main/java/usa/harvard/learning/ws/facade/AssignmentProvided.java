package usa.harvard.learning.ws.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Assignment;
import usa.harvard.learning.service.facade.AssignmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/assignment")
public class AssignmentProvided {

    @GetMapping("/get/code/{code}")
    public Assignment findByCode(@PathVariable String code) {
        return assignmentService.findByCode(code);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return assignmentService.deleteByCode(code);
    }

    @GetMapping("/get-all")
    public List<Assignment> findAll() {
        return assignmentService.findAll();
    }

    @PostMapping("/save")
    public int saveAssignment(@RequestBody Assignment assignment) {
        return assignmentService.saveAssignment(assignment);
    }

    @PutMapping("/update")
    public int updateAssignment(@RequestBody Assignment assignment) {
        return assignmentService.updateAssignment(assignment);
    }


    @Autowired
    private AssignmentService assignmentService;
}
