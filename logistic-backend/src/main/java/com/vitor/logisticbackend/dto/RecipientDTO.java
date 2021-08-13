package com.vitor.logisticbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientDTO {

    private String name;
    private String address;
    private String addressNumber;
    private String complement;
    private String district;
}
