package controller;

import dto.request.CreateCustomerRequest;
import dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    // Constructor Injection for: Customer Service
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello customer";
    }

    @PostMapping("/create")
    public CustomerResponse createCustomer(
            @Valid
            @RequestBody
            CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }
}
