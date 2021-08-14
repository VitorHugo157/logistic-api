package com.vitor.logisticbackend.api.controller;

import com.vitor.logisticbackend.domain.model.Delivery;
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
    public Delivery request(@Valid @RequestBody Delivery delivery) {
        return deliveryService.requestDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> listDeliveries() {
        return deliveryService.listDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery findById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @PutMapping("/{id}/finishing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        deliveryService.finishDelivery(id);
    }
}
