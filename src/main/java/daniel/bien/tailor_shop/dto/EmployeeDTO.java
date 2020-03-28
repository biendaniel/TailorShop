package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {

    Integer id;

    User user;

    List<OrderDTO> orders;
}
