package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.user.Dimensions;
import lombok.Data;

import java.util.List;

@Data
public class PreOrderDTO {
    Dimensions dimensions;

    List<Product> products;

}
