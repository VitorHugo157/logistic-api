package com.vitor.logisticbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Valid
    @ConvertGroup(to = ValidationGroups.CustomerId.class)
    @ManyToOne
    private Customer customer;

    @NotNull
    @Valid
    @Embedded
    private Recipient recipient;

    @NotNull
    @Column(nullable = false)
    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = READ_ONLY)
    private DeliveryStatus status;

    @Column(nullable = false)
    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime orderDate;

    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime deliveryDate;
}
