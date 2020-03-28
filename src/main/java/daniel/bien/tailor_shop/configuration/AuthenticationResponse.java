package daniel.bien.tailor_shop.configuration;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse implements Serializable {
    private String jwt;
    private String userEmail;
    private Integer userId;
    private Integer customerId;
    private Integer employeeId;
    private String role;
}
