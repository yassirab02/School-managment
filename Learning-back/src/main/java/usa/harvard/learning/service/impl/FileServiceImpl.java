package usa.harvard.learning.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usa.harvard.learning.service.facade.FileService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final String uploadDir = "uploads/";

    @Value("${file.path}")
    private String filePath;

    @Override
    public void save(MultipartFile file) {
        String dir = System.getProperty("user.dir") + File.separator + filePath;
        try {
            file.transferTo(new File(dir + File.separator + file.getOriginalFilename()));
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }

    @Override
    public Resource getFile(String filename){
        String dir = System.getProperty("user.dir") + File.separator + filePath + File.separator + filename;
        Path path = Paths.get(dir);

        try {
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    // String dir = System.getProperty("user.dir") + "/" + filePath + "/" + filename;:
    //System.getProperty("user.dir"): This retrieves the current working directory of the Java application. This is useful when you want to access files relative to where your application is running.
    //filePath: This is assumed to be a class-level variable (not shown in your snippet) that holds the path to the directory where the files are stored. Ensure this variable is properly initialized before calling getFile.
    //The resulting dir string will look something like C:/Users/User/IdeaProjects/Learning/uploads/Bs-logo.jpg (assuming filePath is uploads and filename is Bs-logo.jpg).
    //The path separators are using the forward slash /, which works on all operating systems in Java, but if you're working specifically with Windows, you may want to consider using File.separator to avoid issues with path formatting.
}
