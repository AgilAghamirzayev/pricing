package com.hackathon.pricing.mapper;

import com.hackathon.pricing.model.entity.TicketEntity;
import com.hackathon.pricing.model.request.TicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = IGNORE)
public interface TicketMapper {

    void updateEntity(@MappingTarget TicketEntity ticketEntity, TicketRequest ticketRequest);
}
