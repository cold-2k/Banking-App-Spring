package app.banking_app_spring.repository;

import app.banking_app_spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(String customerId);
}
