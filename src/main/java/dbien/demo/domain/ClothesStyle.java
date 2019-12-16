package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "clothes_style")
class ClothesStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_ratio")
    private Float priceRatio;

    @Column(name = "execution_time_ratio")
    private Float executionTimeRatio;

    @ManyToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;


    @ManyToMany
    @JoinTable(
        name = "image_clothes_style",
            joinColumns = @JoinColumn(name = "clothes_style_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;
}