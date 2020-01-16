package dbien.demo.service;

import dbien.demo.domain.Customer;
import dbien.demo.domain.Visit;
import dbien.demo.domain.VisitStatusName;
import dbien.demo.dto.CustomerDTO;
import dbien.demo.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final CustomerService customerService;

    public VisitService(VisitRepository visitRepository, CustomerService customerService) {
        this.visitRepository = visitRepository;
        this.customerService = customerService;
    }

    public Visit bookVisit(Integer id, CustomerDTO customerDTO) {
        Optional<Visit> visit = visitRepository.findById(id);
        Customer customer = customerService.findCustomerById(customerDTO.getId());
        visit.ifPresent(value -> {
                    value.setCustomer(customer);
                    value.setStatus(VisitStatusName.BOOKED);
                }
        );
        return visit.orElseThrow();
    }

    public Visit createVisitTerm(Date date) {
        Visit visit = new Visit();
        visit.setDate(date);
        visit.setStatus(VisitStatusName.FREE);
        return visit;
    }

    public void addVisitTerm(String term) {

    }

    public void addVisitTermsList(List<String> terms) {


    }


}
