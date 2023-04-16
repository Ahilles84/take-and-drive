package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentService {
    void releaseCar(Integer carId);

    void rentCarByUser(Integer carId, String userLogin);

    RentalAgreementResponse makeRentalAgreement(RentalAgreementRequest rentalAgreementRequest);

    List<RentalAgreementResponse> getAllAgreements(Pageable pageable);
}
