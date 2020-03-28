package daniel.bien.tailor_shop.controller.product;

import daniel.bien.tailor_shop.service.product.ImageService;
import daniel.bien.tailor_shop.model.product.Image;
import daniel.bien.tailor_shop.repository.product.ClothesStyleRepository;
import daniel.bien.tailor_shop.repository.product.ImageRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/images")
@CrossOrigin()
public class ImageController {

    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final TextileRepository textileRepository;
    private final ClothesStyleRepository clothesStyleRepository;

    public ImageController(ImageRepository imageRepository, ImageService imageService, TextileRepository textileRepository, ClothesStyleRepository clothesStyleRepository) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.textileRepository = textileRepository;
        this.clothesStyleRepository = clothesStyleRepository;
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


    @PostMapping("/textile_upload")
    public ResponseEntity uploadTextileImage(@ModelAttribute MultipartFile uploadImage) {

        try {
            imageService.saveUploadedTextileImage(uploadImage);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clothesStyle_upload")
    public ResponseEntity uploadClothesStyleRepositoryImage(@ModelAttribute MultipartFile uploadImage) {

        try {
            imageService.saveUploadedClothesStyleImage(uploadImage);
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

    @RequestMapping(path = "/download/textiles/{id}", method = RequestMethod.GET)
    public ResponseEntity downloadTextileImage(@PathVariable Integer id) throws IOException {
        int imgId = 0;
        Optional<Integer> imageId = textileRepository.findImageIdByTextileId(id);
        if(imageId.isPresent()) {
         imgId = imageId.get();
        }

        Optional<Image> image = imageRepository.findById(imgId);

        String nameFile = "";

        if(image.isPresent()) {
            nameFile = image.get().getFileName();
        }

        File file = new File(FILES_LOCATION + nameFile);


        HttpHeaders header = setNecessaryHeaders();

        Path path = Paths.get(FILES_LOCATION + nameFile);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @RequestMapping(path = "/download/clothesStyles/{id}", method = RequestMethod.GET)
    public ResponseEntity downloadClothesStyleImage(@PathVariable Integer id) throws IOException { // TODO REFAKTOR!!!!!

        int imgId = 0;
        Optional<Integer> imageId = clothesStyleRepository.findImageIdByClothesStyleId(id);
        if(imageId.isPresent()) {
            imgId = imageId.get();
        }
        Optional<Image> image = imageRepository.findById(imgId);

        String nameFile = "";

        if(image.isPresent()) {
            nameFile = image.get().getFileName();
        }

        File file = new File(FILES_LOCATION + nameFile);

        HttpHeaders header = setNecessaryHeaders();

        Path path = Paths.get(FILES_LOCATION + nameFile);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    private HttpHeaders setNecessaryHeaders() {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }


}

