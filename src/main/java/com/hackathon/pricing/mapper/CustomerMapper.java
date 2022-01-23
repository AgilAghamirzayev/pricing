package com.hackathon.pricing.mapper;

import com.hackathon.pricing.model.entity.CustomerEntity;
import com.hackathon.pricing.model.request.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = IGNORE)
public interface CustomerMapper {
    CustomerEntity entityToDto(CustomerRequest customerRequest);
}
