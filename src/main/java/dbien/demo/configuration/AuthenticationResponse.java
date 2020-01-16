package dbien.demo.configuration;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private String jwt;
    private String userEmail;
    private Integer userId;
    private Integer customerId;
    private Integer employeeId;


    public String getJwt() {
        return jwt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
