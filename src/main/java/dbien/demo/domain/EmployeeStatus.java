package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "employee_status")
class EmployeeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "current_status_name")
    private String currentStatusName;

    @Column(name = "current_status_start_date")
    private Date currentStatusChangeDate;

    @Column(name = "next_status_name")
    private String nextStatusName;

    @Column(name = "next_status_start_date")
    private Date nextStatusChangeDate;
}