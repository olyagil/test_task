package com.netcracker.controller;

import com.netcracker.dto.CustomerTypeDto;
import com.netcracker.entity.Customer;
import com.netcracker.entity.CustomerType;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.service.CustomerService;
import com.netcracker.service.CustomerTypeService;
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
public class CustomerTypeController {

    private final CustomerTypeService service;

    public CustomerTypeController(CustomerTypeService service) {
        this.service = service;
    }

    /*---get all customer type---*/
    @GetMapping("/customer-types")
    public ResponseEntity<List<CustomerType>> allCustomerTypes(HttpServletRequest request) {
        List<CustomerType> customers = service.read();
        log.info(String.format("The customer's types were requested from this id address: %s", request.getRemoteAddr()));
        return ResponseEntity.ok().body(customers);
    }

    /*---Get a customer type by id---*/
    @GetMapping("/customer-types/{id}")
    public ResponseEntity<CustomerTypeDto> customerType(@PathVariable("id") Integer id,
                                                        HttpServletRequest request)
            throws ResourceNotFoundException {

        CustomerTypeDto customer = service.read(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer type not found with id: " + id));
        log.info(String.format("The customer's type was requested from this id address: %s", request.getRemoteAddr()));
        return ResponseEntity.ok().body(customer);
    }


}

