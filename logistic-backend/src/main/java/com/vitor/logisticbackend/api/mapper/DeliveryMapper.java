package com.vitor.logisticbackend.api.mapper;

import com.vitor.logisticbackend.api.dto.request.DeliveryReqDTO;
import com.vitor.logisticbackend.api.dto.response.DeliveryRespDTO;
import com.vitor.logisticbackend.domain.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    public Delivery toModel(DeliveryReqDTO deliveryReqDTO);
    public DeliveryRespDTO toDTO(Delivery delivery);
    public List<DeliveryRespDTO> toList(List<Delivery> deliveries);
}
