package dbien.demo.controller;

import dbien.demo.domain.Textile;
import dbien.demo.domain.TextileType;
import dbien.demo.repository.ImageRepository;
import dbien.demo.repository.TextileRepository;
import dbien.demo.repository.TextileTypeRepository;
import dbien.demo.service.TextileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/textiles")
@CrossOrigin()
public class TextileController {

    private final TextileRepository textileRepository;
    private final ImageRepository imageRepository;
    private final TextileService textileService;
    private final TextileTypeRepository textileTypeRepository;

    public TextileController(TextileRepository textileRepository, ImageRepository imageRepository, TextileService textileService, TextileTypeRepository textileTypeRepository) {
        this.textileRepository = textileRepository;
        this.imageRepository = imageRepository;
        this.textileService = textileService;
        this.textileTypeRepository = textileTypeRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTextile(@RequestBody Textile textile) {
        if(textileService.isTextileExists(textile.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tkanina o takiej nazwie już istnieje.");
        }
        textileRepository.save(textile);
        return ResponseEntity.ok("Dodano nową tkaninę");
    }

    @RequestMapping("/{id}")
    public Textile getTextile(@PathVariable("id") Integer id) {
        return textileRepository.findById(id).orElseThrow();
    }

    @RequestMapping("")
    public Iterable<Textile> getTextileList() {
        return textileRepository.findAll();
    }

    @PostMapping("/types")
    public void addTextileType(@RequestBody TextileType textileType) {
        textileTypeRepository.save(textileType);
    }

    @GetMapping("/types/{id}")
    public Optional<TextileType> getTextileType (@PathVariable("id") Integer id) {
        return  textileTypeRepository.findById(id);
    }

    @GetMapping("/types")
    public Iterable<TextileType> getTextileTypeList() {
        return textileTypeRepository.findAll();
    }


}

