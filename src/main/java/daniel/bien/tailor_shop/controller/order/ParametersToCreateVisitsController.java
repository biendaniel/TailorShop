package daniel.bien.tailor_shop.controller.order;

import daniel.bien.tailor_shop.model.order.ParametersToCreateVisits;
import daniel.bien.tailor_shop.repository.order.ParametersToCreateVisitsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitsParameters")
@CrossOrigin
public class ParametersToCreateVisitsController {

    private final ParametersToCreateVisitsRepository parametersToCreateVisitsRepository;

    public ParametersToCreateVisitsController(ParametersToCreateVisitsRepository parametersToCreateVisitsRepository) {
        this.parametersToCreateVisitsRepository = parametersToCreateVisitsRepository;
    }

    @GetMapping
    public ResponseEntity getAllParameters() {
        Iterable<ParametersToCreateVisits> parameters = parametersToCreateVisitsRepository.findAll();
        return ResponseEntity.ok(parameters);
    }

    @PostMapping
    public ResponseEntity addParameters(@RequestBody ParametersToCreateVisits parameters) {
        parametersToCreateVisitsRepository.save(parameters);
        return ResponseEntity.ok("Dodano parametr");
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeParameters(@PathVariable Integer id, @RequestBody ParametersToCreateVisits parameters) {
        Optional<ParametersToCreateVisits> params = parametersToCreateVisitsRepository.findById(id);
        if(params.isPresent()) {
            params.get().setStartVisitHour(parameters.getStartVisitHour());
            params.get().setEndVisitHour(parameters.getEndVisitHour());
            params.get().setStartVisitMinute(parameters.getStartVisitMinute());
            params.get().setEndVisitMinute(parameters.getEndVisitMinute());
            params.get().setVisitLength(parameters.getVisitLength());
            parametersToCreateVisitsRepository.save(params.get());
            return ResponseEntity.ok("Zmieniono parametry");
        }
        return ResponseEntity.notFound().build();
    }

}
