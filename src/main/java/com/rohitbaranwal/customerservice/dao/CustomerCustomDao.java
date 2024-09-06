package com.rohitbaranwal.customerservice.dao;

import com.rohitbaranwal.customerservice.dto.request.CustomerSearchRequest;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;

import java.util.List;


public interface CustomerCustomDao {

    List<CustomerResponseDto> getCustomersList(CustomerSearchRequest customerSearchRequest);
}
