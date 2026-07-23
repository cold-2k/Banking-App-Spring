package app.banking_app_spring.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
