package app.banking_app_spring.service;

import app.banking_app_spring.dto.request.CreateCustomerRequest;
import app.banking_app_spring.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
}
