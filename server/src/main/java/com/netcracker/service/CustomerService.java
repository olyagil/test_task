package com.netcracker.service;

import com.netcracker.dto.CustomerDto;
import com.netcracker.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Page<Customer> findAllByOrderByModifiedWhenDesc(Pageable pageable);

    List<CustomerDto> findAllByOrderByModifiedWhenDesc();

    List<CustomerDto> findByFirstNameOrLastNameOrderByModifiedWhenDesc(String firstName, String lastName);

    void create(CustomerDto dto);

    Optional<CustomerDto> readDto(Integer id);

    Optional<Customer> read(Integer id);

    void delete(Customer customer);

}
