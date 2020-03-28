package daniel.bien.tailor_shop.model.product;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "product_type")
public class ProductType { //TODO usunac textiles?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Enumerated(EnumType.STRING)
    private ProductTypeName name;

    @Column(name = "basic_price")
    private Integer basicPrice;
}