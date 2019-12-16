package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_manager")
    private Boolean manager;

    @OneToOne
    @JoinColumn(name = "employee_status")
    private EmployeeStatus employeeStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}