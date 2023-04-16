package by.it.academy.takeanddrive.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class RentalAgreementResponse {
    private UUID agreementId;
    private UserResponse user;
    private CarResponse car;
    private LocalDate rentalStart;
    private LocalDate rentalEnd;
    private BigDecimal rentalCost;
}
