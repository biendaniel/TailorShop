package daniel.bien.tailor_shop.model.product;

import daniel.bien.tailor_shop.model.user.Dimensions;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "clothes_style_id")
    private ClothesStyle clothesStyle;

    @ManyToOne
    @JoinColumn(name = "textile_id")
    private Textile textile;

    @ManyToOne
    @JoinColumn(name = "dimensions_id")
    private Dimensions dimensions;

    @Column(name = "cloth_requirements")
    private Integer clothRequirements;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;


    @Enumerated(EnumType.STRING)
    private ProductStatusName status;


    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;


}