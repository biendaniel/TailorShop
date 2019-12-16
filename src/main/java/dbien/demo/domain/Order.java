package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "final_price")
    private Float totalPrice;

    @Column(name = "comments")
    private String comments;

    @Column(name = "execution_time")
    private Integer executiontime;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}