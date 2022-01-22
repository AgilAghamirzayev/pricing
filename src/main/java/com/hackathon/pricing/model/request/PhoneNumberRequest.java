package com.hackathon.pricing.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberRequest {

    @Pattern(regexp = "^\\d{9}$", message = "{invalid.pattern.phoneNumber}")
    @ApiModelProperty(example = "708090414")
    private String number;
    private BigDecimal price;
    private Boolean isSold;
    private Boolean isBroned;
}
