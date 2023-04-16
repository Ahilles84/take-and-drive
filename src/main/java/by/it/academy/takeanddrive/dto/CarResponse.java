package by.it.academy.takeanddrive.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CarResponse {
    private Integer id;
    private String model;
    private String registrationNumber;
    private boolean busy;
    private BigDecimal rentalPrice;
    private UserResponse user;
}
