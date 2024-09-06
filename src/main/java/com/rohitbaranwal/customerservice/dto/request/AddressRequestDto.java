package com.rohitbaranwal.customerservice.dto.request;

import com.rohitbaranwal.customerservice.entity.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressRequestDto {

    @NotNull(message = "address type is required")
    AddressType type;

    @NotNull(message = "address1 is required")
    String address1;

    String address2;

    @NotNull(message = "city is required")
    String city;

    @NotNull(message = "state is required")
    String state;

    @NotNull(message = "zipcode is required")
    String zipcode;
}
