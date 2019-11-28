package com.netcracker.repository;

import com.netcracker.dto.CustomerDto;
import com.netcracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc(String firstName, String lastName);

    List<Customer> findAllByOrderByModifiedWhenDesc();


}
