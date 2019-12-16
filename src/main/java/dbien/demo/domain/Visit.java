package dbien.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private VisitStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}