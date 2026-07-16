package service;

import dto.request.CreateCustomerRequest;
import dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
}
