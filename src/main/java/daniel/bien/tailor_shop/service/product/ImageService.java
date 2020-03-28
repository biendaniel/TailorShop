package daniel.bien.tailor_shop.service.product;

import daniel.bien.tailor_shop.repository.product.ClothesStyleRepository;
import daniel.bien.tailor_shop.repository.product.ImageRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
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
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final TextileRepository textileRepository;
    private final ClothesStyleRepository clothesStyleRepository;

    public ImageService(ImageRepository imageRepository, TextileRepository textileRepository, ClothesStyleRepository clothesStyleRepository) {
        this.imageRepository = imageRepository;
        this.textileRepository = textileRepository;
        this.clothesStyleRepository = clothesStyleRepository;
    }

    private static final String FILES_LOCATION = "C:\\PROJEKTY\\tailor-shop-last-version\\uploadedFiles\\";


    public String generateNameForTextileImage() {
        Optional<Integer> lastId = textileRepository.findLastId();
        String name = "textile";
        if (lastId.isPresent()) {
            int id = lastId.get() + 1;
            name = name + "_" + id + ".jpg";
        }
        return name;
    }


    public String generateNameForClothesStyleImage() {
        Optional<Integer> lastId = clothesStyleRepository.findLastId();
        String name = "clothesStyle";
        if (lastId.isPresent()) {
            int id = lastId.get() + 1;
            name = name + "_" + id + ".jpg";
        }
        return name;
    }

    public void saveUploadedTextileImage(MultipartFile file) throws IOException, IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILES_LOCATION + generateNameForTextileImage()); //TODO Powalczyć z tą ściężką - by była bezwględna.
            Files.write(path, bytes);
        }
    }

    public void saveUploadedClothesStyleImage(MultipartFile file) throws IOException, IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILES_LOCATION + generateNameForClothesStyleImage()); //TODO Powalczyć z tą ściężką - by była bezwględna.
            Files.write(path, bytes);
        }
    }

    public ResponseEntity download() throws IOException {
        File file = new File(FILES_LOCATION + "textile1.jpg");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}
