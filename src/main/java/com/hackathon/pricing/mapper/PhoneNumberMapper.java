package com.hackathon.pricing.mapper;

import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import com.hackathon.pricing.model.request.PhoneNumberRequest;
import com.hackathon.pricing.model.response.PhoneNumberResponse;
import com.hackathon.pricing.service.BranchService;
import com.hackathon.pricing.service.impl.BranchServiceImpl;
import com.hackathon.pricing.util.PredictPriceUtil;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = IGNORE)
public interface PhoneNumberMapper {

    List<PhoneNumberResponse> entityToResponse(List<PhoneNumberEntity> phoneNumberEntity);

    List<PhoneNumberEntity> requestToEntity(List<PhoneNumberRequest> phoneNumberRequestList);

    @AfterMapping
    default void setPredictedPrice(@MappingTarget PhoneNumberEntity entity, PhoneNumberRequest request) {
        if(Optional.ofNullable(request.getPrice()).isEmpty()) {
            entity.setPrice(BigDecimal.valueOf(PredictPriceUtil.predictPrice(request.getNumber())));
        }
    }
}
