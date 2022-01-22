package com.hackathon.pricing.mapper;

import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = IGNORE)
public interface PhoneNumberMapper {

    List<PhoneNumberResponse> entityToResponse(List<PhoneNumberEntity> phoneNumberEntity);

    List<PhoneNumberEntity> requestToEntity(List<PhoneNumberRequest> phoneNumberRequestList);
}
