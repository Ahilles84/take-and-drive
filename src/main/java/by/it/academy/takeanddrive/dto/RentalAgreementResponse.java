package by.it.academy.takeanddrive.dto;

import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class RentalAgreementResponse {
    private UUID agreementId;
    private User user;
    private Car car;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    private BigDecimal rentalCost;
}
