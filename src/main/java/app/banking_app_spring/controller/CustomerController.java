package app.banking_app_spring.controller;

import app.banking_app_spring.dto.request.CreateCustomerRequest;
import app.banking_app_spring.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.banking_app_spring.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    // Constructor Injection for: Customer Service
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid
            @RequestBody
            CreateCustomerRequest createCustomerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(createCustomerRequest));
    }
}
