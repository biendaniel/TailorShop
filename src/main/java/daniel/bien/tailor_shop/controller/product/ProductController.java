package daniel.bien.tailor_shop.controller.product;

import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.product.ProductStatusName;
import daniel.bien.tailor_shop.model.product.ProductType;
import daniel.bien.tailor_shop.model.product.Textile;
import daniel.bien.tailor_shop.repository.product.ProductRepository;
import daniel.bien.tailor_shop.repository.product.ProductTypeRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
import daniel.bien.tailor_shop.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin()
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final TextileRepository textileRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductController(ProductService productService, ProductRepository productRepository, TextileRepository textileRepository, ProductTypeRepository productTypeRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.textileRepository = textileRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product) {
        Optional<Integer> textileId = Optional.ofNullable(product.getTextile().getId());
        int textileIdInteger = 0;
        if (textileId.isPresent()) {
            textileIdInteger = textileId.get();
        }
        Optional<Textile> textile = textileRepository.findById(textileIdInteger);
        textile.ifPresent(product::setTextile);
        product.setStatus(ProductStatusName.COMPOSED);
        productRepository.save(product);
        return ResponseEntity.ok("Dodano nowy produkt");
    }

    @GetMapping("/types")
    public ResponseEntity getAllProductTypes() {
        return ResponseEntity.ok(productTypeRepository.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity addProductType(@RequestBody ProductType productType) {
        productTypeRepository.save(productType);
        return ResponseEntity.ok("Dodano typ ubrania");
    }
}
