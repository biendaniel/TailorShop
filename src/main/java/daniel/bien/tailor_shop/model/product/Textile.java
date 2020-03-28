package daniel.bien.tailor_shop.model.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "textile")
public class Textile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "price_for_meter")
    private Integer priceForMeter;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "is_visible")
    private Boolean visibleForCustomer;

    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;

    @ManyToOne
    @JoinColumn(name = "textile_type_id")
    private TextileType textileType;


//    @ManyToMany
//    @JoinTable(
//            name = "image_textile",
//            joinColumns = @JoinColumn(name = "image_id"),
//            inverseJoinColumns = @JoinColumn(name = "textile_id")
//    )
//    private List<Image> images;


}