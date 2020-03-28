package daniel.bien.tailor_shop.controller.order;

import daniel.bien.tailor_shop.model.order.Visit;
import daniel.bien.tailor_shop.model.order.VisitStatusName;
import daniel.bien.tailor_shop.model.user.VisitDateCalculator;
import daniel.bien.tailor_shop.repository.user.CustomerRepository;
import daniel.bien.tailor_shop.dto.CustomerDTO;
import daniel.bien.tailor_shop.repository.order.VisitRepository;
import daniel.bien.tailor_shop.service.order.VisitService;
import daniel.bien.tailor_shop.service.product.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/visits")
@CrossOrigin()
public class VisitController {

    private final VisitService visitService;
    private final VisitRepository visitRepository;
    private final CustomerRepository customerRepository;
    private final VisitDateCalculator visitDateCalculator;

    @Autowired
    private EmailSender emailService;

    public VisitController(VisitService visitService, VisitRepository visitRepository, CustomerRepository customerRepository, VisitDateCalculator visitDateCalculator) {
        this.visitService = visitService;
        this.visitRepository = visitRepository;
        this.customerRepository = customerRepository;
        this.visitDateCalculator = visitDateCalculator;
    }

    @PostMapping("/{id}/book")
    public ResponseEntity bookVisit(@PathVariable Integer id, @RequestBody CustomerDTO customer) {
        Visit visit = visitService.bookVisit(id, customer);
        visitRepository.save(visit);
        return ResponseEntity.ok("Booked!");
    }

    @PostMapping()
    public ResponseEntity addVisitTerm(@RequestBody String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date localDateTime = sdf.parse(date);
        Visit visit = visitService.createVisitTerm(localDateTime);
        visitRepository.save(visit);
        return ResponseEntity.status(HttpStatus.OK).body("Created!");
    }

    @GetMapping
    public ResponseEntity getVisits() {
        List<Visit> visits = visitRepository.findAllAfterDate(new Date());
        return ResponseEntity.ok(visitService.convertToDTOCollection(visits));
    }

    @GetMapping("/dayOfWeek")
    public ResponseEntity getDayOfWeek() {
        return ResponseEntity.ok(VisitService.checkTodayDayOfWeek());
    }

    @GetMapping("/test/{id}")
    public ResponseEntity getTest(@PathVariable Integer id) {
        return ResponseEntity.ok(visitDateCalculator.setProperDayOfWeek(id));
    }

    @GetMapping("/test/week")
    public ResponseEntity getTestWeek() {
        return ResponseEntity.ok(visitDateCalculator.createDatesForWeek());
    }

    @PostMapping("/add")
    public ResponseEntity addVisit(@RequestBody int id) {
        visitService.addVisitsInSingleDay(id);
        return ResponseEntity.ok("dodano");
    }

    @PostMapping("/addWeek")
    public ResponseEntity addWeekVisit() {
        visitService.addVisitsWeek();
        return ResponseEntity.ok("dodano");
    }

    @GetMapping("/getVisitsThisWeek")
    public ResponseEntity getWeekVisit() {
        return ResponseEntity.ok(visitService.getVisitNearWeek());
    }

    @PatchMapping("/{id}")
        public ResponseEntity bookVisit(@PathVariable Integer id, @RequestBody int customerId){
        Visit visit = visitRepository.findById(id).orElseThrow();
        visit.setCustomer(customerRepository.findById(customerId).orElseThrow());
        visit.setStatus(VisitStatusName.BOOKED);
        visitRepository.save(visit);
        return ResponseEntity.ok("Zarezerwowano");
    }



}
