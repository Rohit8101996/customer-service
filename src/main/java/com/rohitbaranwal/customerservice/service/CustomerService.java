package com.rohitbaranwal.customerservice.service;

import com.rohitbaranwal.customerservice.dto.request.CustomerRequestDto;
import com.rohitbaranwal.customerservice.dto.request.CustomerSearchRequest;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;

import java.util.List;


public interface CustomerService {

     CustomerResponseDto save(CustomerRequestDto customerRequestDTO);

     List<CustomerResponseDto> getCustomersList(CustomerSearchRequest customerSearchRequest);
}
