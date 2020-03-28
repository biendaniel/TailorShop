package daniel.bien.tailor_shop.controller.product;

import daniel.bien.tailor_shop.model.product.Textile;
import daniel.bien.tailor_shop.model.product.TextileType;
import daniel.bien.tailor_shop.repository.product.TextileTypeRepository;
import daniel.bien.tailor_shop.service.product.TextileService;
import daniel.bien.tailor_shop.repository.product.ImageRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
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
        if (textileService.isTextileExists(textile.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tkanina o takiej nazwie już istnieje.");
        }

        textileService.addTextileWithImage(textile);
        return ResponseEntity.ok("Dodano nową tkaninę");
    }

//    @PostMapping("/withImages")
//    public ResponseEntity addTextileWithImage(@RequestBody TextileImageDTO textileImageDTO) {
//        try{
//            textileService.addTextileWithImage(textileImageDTO);
//            return ResponseEntity.ok("ok");
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }


    @GetMapping("/{id}")
    public ResponseEntity getTextile(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(textileRepository.findById(id).orElseThrow());
    }

    @GetMapping("")
    public ResponseEntity getTextileList() {
            return ResponseEntity.ok(textileRepository.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity addTextileType(@RequestBody TextileType textileType) {
        textileTypeRepository.save(textileType);
        return ResponseEntity.ok("Dodano nowy typ tkaniny");
    }

    @GetMapping("/types/{id}")
    public ResponseEntity getTextileType(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(textileTypeRepository.findById(id));
    }

    @GetMapping("/types")
    public ResponseEntity getTextileTypeList() {
        return ResponseEntity.ok(textileTypeRepository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeTextileParameters(@PathVariable Integer id, @RequestBody Textile textile) {
        Optional<Textile> textileOptional = textileRepository.findById(id);
        if(textileOptional.isPresent()) {
            Textile loadTextile = textileOptional.get();
            loadTextile.setColor(textile.getColor());
            loadTextile.setName(textile.getName());
            loadTextile.setPriceForMeter(textile.getPriceForMeter());
            textileRepository.save(loadTextile);
            return ResponseEntity.ok("Zmieniono parametry");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/order")
    public ResponseEntity orderMoreTextile(@PathVariable Integer id, @RequestBody Integer amount) {
        textileService.sendEmailToFactory(id, amount);
        return ResponseEntity.ok("Wysłano zamówienie");
    }

    @PostMapping("/{id}/add")
    public ResponseEntity addTextileAmount(@PathVariable Integer id, @RequestBody Integer amount) {
        Textile textile = textileRepository.findById(id).orElseThrow();
        if(textile.getAmount() == null) {
            textile.setAmount(0);
        }
        textile.setAmount(textile.getAmount() + amount);
        textileRepository.save(textile);
        return ResponseEntity.ok("Dodano materiał");
    }
}

