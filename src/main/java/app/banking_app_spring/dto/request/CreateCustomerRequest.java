package app.banking_app_spring.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    @Schema(
            description = "Person's first name"
    )
    @NotBlank
    @Min(3)
    private String firstName;

    @Schema(
            description = "Person's last name"
    )
    @NotBlank
    private String lastName;

    @Schema(
            description = "Person's phone number"
    )
    @NotBlank
    private String phoneNumber;

    @Schema(
            description = "Person's email ID"
    )
    @NotBlank
    @Email
    private String email;
}
