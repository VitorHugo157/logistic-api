package com.vitor.logisticbackend.api.controller;

import com.vitor.logisticbackend.api.dto.request.CustomerReqDTO;
import com.vitor.logisticbackend.api.dto.response.CustomerRespDTO;
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
    public CustomerRespDTO create(@Valid @RequestBody CustomerReqDTO customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<CustomerRespDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping("/{id}")
    public CustomerRespDTO findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public CustomerRespDTO updateById(@PathVariable Long id, @Valid @RequestBody CustomerReqDTO customer) {
        return customerService.updateById(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
