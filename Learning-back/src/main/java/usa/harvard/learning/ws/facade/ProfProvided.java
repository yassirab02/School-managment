package usa.harvard.learning.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.service.facade.ProfService;

import java.util.List;

@RestController
@RequestMapping("api/v1/prof")
public class ProfProvided {

    @PostMapping("/save")
    public int saveProf( @RequestBody Prof prof) {
        return profService.saveProf(prof);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return profService.deleteByCode(code);
    }

    @GetMapping
    public List<Prof> findAll() {
        return profService.findAll();
    }

    @GetMapping("/code/{code}")
    public Prof findByCode(@PathVariable String code) {
        return profService.findByCode(code);
    }

    @PutMapping("/update")
    public int updateProf(@RequestBody Prof prof) {
        return profService.updateProf(prof);
    }



    @Autowired
    private ProfService profService;
}
