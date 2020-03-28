package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.product.Textile;
import daniel.bien.tailor_shop.model.product.ClothesStyle;
import daniel.bien.tailor_shop.model.user.Dimensions;
import lombok.Data;

@Data
public class ProductDTO {

    private String comments;
    private ClothesStyle clothesStyle;
    private Textile textile;
    private Dimensions dimensions;
    private Integer price;

}
