package com.vitor.logisticbackend.api.controller;

import com.vitor.logisticbackend.domain.model.Customer;
import com.vitor.logisticbackend.domain.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public Customer updateById(@PathVariable Long id, @Valid @RequestBody Customer customer) {
//        return customerService.updateById(id, customer);
        return null;
    }
}
