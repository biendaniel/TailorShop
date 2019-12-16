package dbien.demo.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import dbien.demo.domain.Customer;
import dbien.demo.domain.Visit;
import dbien.demo.dto.CustomerDTO;
import dbien.demo.repository.VisitRepository;
import dbien.demo.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;
    private final VisitRepository visitRepository;


    public VisitController(VisitService visitService, VisitRepository visitRepository) {
        this.visitService = visitService;
        this.visitRepository = visitRepository;
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
        Iterable<Visit> visits = visitRepository.findAll();
        return ResponseEntity.ok(visits);
    }
}
