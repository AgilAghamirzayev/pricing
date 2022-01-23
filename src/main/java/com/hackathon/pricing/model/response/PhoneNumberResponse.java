package com.hackathon.pricing.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberResponse {

    private Long id;
    private String number;
    private BigDecimal price;
    private String address;
    private Long branchId;
}
