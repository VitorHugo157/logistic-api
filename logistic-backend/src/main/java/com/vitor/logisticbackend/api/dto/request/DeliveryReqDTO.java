package com.vitor.logisticbackend.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryReqDTO {

    @NotNull
    @Valid
    private CustomerIdReqDTO customer;

    @NotNull
    @Valid
    private RecipientReqDTO recipient;

    @NotNull
    @Min(0)
    private BigDecimal rate;
}
