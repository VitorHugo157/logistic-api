package com.vitor.logisticbackend.service;

import com.vitor.logisticbackend.exception.BusinessException;
import com.vitor.logisticbackend.model.Customer;
import com.vitor.logisticbackend.model.Delivery;
import com.vitor.logisticbackend.model.DeliveryStatus;
import com.vitor.logisticbackend.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryService {

    private CustomerService customerService;
    private DeliveryRepository deliveryRepository;

    public Delivery requestDelivery(Delivery delivery) {

        Customer customer = customerService.verifyIfCustomerExists(delivery.getCustomer().getId());

        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(OffsetDateTime.now());
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> listDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id) {
        return verifyIfExists(id);
    }

    private Delivery verifyIfExists(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Delivery with id " + id + " doesn't exists"));
    }
}
