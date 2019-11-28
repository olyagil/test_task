package com.netcracker.service.impl;

import com.netcracker.converter.CustomerMapper;
import com.netcracker.dto.CustomerDto;
import com.netcracker.entity.Customer;
import com.netcracker.repository.CustomerRepository;
import com.netcracker.service.CustomerService;
import org.apache.commons.codec.language.Metaphone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final Metaphone metaphone;

    public CustomerServiceImpl(CustomerRepository repository,
                               CustomerMapper mapper,
                               Metaphone metaphone) {
        this.repository = repository;
        this.mapper = mapper;
        this.metaphone = metaphone;
    }

    @Override
    public List<CustomerDto> findAllByCustomerByModifiedWhenDesc() {
        return repository.findAllByOrderByModifiedWhenDesc()
                .stream()
                .map(customer -> (mapper.toDto(customer).get()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> findByFirstNameOrLastNameOrderByModifiedWhenDesc(String firstName, String lastName) {

        String firstNameMetaphone = metaphone.encode(firstName);
        String lastNameMetaphone = metaphone.encode(lastName);

        return repository.findByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc(firstNameMetaphone, lastNameMetaphone)
                .stream()
                .map(customer -> (mapper.toDto(customer).get()))
                .collect(Collectors.toList());
    }


    @Override
    public void create(CustomerDto dto) {
        Customer customer = mapper.toEntity(dto);
        customer.setFirstNameMetaphone(metaphone.encode(customer.getFirstName()));
        customer.setLastNameMetaphone(metaphone.encode(customer.getLastName()));
        repository.save(customer);
    }

    @Override
    public Optional<CustomerDto> readDto(Integer id) {
        return mapper.toDto(repository.findById(id).get());
    }

    @Override
    public Optional<Customer> read(Integer id) {
        return repository.findById(id);
    }


    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }
}
