package daniel.bien.tailor_shop.controller.product;

import daniel.bien.tailor_shop.model.product.ClothesStyle;
import daniel.bien.tailor_shop.repository.product.ClothesStyleRepository;
import daniel.bien.tailor_shop.service.product.ClothesStyleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clothesStyles")
@CrossOrigin()
public class ClothesStyleController {

    private final ClothesStyleRepository clothesStyleRepository;
    private final ClothesStyleService clothesStyleService;

    public ClothesStyleController(ClothesStyleRepository clothesStyleRepository, ClothesStyleService clothesStyleService) {
        this.clothesStyleRepository = clothesStyleRepository;
        this.clothesStyleService = clothesStyleService;
    }


    @GetMapping
    public ResponseEntity getAllClothesStyles() {
        return ResponseEntity.ok(clothesStyleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addClothesStyle(@RequestBody ClothesStyle clothesStyle) {
        clothesStyleService.addClothesStyleWithImage(clothesStyle);
        return ResponseEntity.ok("Dodano nowy fason!");
    }

    @GetMapping("/productTypes/{id}")
    public ResponseEntity getClothesStylesByProductType(@PathVariable Integer id) {
        return ResponseEntity.ok(clothesStyleRepository.findClothesStyleByProductTypes(id));
    }

}
