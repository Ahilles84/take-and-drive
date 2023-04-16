package by.it.academy.takeanddrive.mapper;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.RentalAgreement;
import by.it.academy.takeanddrive.entities.User;
import by.it.academy.takeanddrive.repositories.CarRepository;
import by.it.academy.takeanddrive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class RentalAgreementMapper {
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final UserMapper userMapper;
    private final CarMapper carMapper;

    public RentalAgreementResponse buildRentalAgreementResponse(RentalAgreement rentalAgreement) {
        return RentalAgreementResponse.builder()
                .agreementId(rentalAgreement.getAgreementId())
                .user(userMapper.buildUserResponse(rentalAgreement.getUser()))
                .car(carMapper.buildCarResponse(rentalAgreement.getCar()))
                .rentalStart(rentalAgreement.getRentalStart())
                .rentalEnd(rentalAgreement.getRentalEnd())
                .rentalCost(rentalAgreement.getRentalCost())
                .build();
    }

    public RentalAgreement buildRentalAgreement(RentalAgreementRequest rentalAgreementRequest) {
        User client = userRepository.findByLogin(rentalAgreementRequest.getUserLogin())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Car carToRent = carRepository.findCarById(rentalAgreementRequest.getCarId())
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        return RentalAgreement.builder()
                .user(client)
                .car(carToRent)
                .rentalStart(rentalAgreementRequest.getRentalStart())
                .rentalEnd(rentalAgreementRequest.getRentalEnd())
                .rentalCost(countRentalCost(rentalAgreementRequest.getRentalStart(),
                        rentalAgreementRequest.getRentalEnd(),
                        carToRent.getRentalPrice()))
                .build();
    }

    private BigDecimal countRentalCost(LocalDate startOfRent, LocalDate endOfRent, BigDecimal rentalPrice) {
        Period termOfRent = Period.between(startOfRent, endOfRent);
        return rentalPrice.multiply(BigDecimal.valueOf(termOfRent.getDays()));
    }
}
