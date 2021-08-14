package com.vitor.logisticbackend.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Recipient {

    @Column(name = "recipient_name", nullable = false, length = 100)
    private String name;

    @Column(name = "recipient_address", nullable = false, length = 100)
    private String address;

    @Column(name = "recipient_address_number", nullable = false, length = 10)
    private String addressNumber;

    @Column(name = "recipient_complement", length = 30)
    private String complement;

    @Column(name = "recipient_district", nullable = false, length = 60)
    private String district;
}
