package app.banking_app_spring.service.impl;

import app.banking_app_spring.dto.request.CreateCustomerRequest;
import app.banking_app_spring.dto.response.CustomerResponse;
import app.banking_app_spring.entity.Customer;
import org.springframework.stereotype.Service;
import app.banking_app_spring.repository.CustomerRepository;
import app.banking_app_spring.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getEmail()
        );
        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setEmail(customer.getEmail());

        return response;
    }
}
