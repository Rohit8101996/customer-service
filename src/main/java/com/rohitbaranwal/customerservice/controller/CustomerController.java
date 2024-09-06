package com.rohitbaranwal.customerservice.controller;

import com.rohitbaranwal.customerservice.api.CustomerApi;
import com.rohitbaranwal.customerservice.dto.request.CustomerRequestDto;
import com.rohitbaranwal.customerservice.dto.request.CustomerSearchRequest;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;
import com.rohitbaranwal.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class CustomerController implements CustomerApi {

    @Autowired
    CustomerService customerService;


    public ResponseEntity<CustomerResponseDto> save(CustomerRequestDto customerRequestDTO) {
        CustomerResponseDto responseDto = customerService.save(customerRequestDTO);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<List<CustomerResponseDto>> getCustomersList(String name,
                                                                      String city,
                                                                      String state) {
        CustomerSearchRequest customerSearchRequest =
                CustomerSearchRequest.builder()
                        .name(name)
                        .city(city)
                        .state(state)
                        .build();
        return ResponseEntity.ok(customerService.getCustomersList(customerSearchRequest));
    }

}
