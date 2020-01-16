package dbien.demo.controller;

import dbien.demo.domain.Image;
import dbien.demo.repository.ImageRepository;
import dbien.demo.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
@CrossOrigin() //TODO, moze to dac do serwisu? i usunac ten controller
public class ImageControllerUSUNAC {

    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public ImageControllerUSUNAC(ImageRepository imageRepository, ImageService imageService) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @PostMapping
    public void addImage(@RequestBody Image image) {
        imageRepository.save(image);
    }

    @GetMapping("/{id}")
    public Image getImage(@PathVariable Integer id) {
        return imageRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<Image> getImageList() {
        return imageRepository.findAll();
    }


    @PostMapping("/upload")
    public ResponseEntity uploadFile(@ModelAttribute MultipartFile uploadImage) {

        try {
            imageService.uploadFile(uploadImage);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lastId")
    public ResponseEntity getLastId() {
        return ResponseEntity.ok(imageRepository.getLastImageId());
    }

    private static final String FILES_LOCATION = "C:\\PROJEKTY\\tailor-shop-last-version\\uploadedFiles\\";

    @RequestMapping(path = "/download", method = RequestMethod.GET)
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

