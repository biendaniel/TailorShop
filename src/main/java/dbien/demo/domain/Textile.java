package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "textile")
class Textile {

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

    @ManyToOne
    @JoinColumn(name = "textile_type_id")
    private TextileType textileType;


    @ManyToMany
    @JoinTable(
            name = "image_textile",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "textile_id")
    )
    private List<Image> images;


}