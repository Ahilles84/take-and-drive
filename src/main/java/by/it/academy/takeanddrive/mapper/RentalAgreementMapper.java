package by.it.academy.takeanddrive.mapper;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import by.it.academy.takeanddrive.entities.RentalAgreement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
public class RentalAgreementMapper {
    public RentalAgreementResponse buildRentalAgreementResponse(RentalAgreement rentalAgreement) {
        return RentalAgreementResponse.builder()
                .agreementId(rentalAgreement.getAgreementId())
                .user(rentalAgreement.getUser())
                .car(rentalAgreement.getCar())
                .rentalStart(rentalAgreement.getRentalStart())
                .rentalEnd(rentalAgreement.getRentalEnd())
                .rentalCost(rentalAgreement.getRentalCost())
                .build();
    }

    public RentalAgreement buildRentalAgreement(RentalAgreementRequest rentalAgreementRequest) {
        return RentalAgreement.builder()
                .user(rentalAgreementRequest.getUser())
                .car(rentalAgreementRequest.getCar())
                .rentalStart(rentalAgreementRequest.getRentalStart())
                .rentalEnd(rentalAgreementRequest.getRentalEnd())
                .rentalCost(countRentalCost(rentalAgreementRequest.getRentalStart(),
                        rentalAgreementRequest.getRentalEnd(),
                        rentalAgreementRequest.getCar().getRentalPrice()))
                .build();
    }

    private BigDecimal countRentalCost(LocalDate startOfRent, LocalDate endOfRent, BigDecimal rentalPrice) {
        Period termOfRent = Period.between(startOfRent, endOfRent);
        return rentalPrice.multiply(BigDecimal.valueOf(termOfRent.getDays()));
    }
}
