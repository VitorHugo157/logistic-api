package com.vitor.logisticbackend.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomerIdReqDTO {

    @NotNull
    private Long id;
}
