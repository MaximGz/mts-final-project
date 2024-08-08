package ru.mts.opty_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.mts.data_models.ClientData;
import ru.mts.opty_service.model.OptyClient;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OptyDataMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    OptyClient toOptyClient(ClientData clientData);
}