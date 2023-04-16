package by.it.academy.takeanddrive.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
public class CarRequest {
    @Size
    @NotBlank
    private String model;
    @Size
    @NotBlank
    private String registrationNumber;
    @NotNull
    private BigDecimal rentalPrice;
}
