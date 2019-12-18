package dbien.demo.controller;

import dbien.demo.domain.TextileType;
import dbien.demo.repository.TextileTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/textileTypes")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TextileTypeController {

   private final TextileTypeRepository textileTypeRepository;

    public TextileTypeController(TextileTypeRepository textileTypeRepository) {
        this.textileTypeRepository = textileTypeRepository;
    }

    @PostMapping
    public void addTextileType(@RequestBody TextileType textileType) {
        textileTypeRepository.save(textileType);
    }

    @GetMapping("/{id}")
    public Optional<TextileType> getTextileType (@PathVariable("id") Integer id) {
        return  textileTypeRepository.findById(id);
    }

    @GetMapping
    public Iterable<TextileType> getTextileTypeList() {
        return textileTypeRepository.findAll();
    }
}

