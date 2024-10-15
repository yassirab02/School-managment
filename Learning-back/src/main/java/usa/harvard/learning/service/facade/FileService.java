package usa.harvard.learning.service.facade;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void save(MultipartFile file);

    Resource getFile(String filename);

    String uploadFile(MultipartFile file) throws IOException;
}
