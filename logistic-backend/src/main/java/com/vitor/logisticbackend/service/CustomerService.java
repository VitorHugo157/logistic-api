package com.vitor.logisticbackend.service;

import com.vitor.logisticbackend.exception.BusinessException;
import com.vitor.logisticbackend.model.Customer;
import com.vitor.logisticbackend.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {

        verifyIfIsAlreadyRegistered(customer.getEmail());
        return customerRepository.save(customer);
    }

    public Customer verifyIfCustomerExists(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new BusinessException("Customer with id " + id + " doesn't exists in the system."));
    }

    private void verifyIfIsAlreadyRegistered(String email) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        if (optCustomer.isPresent()) {
            throw new BusinessException("Customer with email " + email + " is already registered in the system.");
        }
    }
}
