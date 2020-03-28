package daniel.bien.tailor_shop.service.order;

import daniel.bien.tailor_shop.dto.CustomerDTO;
import daniel.bien.tailor_shop.model.order.Visit;
import daniel.bien.tailor_shop.model.order.VisitStatusName;
import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.model.user.VisitDateCalculator;
import daniel.bien.tailor_shop.service.user.CustomerService;
import daniel.bien.tailor_shop.dto.VisitDTO;
import daniel.bien.tailor_shop.repository.order.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final CustomerService customerService;
    private final VisitDateCalculator visitDateCalculator;

    public VisitService(VisitRepository visitRepository, CustomerService customerService, VisitDateCalculator visitDateCalculator) {
        this.visitRepository = visitRepository;
        this.customerService = customerService;
        this.visitDateCalculator = visitDateCalculator;
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

    public static String checkTodayDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return String.valueOf("Dzien: " + c.get(Calendar.DAY_OF_WEEK) + " \n Data: " + c.getTime());
    }

    public void addVisitsInSingleDay(Integer dayOfWeek) {
        List<Date> dates = visitDateCalculator.getVisitsOfDay(dayOfWeek);
        List<Visit> visits = new LinkedList<>();
        dates.forEach(date -> visits.add(createVisitTerm(date)));
        visits.forEach(visit -> visitRepository.save(visit));
    }

    public void addVisitsWeek() {
        List<Date> dates = visitDateCalculator.addAllWeekVisits();
        List<Visit> visits = new LinkedList<>();
        dates.forEach(date -> visits.add(createVisitTerm(date)));
        visits.forEach(visit -> visitRepository.save(visit));
    }

    public Visit getLastAddedVisitDate() {
        return visitRepository.findById(visitRepository.getLastVisitId()).orElseThrow();

    }


    public List<Visit> getVisitNearWeek() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        return visitRepository.getVisitsToDay(new Date(), calendar.getTime());
    }

    public VisitDTO convertToDTO(Visit visit) {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setDate(visit.getDate());
        visitDTO.setId(visit.getId());
        visitDTO.setStatus(visit.getStatus());
        visitDTO.setCustomerDTO(visit.getCustomer());
        return visitDTO;
    }

    public List<VisitDTO> convertToDTOCollection(List<Visit> visits) {
        List<VisitDTO> visitDTOs = new LinkedList<>();
        visits.forEach(visit -> visitDTOs.add(convertToDTO(visit)));
        return visitDTOs;
    }

}
