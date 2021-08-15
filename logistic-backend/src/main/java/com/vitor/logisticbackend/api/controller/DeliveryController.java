package com.vitor.logisticbackend.api.controller;

import com.vitor.logisticbackend.api.dto.request.DeliveryReqDTO;
import com.vitor.logisticbackend.api.dto.request.OccurrenceReqDTO;
import com.vitor.logisticbackend.api.dto.response.DeliveryRespDTO;
import com.vitor.logisticbackend.api.dto.response.OccurrenceRespDTO;
import com.vitor.logisticbackend.domain.model.Occurrence;
import com.vitor.logisticbackend.domain.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveries")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryController {

    private DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryRespDTO request(@Valid @RequestBody DeliveryReqDTO delivery) {
        return deliveryService.requestDelivery(delivery);
    }

    @GetMapping
    public List<DeliveryRespDTO> listDeliveries() {
        return deliveryService.listDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryRespDTO findById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @PutMapping("/{id}/finishing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        deliveryService.finishDelivery(id);
    }

    @PostMapping("/{deliveryId}/occurrences")
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceRespDTO registerOccurrence(@PathVariable Long deliveryId, @Valid @RequestBody OccurrenceReqDTO occurrenceReqDTO) {
        return deliveryService.registerOccurrence(deliveryId, occurrenceReqDTO.getDescription());
    }

    @GetMapping("/{deliveryId}/occurrences")
    public List<OccurrenceRespDTO> listOccurrences(@PathVariable Long deliveryId) {
        return deliveryService.listOccurrencesByDeliveryId(deliveryId);
    }
}
