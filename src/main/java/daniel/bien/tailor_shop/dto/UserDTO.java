package daniel.bien.tailor_shop.dto;


import lombok.Data;

@Data
public class UserDTO {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String password;
}
