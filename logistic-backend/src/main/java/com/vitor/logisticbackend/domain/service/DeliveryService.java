package com.vitor.logisticbackend.domain.service;

import com.vitor.logisticbackend.api.dto.request.DeliveryReqDTO;
import com.vitor.logisticbackend.api.dto.response.DeliveryRespDTO;
import com.vitor.logisticbackend.api.dto.response.OccurrenceRespDTO;
import com.vitor.logisticbackend.api.mapper.DeliveryMapper;
import com.vitor.logisticbackend.api.mapper.OccurrenceMapper;
import com.vitor.logisticbackend.domain.exception.BusinessException;
import com.vitor.logisticbackend.domain.model.Customer;
import com.vitor.logisticbackend.domain.model.Delivery;
import com.vitor.logisticbackend.domain.model.DeliveryStatus;
import com.vitor.logisticbackend.domain.model.Occurrence;
import com.vitor.logisticbackend.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final CustomerService customerService;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper = DeliveryMapper.INSTANCE;
    private final OccurrenceMapper occurrenceMapper = OccurrenceMapper.INSTANCE;

    @Transactional
    public DeliveryRespDTO requestDelivery(DeliveryReqDTO deliveryReq) {

        Customer customer = customerService.verifyIfCustomerExists(deliveryReq.getCustomer().getId());
        Delivery delivery = deliveryMapper.toModel(deliveryReq);
        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(OffsetDateTime.now());
        Delivery deliverySaved = deliveryRepository.save(delivery);
        return deliveryMapper.toDTO(deliverySaved);
    }

    public List<DeliveryRespDTO> listDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveryMapper.toList(deliveries);
    }

    public DeliveryRespDTO findById(Long id) {
        Delivery deliveryFound = verifyIfDeliveryExists(id);
        return deliveryMapper.toDTO(deliveryFound);
    }

    @Transactional
    public void finishDelivery(Long id) {
        Delivery delivery = verifyIfDeliveryExists(id);
        delivery.finish();
        deliveryRepository.save(delivery);
    }

    @Transactional
    public OccurrenceRespDTO registerOccurrence(Long deliveryId, String description) {
        Delivery delivery = verifyIfDeliveryExists(deliveryId);
        Occurrence occurrenceRegistered = delivery.addOccurrence(description);
        return occurrenceMapper.toDTO(occurrenceRegistered);
    }

    public List<OccurrenceRespDTO> listOccurrencesByDeliveryId(Long deliveryId) {
        Delivery delivery = verifyIfDeliveryExists(deliveryId);
        return occurrenceMapper.toList(delivery.getOccurrences());
    }

    private Delivery verifyIfDeliveryExists(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Delivery with id " + id + " doesn't exists"));
    }
}
