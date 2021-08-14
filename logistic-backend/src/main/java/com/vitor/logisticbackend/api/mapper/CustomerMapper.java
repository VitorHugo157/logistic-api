package com.vitor.logisticbackend.api.mapper;

import com.vitor.logisticbackend.api.dto.request.CustomerReqDTO;
import com.vitor.logisticbackend.api.dto.response.CustomerRespDTO;
import com.vitor.logisticbackend.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toModel(CustomerReqDTO customerReqDTO);
    CustomerRespDTO toDTO(Customer customer);
    List<CustomerRespDTO> toList(List<Customer> customers);
}
