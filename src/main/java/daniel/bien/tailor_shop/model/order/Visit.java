package daniel.bien.tailor_shop.model.order;

import daniel.bien.tailor_shop.model.user.Customer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "visit_date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VisitStatusName status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}