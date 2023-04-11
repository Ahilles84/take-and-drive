package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import by.it.academy.takeanddrive.services.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @PostMapping
    public RentalAgreementResponse makeRentalAgreement(@Validated @RequestBody RentalAgreementRequest rentalAgreementRequest) {
        return rentService.makeRentalAgreement(rentalAgreementRequest);
    }

    @PostMapping("{carId}/{userLogin}")
    public void rentCar(@PathVariable Integer carId, @PathVariable String userLogin) {
        rentService.rentCarByUser(carId, userLogin);
    }
}
