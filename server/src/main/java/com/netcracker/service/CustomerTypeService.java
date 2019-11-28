package com.netcracker.service;

import com.netcracker.dto.CustomerTypeDto;
import com.netcracker.entity.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerTypeService {

    List<CustomerType> read();

    Optional<CustomerTypeDto> read(Integer id);
}
