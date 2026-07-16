package dto.response;

import lombok.Setter;

@Setter
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
