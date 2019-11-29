//package com.netcracker.service.impl;
//
//import com.netcracker.converter.CustomerMapper;
//import com.netcracker.dto.CustomerDto;
//import com.netcracker.entity.Customer;
//import com.netcracker.entity.CustomerType;
//import com.netcracker.repository.CustomerRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.language.Metaphone;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@Slf4j
//class CustomerServiceImplTest {
//    @Autowired
//    @InjectMocks
//    private CustomerServiceImpl service;
//
//    @Autowired
//    @InjectMocks
//    private CustomerMapper mapper;
//
//    @Autowired
//    private Metaphone metaphone;
//
//    @Mock
//    private CustomerRepository repository;
//
//    private Customer expected;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        expected = Customer
//                .builder()
//                .id(26)
//                .title("t")
//                .firstName("test")
//                .firstNameMetaphone(metaphone.encode("test"))
//                .lastName("test")
//                .lastNameMetaphone("test")
//                .modifiedWhen(new Date())
//                .type(CustomerType.builder().id(1).caption("Residential").build())
//                .build();
//    }
//
//    @Test
//    void findAllByCustomerByModifiedWhenDesc() {
//        List<Customer> countries = spy(new ArrayList<>());
//        int expected = 25;
//        doReturn(expected).when(countries).size();
//        when(repository.findAll()).thenReturn(countries);
//        countries = service.findAllByCustomerByModifiedWhenDesc();
//        Assert.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    void findByFirstNameOrLastNameOrderByModifiedWhenDesc() {
//        when(repository.findByFirstNameMetaphoneOrLastNameMetaphoneOrderByModifiedWhenDesc(anyString(), anyString()))
//                .thenReturn(Arrays.asList(expected));
//        List<CustomerDto> actual = service.findByFirstNameOrLastNameOrderByModifiedWhenDesc("test", "test");
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    void create() {
////        when(repository.save(any(Customer.class))).thenReturn(1);
////        Long actual = service.create(expected);
////        Assert.assertEquals(expected.getId(), actual);
//    }
//
////    @Test
////    void testReadById() {
////        when(repository.findById(anyInt())).thenReturn(Optional.of(expected));
////        Customer actual = repository.findById(expected.getId()).get();
////        Assert.assertEquals(expected, actual);
////    }
//
//
////    @Test
////    void delete() {
////        when(repository.delete(anyInt())).thenReturn(true);
////        boolean actual = service.delete(expected.getId());
////        Assert.assertTrue(actual);
////    }
//
//
//}
