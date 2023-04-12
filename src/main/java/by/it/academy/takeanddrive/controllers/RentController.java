package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import by.it.academy.takeanddrive.services.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @PostMapping
    public RentalAgreementResponse makeRentalAgreement(@Validated @RequestBody RentalAgreementRequest rentalAgreementRequest) {
        return rentService.makeRentalAgreement(rentalAgreementRequest);
    }

    @GetMapping
    public List<RentalAgreementResponse> readAllRentalAgreements(Pageable pageable) {
        return rentService.getAllAgreements(pageable);
    }
}
