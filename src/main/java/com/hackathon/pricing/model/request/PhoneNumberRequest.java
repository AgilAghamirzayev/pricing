package com.hackathon.pricing.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberRequest {

    @Pattern(regexp = "^\\d{9}$", message = "{invalid.pattern.phoneNumber}")
    @NotNull
    @ApiModelProperty(example = "708090414")
    private String number;

    @DecimalMin(value = "1", message = "price {javax.validation.constraints.DecimalMin.message}")
    @ApiModelProperty(example = "45")
    private BigDecimal price;

    @Min(value = 1, message = "branchId {javax.validation.constraints.Min.message}")
    @NotNull(message = "branchId {javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(example = "13")
    private Long branchId;

    private Boolean isSold;
    private Boolean isBroned;
}
