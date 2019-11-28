package com.netcracker.controller;

import com.netcracker.dto.CustomerDto;
import com.netcracker.entity.Customer;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.service.CustomerService;
import com.netcracker.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*---get all customers---*/
    @GetMapping({"/", Constants.GET_ALL_CUSTOMERS})
    public ResponseEntity<List<CustomerDto>> allCustomers(HttpServletRequest request) {
        List<CustomerDto> customers = customerService.findAllByCustomerByModifiedWhenDesc();
        log.info("The customers were requested from ip address: " + request.getRemoteAddr());
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping({"/", Constants.SEARCH_CUSTOMER})
    public ResponseEntity<List<CustomerDto>> filteredCustomers(@RequestParam String firstName,
                                                               @RequestParam String lastName,
                                                               HttpServletRequest request) {
        List<CustomerDto> customers = customerService.findByFirstNameOrLastNameOrderByModifiedWhenDesc(firstName, lastName);
        log.info("The searched customers were requested from ip address: " + request.getRemoteAddr());
        return ResponseEntity.ok().body(customers);
    }

    /*---Get a customer by id---*/
    @GetMapping(Constants.GET_CUSTOMER_BY_ID)
    public ResponseEntity<CustomerDto> customer(@PathVariable("id") Integer id,
                                                HttpServletRequest request)
            throws ResourceNotFoundException {

        CustomerDto customer = customerService.readDto(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        log.info(String.format("The customer with id: %d was requested from ip address: %s", id, request.getRemoteAddr()));
        return ResponseEntity.ok().body(customer);
    }

    /*---Add new customer---*/
    @PostMapping(Constants.GET_ALL_CUSTOMERS)
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto dto,
                                          HttpServletRequest request)
            throws ResourceNotFoundException {

        customerService.create(dto);
        log.info(String.format("The customer was requested from ip address: %s",
                request.getRemoteAddr()));

        return ResponseEntity.ok().body("New customer has been saved");
    }

    /*---Update the customer by id---*/
    @PutMapping(Constants.GET_CUSTOMER_BY_ID)
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer id,
                                            @Valid @RequestBody CustomerDto customerDto,
                                            HttpServletRequest request) {

        customerService.create(customerDto);
        log.info(String.format("The customer with id: %d was updated from ip address: %s",
                id, request.getRemoteAddr()));
        return ResponseEntity.ok().body("The customer has been updated successfully with id: " + id);
    }

    /*---Delete the customer by id---*/
    @DeleteMapping(Constants.GET_CUSTOMER_BY_ID)
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id,
                                            HttpServletRequest request)
            throws ResourceNotFoundException {

        Customer customer = customerService.read(id)
                .orElseThrow(() -> new ResourceNotFoundException("The customer not found with this id: " + id));
        customerService.delete(customer);
        log.info(String.format("The customer with id: %d was deleted from ip address: %s",
                id, request.getRemoteAddr()));
        return ResponseEntity.ok().body("The customer has been deleted successfully with id: " + id);
    }
}
