package com.rohitbaranwal.customerservice.api;

import com.rohitbaranwal.customerservice.dto.request.CustomerRequestDto;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/customer")
public interface CustomerApi {

    @PostMapping(path = "/save")
    ResponseEntity<CustomerResponseDto> save(@RequestBody @Valid CustomerRequestDto customerRequestDTO);


    @GetMapping(path = "/list")
    ResponseEntity<List<CustomerResponseDto>> getCustomersList(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "city") String city,
            @RequestParam(required = false, name = "state") String state);
}
