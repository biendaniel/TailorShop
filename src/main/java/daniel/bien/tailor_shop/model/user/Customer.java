package daniel.bien.tailor_shop.model.user;

import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.product.Product;
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
    @JoinColumn(name = " customer_id")
    private List<Dimensions> dimensionsList;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}