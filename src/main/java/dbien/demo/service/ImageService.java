package dbien.demo.service;

import dbien.demo.domain.Image;
import dbien.demo.repository.ImageRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    private static final String FILES_LOCATION = "C:\\PROJEKTY\\tailor-shop-last-version\\uploadedFiles\\";

    public void uploadFile(MultipartFile uploadFile) throws IOException {
        saveUploadedFile(uploadFile);
        String originalFilename = uploadFile.getOriginalFilename();
        Image newImage = new Image();
        newImage.setUrl(originalFilename);
        imageRepository.save(newImage);
    }

    private void saveUploadedFile(MultipartFile file) throws IOException, IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILES_LOCATION + file.getOriginalFilename()); //TODO Powalczyć z tą ściężką - by była bezwględna.
//            Path path = Paths.get("/" +  file.getOriginalFilename()); //TODO Powalczyć z tą ściężką - by była bezwględna.

            Files.write(path, bytes);
        }
    }

    public ResponseEntity download() throws IOException {
        File file = new File("C:\\PROJEKTY\\tailor-shop-last-version\\uploadedFiles\\textile1.jpg");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get("C:\\PROJEKTY\\tailor-shop-last-version\\uploadedFiles\\textile1.jpg");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
