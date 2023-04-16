package by.it.academy.takeanddrive.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class RentalAgreementRequest {
    @NotNull
    private String userLogin;
    @NotNull
    private Integer carId;
    @NotNull
    @FutureOrPresent
    private LocalDate rentalStart;
    @NotNull
    @Future
    private LocalDate rentalEnd;
}
