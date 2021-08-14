package com.vitor.logisticbackend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceRespDTO {

    private Long id;
    private String description;
    private OffsetDateTime registerDate;
}
