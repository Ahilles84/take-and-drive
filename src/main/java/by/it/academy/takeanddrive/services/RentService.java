package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;

public interface RentService {
    void releaseCar(Integer carId);

    void rentCarByUser(Integer carId, String userLogin);

    RentalAgreementResponse makeRentalAgreement(RentalAgreementRequest rentalAgreementRequest);
}
