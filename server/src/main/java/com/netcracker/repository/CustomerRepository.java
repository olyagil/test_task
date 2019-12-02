package com.netcracker.repository;

import com.netcracker.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc(String firstName, String lastName);

    List<Customer> findAllByOrderByModifiedWhenDesc();

    Page<Customer> findAllByOrderByModifiedWhenDesc(Pageable pageable);
}
