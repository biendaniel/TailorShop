package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Integer price;

    @Column(name = "comments")
    private String comments;

    @Column(name = "execution_time")
    private Integer executionTime;

    @ManyToOne
    @JoinColumn(name = "clothes_style_id")
    private ClothesStyle clothesStyle;

    @ManyToOne
    @JoinColumn(name = "textile_id")
    private Textile textile;

    @ManyToOne
    @JoinColumn(name = "dimensions_id")
    private Dimensions dimensions;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//    @ManyToOne
//    @JoinColumn(name = "product_status_id")
//    private ProductStatus productStatus; //TODO - to usunąć

    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;

    @ManyToMany
    @JoinTable(
            name = "image_product",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Image> images;

}