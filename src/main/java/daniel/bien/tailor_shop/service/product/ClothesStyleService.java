package daniel.bien.tailor_shop.service.product;

import daniel.bien.tailor_shop.model.product.Image;
import daniel.bien.tailor_shop.repository.product.ClothesStyleRepository;
import daniel.bien.tailor_shop.repository.product.ImageRepository;
import daniel.bien.tailor_shop.model.product.ClothesStyle;
import org.springframework.stereotype.Service;

@Service
public class ClothesStyleService {

    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final ClothesStyleRepository clothesStyleRepository;

    public ClothesStyleService(ImageService imageService, ImageRepository imageRepository, ClothesStyleRepository clothesStyleRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.clothesStyleRepository = clothesStyleRepository;
    }


    public void addClothesStyleWithImage(ClothesStyle clothesStyle) {
        Image image = new Image();
        image.setFileName(imageService.generateNameForClothesStyleImage());
        imageRepository.save(image);
        clothesStyle.setMainImage(image);
        clothesStyleRepository.save(clothesStyle);
    }


}
