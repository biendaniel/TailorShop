package dbien.demo.controller;

import dbien.demo.domain.Product;
import dbien.demo.domain.ProductType;
import dbien.demo.domain.Textile;
import dbien.demo.repository.ProductRepository;
import dbien.demo.repository.ProductTypeRepository;
import dbien.demo.repository.TextileRepository;
import dbien.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
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
        productService.fillPriceAndExecutionTimeInProduct(product);
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
