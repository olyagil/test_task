package com.netcracker.service.impl;

import com.netcracker.converter.CustomerTypeMapper;
import com.netcracker.dto.CustomerTypeDto;
import com.netcracker.entity.CustomerType;
import com.netcracker.repository.CustomerTypeRepository;
import com.netcracker.service.CustomerTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerTypeServiceImpl implements CustomerTypeService {

    private final CustomerTypeRepository repository;
    private final CustomerTypeMapper mapper;

    public CustomerTypeServiceImpl(CustomerTypeRepository repository,
                                   CustomerTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerType> read() {
        return repository.findAll();
    }

    @Override
    public Optional<CustomerTypeDto> read(Integer id) {
        return mapper.toDto(repository.findById(id).get());
    }


}
