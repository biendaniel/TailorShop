package daniel.bien.tailor_shop.model.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "textile_type")

public class TextileType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_natural", nullable = false)
    private Boolean natural;

}
