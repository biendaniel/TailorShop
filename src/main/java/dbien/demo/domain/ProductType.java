package dbien.demo.domain;

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

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "product_type_textile",
            joinColumns = @JoinColumn(name = "textile_id"),
            inverseJoinColumns = @JoinColumn(name = "product_type_id"))
    List<Textile> textiles;
}