package com.vitor.logisticbackend.api.dto.response;

import com.vitor.logisticbackend.domain.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRespDTO {

    private Long id;
    private CustomerDeliveryRespDTO customer;
    private RecipientRespDTO recipient;
    private BigDecimal rate;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime deliveryDate;
    private List<OccurrenceRespDTO> occurrences;
}
