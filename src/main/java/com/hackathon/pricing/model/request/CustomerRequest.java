package com.hackathon.pricing.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotNull
    @Size(min = 3, max=255, message = "name {javax.validation.constraints.Size.message}")
    @ApiModelProperty(example = "Ayshan")
    private String name;

    @NotNull
    @Size(min = 3, max=255, message = "surname {javax.validation.constraints.Size.message}")
    @ApiModelProperty(example = "Rzayeva")
    private String surname;

    @NotNull
    @Pattern(regexp = "^\\w{7}$", message = "{invalid.pattern.fin}")
    @ApiModelProperty(example = "62J4X1Z")
    private String pin;

    @Email
    @ApiModelProperty(example = "rza.ayshan@gmail.com")
    private String email;

    @Min(value = 0, message = "phoneNumberId {javax.validation.constraints.DecimalMin.message}")
    @ApiModelProperty(example = "125")
    private Long phoneNumberId;
}
