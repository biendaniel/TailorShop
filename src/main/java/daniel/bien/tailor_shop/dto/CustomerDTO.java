package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    private Integer id;

    private User user;

    List<OrderDTO> orders;

    List<Dimensions> dimensions;

}
