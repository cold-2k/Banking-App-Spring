package service.impl;

import dto.request.CreateCustomerRequest;
import dto.response.CustomerResponse;
import entity.Customer;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;
import service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
