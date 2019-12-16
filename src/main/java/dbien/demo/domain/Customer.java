package dbien.demo.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany()
    private List<Dimensions> dimensionsList;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}