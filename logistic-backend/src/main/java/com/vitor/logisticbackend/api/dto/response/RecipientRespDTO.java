package com.vitor.logisticbackend.api.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipientRespDTO {

    private String name;
    private String address;
    private String addressNumber;
    private String complement;
    private String district;
}
