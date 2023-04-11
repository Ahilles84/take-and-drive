package by.it.academy.takeanddrive.dto;

import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.User;
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
    private User user;
    @NotNull
    private Car car;
    @NotNull
    @FutureOrPresent
    private LocalDate rentalStart;
    @NotNull
    @Future
    private LocalDate rentalEnd;
}
