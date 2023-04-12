package by.it.academy.takeanddrive.dto;

import by.it.academy.takeanddrive.entities.User;
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
    private User user;
}
