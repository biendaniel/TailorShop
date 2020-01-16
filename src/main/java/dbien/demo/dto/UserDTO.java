package dbien.demo.dto;


import dbien.demo.domain.User;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDTO {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String password;
}
