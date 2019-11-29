package com.netcracker.repository;

import com.netcracker.config.TestApplication;
import com.netcracker.entity.Customer;
import com.netcracker.entity.CustomerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.language.Metaphone;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private Metaphone metaphone;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer
                .builder()
                .id(26)
                .title("t")
                .firstName("test")
                .firstNameMetaphone(metaphone.encode("test"))
                .lastName("test")
                .lastNameMetaphone("test")
                .modifiedWhen(new Date())
                .type(CustomerType.builder().id(1).caption("Residential").build())
                .build();
    }

    @Test
    void testFindAllCustomers() {
        List<Customer> customers = repository.findAll();
        int actual = customers.size();
        int expected = 25;
        Assert.assertEquals(expected, actual);
    }


    @Test
    void testFindByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc() {
        List<Customer> customers = repository.findByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc("", metaphone.encode("Boggas"));
        int actual = customers.size();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCustomer() {
//
//        Customer expected = repository.findById(1).get();
//        log.info("Customer: " + expected);
//        expected.setTitle("Mr");
//        repository.save(expected);
//        Assert.assertEquals(expected, repository.findById(expected.getId()).get());
    }

    @Test
    public void testDeleteCustomer() {
        repository.save(customer);
        repository.delete(customer);
        Assert.assertFalse(repository.findById(customer.getId()).isPresent());
    }

    @Test
    public void testFindCustomerById() {
        customer.setId(null);
        repository.save(customer);
        Optional<Customer> actual = repository.findById(customer.getId());
        Assert.assertEquals(customer, actual.get());

    }
}
