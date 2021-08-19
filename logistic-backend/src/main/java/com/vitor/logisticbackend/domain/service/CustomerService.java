package com.vitor.logisticbackend.domain.service;

import com.vitor.logisticbackend.api.dto.request.CustomerReqDTO;
import com.vitor.logisticbackend.api.dto.response.CustomerRespDTO;
import com.vitor.logisticbackend.api.mapper.CustomerMapper;
import com.vitor.logisticbackend.domain.exception.BusinessException;
import com.vitor.logisticbackend.domain.exception.ResourceNotFoundException;
import com.vitor.logisticbackend.domain.model.Customer;
import com.vitor.logisticbackend.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Transactional
    public CustomerRespDTO createCustomer(CustomerReqDTO customerReq) {
        verifyIfIsAlreadyRegistered(customerReq.getEmail());
        Customer customer = customerMapper.toModel(customerReq);
        Customer customerSaved = customerRepository.save(customer);
        return customerMapper.toDTO(customerSaved);
    }

    public List<CustomerRespDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toList(customers);
    }

    public CustomerRespDTO findById(Long id) {
        Customer customerFound = verifyIfCustomerExists(id);
        return customerMapper.toDTO(customerFound);
    }

    public CustomerRespDTO updateById(Long id, CustomerReqDTO customerReqDTO) {
        verifyIfCustomerExists(id);
        Customer customerToUpdate = customerMapper.toModel(customerReqDTO);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.toDTO(updatedCustomer);
    }

    @Transactional
    public void deleteById(Long id) {
        verifyIfCustomerExists(id);
        customerRepository.deleteById(id);
    }

    public Customer verifyIfCustomerExists(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found in the system."));
    }

    private void verifyIfIsAlreadyRegistered(String email) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(email);
        if (optCustomer.isPresent()) {
            throw new BusinessException("Customer with email " + email + " is already registered in the system.");
        }
    }
}
