package com.vitor.logisticbackend.domain.model;

import com.vitor.logisticbackend.domain.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Customer customer;

    @Embedded
    private Recipient recipient;

    @Column(nullable = false)
    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = false)
    private OffsetDateTime orderDate;

    private OffsetDateTime deliveryDate;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrences().add(occurrence);

        return occurrence;
    }

    public void finish() {
        if (cannotBeFinished()) {
            throw new BusinessException("Delivery cannot be finished.");
        }
        this.setStatus(DeliveryStatus.FINALIZADA);
        this.setDeliveryDate(OffsetDateTime.now());
    }

    private boolean canBeFinished() {
        return DeliveryStatus.PENDENTE.equals(this.getStatus());
    }

    private boolean cannotBeFinished() {
        return !canBeFinished();
    }
}
