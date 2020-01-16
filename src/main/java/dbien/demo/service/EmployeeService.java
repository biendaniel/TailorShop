package dbien.demo.service;

import dbien.demo.domain.EmployeeStatus;
import dbien.demo.domain.EmployeeStatusName;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {

    public EmployeeStatus initNewStatus() {
        EmployeeStatus employeeStatus = new EmployeeStatus();
        employeeStatus.setCurrentStatusName(EmployeeStatusName.BEZ_ZAJECIA);
        employeeStatus.setCurrentStatusChangeDate(new Date());
        return employeeStatus;
    }
}
