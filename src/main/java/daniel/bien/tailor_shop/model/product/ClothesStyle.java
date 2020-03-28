package daniel.bien.tailor_shop.model.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "clothes_style")
public class ClothesStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_ratio")
    private Float priceRatio;

    @Column(name = "description")
    private String description;

    @Column(name = "is_visible")
    private String visibleForCustomer;

    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    ProductType productTypes;


//    @ManyToMany
//    @JoinTable(
//        name = "image_clothes_style",
//            joinColumns = @JoinColumn(name = "clothes_style_id"),
//            inverseJoinColumns = @JoinColumn(name = "image_id")
//    )
//    private List<Image> images;
}