package dbien.demo.controller;

import dbien.demo.domain.Textile;
import dbien.demo.repository.TextileRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/textiles")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TextileController {

    private final TextileRepository textileRepository;

    public TextileController(TextileRepository textileRepository) {
        this.textileRepository = textileRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTextile(@RequestBody Textile textile) {
        textileRepository.save(textile);
    }

    @RequestMapping("/{id}")
    public Textile getTextile(@PathVariable("id") Integer id) {
        return textileRepository.findById(id).orElseThrow();
    }

    @RequestMapping("")
    public Iterable<Textile> getTextileList() {
        return textileRepository.findAll();
    }
}

