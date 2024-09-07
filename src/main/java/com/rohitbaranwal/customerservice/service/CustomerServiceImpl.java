package com.rohitbaranwal.customerservice.service;

import com.rohitbaranwal.customerservice.dao.CustomerCustomDao;
import com.rohitbaranwal.customerservice.dao.CustomerDao;
import com.rohitbaranwal.customerservice.dto.request.CustomerRequestDto;
import com.rohitbaranwal.customerservice.dto.request.CustomerSearchRequest;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;
import com.rohitbaranwal.customerservice.entity.Customer;
import com.rohitbaranwal.customerservice.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerCustomDao customerCustomDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    @Transactional
    public CustomerResponseDto save(CustomerRequestDto customerRequestDTO) {
        Customer customer = modelMapper.map(customerRequestDTO, Customer.class);
        customer.getAddressesList().stream().forEach(addresses -> addresses.setCustomer(customer));
        Customer savedCustomer = customerDao.save(customer);
        kafkaProducer.sendMessage(savedCustomer);
        return modelMapper.map(savedCustomer, CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> getCustomersList(CustomerSearchRequest customerSearchRequest) {
        return customerCustomDao.getCustomersList(customerSearchRequest);
    }


}
