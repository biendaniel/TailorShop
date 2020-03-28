package daniel.bien.tailor_shop.service.product;

import daniel.bien.tailor_shop.configuration.ProductCosts;
import daniel.bien.tailor_shop.model.product.*;
import daniel.bien.tailor_shop.repository.product.ClothesStyleRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final TextileRepository textileRepository;
    private final ClothesStyleRepository clothesStyleRepository;

    public ProductService(TextileRepository textileRepository, ClothesStyleRepository clothesStyleRepository) {
        this.textileRepository = textileRepository;
        this.clothesStyleRepository = clothesStyleRepository;
    }

    private Integer calculateProductPrice(Product product) {
        if (product.getProductType().getName() == ProductTypeName.SHIRT) {
            return product.getClothRequirements() * product.getTextile().getPriceForMeter() + ProductCosts.SHIRT_PRICE;
        } else if (product.getProductType().getName() == ProductTypeName.TROUSER) {
            return product.getClothRequirements() * product.getTextile().getPriceForMeter() + ProductCosts.TROUSER_PRICE;
        } else if (product.getProductType().getName() == ProductTypeName.JACKET) {
            return product.getClothRequirements() * product.getTextile().getPriceForMeter() + ProductCosts.JACKET_PRICE;
        }
        return 0;
    }

    public void prepareNewProduct(Product product) {
        fillProductPrice(product);
        product.setStatus(ProductStatusName.COMPOSED);
    }

    private void fillProductPrice(Product product) {
        int productPrice = calculateProductPrice(product);
        product.setPrice(productPrice);
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
