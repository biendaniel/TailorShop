package dbien.demo.controller;

import dbien.demo.domain.ClothesStyle;
import dbien.demo.repository.ClothesStyleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clothesStyles")
public class ClothesStyleController { // TODO, a moze by tak usunac i zostawic samo TextileController? np: /textiles/types/{id}..

    private final ClothesStyleRepository clothesStyleRepository;

    public ClothesStyleController(ClothesStyleRepository clothesStyleRepository) {
        this.clothesStyleRepository = clothesStyleRepository;
    }


    @GetMapping
    public ResponseEntity getAllClothesStyles() {
        return ResponseEntity.ok(clothesStyleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addClothesStyle(@RequestBody ClothesStyle clothesStyle) {
        clothesStyleRepository.save(clothesStyle);
        return ResponseEntity.ok("Dodano nowy fason!");
    }

}
