package com.vitor.logisticbackend.api.mapper;

import com.vitor.logisticbackend.api.dto.response.OccurrenceRespDTO;
import com.vitor.logisticbackend.domain.model.Occurrence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OccurrenceMapper {

    OccurrenceMapper INSTANCE = Mappers.getMapper(OccurrenceMapper.class);

    public OccurrenceRespDTO toDTO(Occurrence occurrence);
    public List<OccurrenceRespDTO> toList(List<Occurrence> occurrences);
}
