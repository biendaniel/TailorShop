package daniel.bien.tailor_shop.model.user;

import daniel.bien.tailor_shop.model.order.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_manager")
    private Boolean manager;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}