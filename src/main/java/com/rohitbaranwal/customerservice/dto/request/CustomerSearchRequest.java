package com.rohitbaranwal.customerservice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerSearchRequest {

    private String name;
    private String city;
    private String state;

}
