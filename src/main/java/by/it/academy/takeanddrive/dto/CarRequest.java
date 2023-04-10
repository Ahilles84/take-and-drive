package by.it.academy.takeanddrive.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CarRequest {
    @NotBlank
    private String model;
    @NotBlank
    private String registrationNumber;
    @NotNull
    private BigDecimal rentalPrice;
}
