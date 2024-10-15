package usa.harvard.learning.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.Resource; // Correct import
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.Student;
import usa.harvard.learning.dao.ProfDao;
import usa.harvard.learning.dao.StudentDao;
import usa.harvard.learning.service.facade.FileService;

@RestController
@RequestMapping("api/v1/picture")
public class PictureProvided {

    @Autowired
    private FileService fileService;

    @Autowired
    private ProfDao profDao;

    @Autowired
    private StudentDao studentDao;

    @PostMapping("/prof/{id}/uploadProfPicture")
    public ResponseEntity<String> uploadProfPicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Prof prof = profDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prof not found"));
            String profilePicturePath = fileService.uploadFile(file);
            prof.setProfPicture(profilePicturePath);
            profDao.save(prof);
            return ResponseEntity.ok("Profile picture uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading profile picture");
        }
    }

    @PostMapping("/student/{id}/uploadStudentPicture")
    public ResponseEntity<String> uploadStudentPicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Student student = studentDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
            String profilePicturePath = fileService.uploadFile(file);
            student.setStudentPicture(profilePicturePath);
            studentDao.save(student);
            return ResponseEntity.ok("Profile picture uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading profile picture");
        }
    }

    @GetMapping("/student/{id}/picture")
    public ResponseEntity<Resource> getStudentPicture(@PathVariable Long id) {
        try {
            Student student = studentDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
            String picturePath = student.getStudentPicture();
            Resource resource = fileService.getFile(picturePath); // Ensure this returns org.springframework.core.io.Resource
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/prof/{id}/picture")
    public ResponseEntity<Resource> getProfPicture(@PathVariable Long id) {
        try {
            Prof prof = profDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prof not found"));
            String picturePath = prof.getProfPicture();
            Resource resource = fileService.getFile(picturePath); // Ensure this returns org.springframework.core.io.Resource
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
