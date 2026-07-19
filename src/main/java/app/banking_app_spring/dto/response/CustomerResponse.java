package app.banking_app_spring.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
