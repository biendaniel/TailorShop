package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "employee_status")
public class EmployeeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status_name")
    private EmployeeStatusName currentStatusName;

    @Column(name = "current_status_start_date")
    private Date currentStatusChangeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "next_status_name")
    private EmployeeStatusName nextStatusName;

    @Column(name = "next_status_start_date")
    private Date nextStatusChangeDate;
}