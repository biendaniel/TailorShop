package dbien.demo.controller;

import dbien.demo.domain.Image;
import dbien.demo.repository.ImageRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping
    public void addImage(@RequestBody Image image) {
        imageRepository.save(image);
    }

    @GetMapping("/{id}")
    public Image getImage(@PathVariable("id") Integer id) {
        return imageRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<Image> getImageList() {
        return imageRepository.findAll();
    }
}

