package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.RentalAgreementRequest;
import by.it.academy.takeanddrive.dto.RentalAgreementResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.RentalAgreement;
import by.it.academy.takeanddrive.entities.User;
import by.it.academy.takeanddrive.exceptions.CarIsBusyException;
import by.it.academy.takeanddrive.mapper.RentalAgreementMapper;
import by.it.academy.takeanddrive.repositories.CarRepository;
import by.it.academy.takeanddrive.repositories.RentalAgreementRepository;
import by.it.academy.takeanddrive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RentCarService implements RentService {
    private final RentalAgreementMapper rentalAgreementMapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalAgreementRepository rentalAgreementRepository;

    @Override
    public RentalAgreementResponse makeRentalAgreement(RentalAgreementRequest rentalAgreementRequest) {
        RentalAgreement rentalAgreement = rentalAgreementMapper.buildRentalAgreement(rentalAgreementRequest);
        RentalAgreement newRentalAgreement = rentalAgreementRepository.save(rentalAgreement);
        Integer carId = rentalAgreementRequest.getCar().getId();
        String userLogin = rentalAgreementRequest.getUser().getLogin();
        rentCarByUser(carId, userLogin);
        return rentalAgreementMapper.buildRentalAgreementResponse(newRentalAgreement);
    }

    @Override
    public void releaseCar(Integer carId) {
        Car carToRelease = carRepository.findCarById(carId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s doesn't exist", carId)));
        carToRelease.setUser(null);
        carToRelease.setCarBusy(false);
    }

    @Override
    public void rentCarByUser(Integer carId, String userLogin) {
        Car car = carRepository.findCarById(carId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s not found.", carId)));
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with login %s not found.", userLogin)));
        if (car.isCarBusy()) {
            throw new CarIsBusyException("This car is busy, choose another one.");
        } else {
            car.setUser(user);
            car.setCarBusy(true);
            carRepository.save(car);
        }
    }
}
