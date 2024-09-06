package com.rohitbaranwal.customerservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponseDto {

    UUID customerId;

    String firstName;

    String lastName;

    Integer age;

    Double spendingLimit;

    String mobileNumber;

    List<AddressResponseDto> addressesList;
}
