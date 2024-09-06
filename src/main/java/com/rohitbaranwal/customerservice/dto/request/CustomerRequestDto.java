package com.rohitbaranwal.customerservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerRequestDto {

    @NotNull(message = "first name is required")
    String firstName;

    String lastName;

    Integer age;

    Double spendingLimit;

    String mobileNumber;

    @Valid
    List<AddressRequestDto> addressesList;
}
