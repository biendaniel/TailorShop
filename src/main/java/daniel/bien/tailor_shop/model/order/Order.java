package daniel.bien.tailor_shop.model.order;

import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.model.user.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "final_price")
    private Integer totalPrice;

    @Column(name = "comments")
    private String comments;

    @Column(name = "start_date")
    private Date startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatusName status;

    @Column(name = "is_paid")
    private Boolean paid;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Dimensions dimensions;


    @ManyToMany
    @JoinTable(
            name ="orders_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Product> products;

}