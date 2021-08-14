package com.vitor.logisticbackend.domain.service;

import com.vitor.logisticbackend.domain.exception.BusinessException;
import com.vitor.logisticbackend.domain.exception.ResourceNotFoundException;
import com.vitor.logisticbackend.domain.model.Customer;
import com.vitor.logisticbackend.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        verifyIfIsAlreadyRegistered(customer.getEmail());
        return customerRepository.save(customer);
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + "not found"));
    }

//    public Customer updateById(Long id) {
//
//    }

    @Transactional
    public void deleteById(Long id) {
        verifyIfCustomerExists(id);
        customerRepository.deleteById(id);
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
