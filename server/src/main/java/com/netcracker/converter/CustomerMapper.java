package com.netcracker.converter;

import com.netcracker.dto.CustomerDto;
import com.netcracker.entity.Customer;
import com.netcracker.repository.CustomerTypeRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerMapper {

    private final ModelMapper mapper;
    private final CustomerTypeRepository repository;

    public CustomerMapper(ModelMapper mapper, CustomerTypeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Customer toEntity(CustomerDto dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, Customer.class);
    }

    public Optional<CustomerDto> toDto(Customer entity) {
        return Optional.of(mapper.map(entity, CustomerDto.class));
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Customer.class, CustomerDto.class)
                .addMappings(m -> m.skip(CustomerDto::setTypeId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CustomerDto.class, Customer.class)
                .addMappings(m -> m.skip(Customer::setType)).setPostConverter(toEntityConverter());

    }

    private Converter<Customer, CustomerDto> toDtoConverter() {
        return context -> {
            Customer source = context.getSource();
            CustomerDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<CustomerDto, Customer> toEntityConverter() {
        return context -> {
            CustomerDto source = context.getSource();
            Customer destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }


    private void mapSpecificFields(Customer source, CustomerDto destination) {
        destination.setTypeId(getId(source));
        destination.setTypeCaption(getCaption(source));
    }

    private void mapSpecificFields(CustomerDto source, Customer destination) {
        destination.setType(repository.findById(source.getTypeId()).orElse(null));
    }

    private Integer getId(Customer source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getType().getId();
    }
    private String getCaption(Customer source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getType().getCaption();
    }
}
