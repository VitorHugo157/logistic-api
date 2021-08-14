package com.vitor.logisticbackend.api.dto.response;

import com.vitor.logisticbackend.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryDTO {

    private Long id;
    private String customerName;
    private RecipientDTO recipient;
    private BigDecimal rate;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime deliveryDate;

}
