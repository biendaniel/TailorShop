package dbien.demo.service;

import dbien.demo.configuration.ProductCosts;
import dbien.demo.domain.ClothesStyle;
import dbien.demo.domain.Product;
import dbien.demo.domain.Textile;
import dbien.demo.repository.ClothesStyleRepository;
import dbien.demo.repository.ProductRepository;
import dbien.demo.repository.TextileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final TextileRepository textileRepository;
    private final ClothesStyleRepository clothesStyleRepository;

    public ProductService(ProductRepository productRepository, TextileRepository textileRepository, ClothesStyleRepository clothesStyleRepository) {
        this.productRepository = productRepository;
        this.textileRepository = textileRepository;
        this.clothesStyleRepository = clothesStyleRepository;
    }

    private Integer calculateProductPrice(Textile textile, ClothesStyle clothesStyle) {
        float priceRatio = textile.getPriceRatio() * clothesStyle.getPriceRatio();
        return Math.round(priceRatio * ProductCosts.PRICE_ORDER_BASIC_VALUE);
    }

    private Integer calculateProductExecutionTime(Textile textile, ClothesStyle clothesStyle) {
        float executionTimeRatio = textile.getExecutionTimeRatio() * clothesStyle.getPriceRatio();
        return Math.round(executionTimeRatio * ProductCosts.TIME_ORDER_BASIC_VALUE);
    }

    public void fillPriceAndExecutionTimeInProduct(Product product) {
        fillTextileAndClothesStyleById(product);
        fillProductPrice(product);
        fillProductExecutionTime(product);
    }

    private void fillProductPrice(Product product) {
        int productPrice = calculateProductPrice(product.getTextile(), product.getClothesStyle());
        product.setPrice(productPrice);
    }

    private void fillProductExecutionTime(Product product) {
        int productExecutionTime = calculateProductExecutionTime(product.getTextile(), product.getClothesStyle());
        product.setExecutionTime(productExecutionTime);
    }

    private void fillTextileAndClothesStyleById(Product product) {
        fillTextileById(product);
        fillClothesStyleById(product);
    }

    private void fillTextileById(Product product) {
        int textileId = product.getTextile().getId();
        Optional<Textile> textile = textileRepository.findById(textileId);
        textile.ifPresent(product::setTextile);
    }

    private void fillClothesStyleById(Product product) {
        int clothesStyleId = product.getClothesStyle().getId();
        Optional<ClothesStyle> clothesStyle = clothesStyleRepository.findById(clothesStyleId);
        clothesStyle.ifPresent(product::setClothesStyle);
    }


}
