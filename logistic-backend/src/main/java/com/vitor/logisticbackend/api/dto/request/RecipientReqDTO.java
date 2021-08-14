package com.vitor.logisticbackend.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientReqDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotBlank
    @Size(max = 10)
    private String addressNumber;

    @Size(max = 30)
    private String complement;

    @NotBlank
    @Size(max = 60)
    private String district;
}
