package daniel.bien.tailor_shop.service.user;

import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.repository.user.CustomerRepository;
import daniel.bien.tailor_shop.model.user.Dimensions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DimensionsService {

    private final CustomerRepository customerRepository;

    public DimensionsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

   public List<Dimensions> getDimensionsListByCustomer(Integer id) throws Exception {
      Optional<Customer> customer = customerRepository.findById(id);
      if(customer.isPresent()) {
          return customer.get().getDimensionsList();
      }
      else {
          throw new Exception();
      }

    }
}
