package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.order.VisitStatusName;
import daniel.bien.tailor_shop.model.user.Customer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class VisitDTO {

    private Integer id;

    private Date date;

    private VisitStatusName status;

    @Setter(AccessLevel.NONE)
    private CustomerDTO customer;

    public void setCustomerDTO(Customer customer) {
        if (customer != null) {
            this.customer = new CustomerDTO();
            this.customer.setId(customer.getId());
            this.customer.setUser(customer.getUser());
        }
    }
}
