package com.vitor.logisticbackend.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Embeddable
public class Recipient {

    @NotBlank
    @Size(max = 100)
    @Column(name = "recipient_name", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(name = "recipient_address", nullable = false)
    private String address;

    @NotBlank
    @Size(max = 10)
    @Column(name = "recipient_address_number", nullable = false)
    private String addressNumber;

    @Size(max = 30)
    @Column(name = "recipient_complement")
    private String complement;

    @NotBlank
    @Size(max = 60)
    @Column(name = "recipient_district", nullable = false)
    private String district;
}
