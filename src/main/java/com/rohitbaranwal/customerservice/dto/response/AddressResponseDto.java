package com.rohitbaranwal.customerservice.dto.response;

import com.rohitbaranwal.customerservice.entity.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressResponseDto {

    UUID addressId;

    AddressType type;

    String address1;

    String address2;

    String city;

    String state;

    String zipcode;
}
