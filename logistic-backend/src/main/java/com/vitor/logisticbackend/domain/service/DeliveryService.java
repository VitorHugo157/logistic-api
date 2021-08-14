package com.vitor.logisticbackend.domain.service;

import com.vitor.logisticbackend.api.dto.request.DeliveryReqDTO;
import com.vitor.logisticbackend.api.dto.response.DeliveryRespDTO;
import com.vitor.logisticbackend.api.mapper.DeliveryMapper;
import com.vitor.logisticbackend.domain.exception.BusinessException;
import com.vitor.logisticbackend.domain.model.Customer;
import com.vitor.logisticbackend.domain.model.Delivery;
import com.vitor.logisticbackend.domain.model.DeliveryStatus;
import com.vitor.logisticbackend.domain.model.Occurrence;
import com.vitor.logisticbackend.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryService {

    private CustomerService customerService;
    private DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper = DeliveryMapper.INSTANCE;

    public DeliveryRespDTO requestDelivery(DeliveryReqDTO deliveryReq) {

        Customer customer = customerService.verifyIfCustomerExists(deliveryReq.getCustomer().getId());
        Delivery delivery = deliveryMapper.toModel(deliveryReq);
        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(OffsetDateTime.now());
        Delivery deliverySaved = deliveryRepository.save(delivery);
        return deliveryMapper.toDTO(deliverySaved);
    }

    public List<Delivery> listDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id) {
        return verifyIfDeliveryExists(id);
    }

    @Transactional
    public void finishDelivery(Long id) {
        Delivery delivery = verifyIfDeliveryExists(id);
        delivery.finish();
        deliveryRepository.save(delivery);
    }

    @Transactional
    public Occurrence registerOccurrence(Long deliveryId, String description) {
        Delivery delivery = verifyIfDeliveryExists(deliveryId);
        return delivery.addOccurrence(description);
    }

    private Delivery verifyIfDeliveryExists(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Delivery with id " + id + " doesn't exists"));
    }
}
