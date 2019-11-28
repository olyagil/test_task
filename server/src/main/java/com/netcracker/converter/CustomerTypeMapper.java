package com.netcracker.converter;

import com.netcracker.dto.CustomerTypeDto;
import com.netcracker.entity.CustomerType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerTypeMapper {

    private final ModelMapper mapper;

    public CustomerTypeMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerType toEntity(CustomerTypeDto dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, CustomerType.class);
    }

    public Optional<CustomerTypeDto> toDto(CustomerType entity) {
        return Optional.of(mapper.map(entity, CustomerTypeDto.class));
    }
}
