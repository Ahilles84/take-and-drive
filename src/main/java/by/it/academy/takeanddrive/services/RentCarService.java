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
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class RentCarService implements RentService {
    private final RentalAgreementMapper rentalAgreementMapper;
    private final RentalAgreementRepository rentalAgreementRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public RentalAgreementResponse makeRentalAgreement(RentalAgreementRequest rentalAgreementRequest) {
        RentalAgreement rentalAgreement = rentalAgreementMapper.buildRentalAgreement(rentalAgreementRequest);
        RentalAgreement newRentalAgreement = rentalAgreementRepository.save(rentalAgreement);
        rentCarByUser(rentalAgreementRequest.getCarId(), rentalAgreementRequest.getUserLogin());
        return rentalAgreementMapper.buildRentalAgreementResponse(newRentalAgreement);
    }

    @Override
    public List<RentalAgreementResponse> getAllAgreements(Pageable pageable) {
        return rentalAgreementRepository.findAll(pageable).stream()
                .map(rentalAgreementMapper::buildRentalAgreementResponse)
                .toList();
    }

    @Override
    public void releaseCar(Integer carId) {
        Car carToRelease = carRepository.findCarById(carId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s doesn't exist", carId)));
        carToRelease.setUser(null);
        carToRelease.setBusy(false);
        carRepository.save(carToRelease);
    }

    @Override
    public void rentCarByUser(Integer carId, String userLogin) {
        Car car = carRepository.findCarById(carId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s not found.", carId)));
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with login %s not found.", userLogin)));
        if (car.isBusy()) {
            throw new CarIsBusyException("This car is busy, choose another one.");
        } else {
            car.setUser(user);
            car.setBusy(true);
            carRepository.save(car);
        }
    }

    @Scheduled(cron = "@daily")
    public void checkCarsToRelease() {
        List<RentalAgreementResponse> agreements = getAllAgreements(Pageable.unpaged());
        agreements.stream()
                .filter(rentalAgreementResponse -> rentalAgreementResponse.getRentalEnd().isBefore(LocalDate.now()))
                .forEach(rentalAgreementResponse -> releaseCar(rentalAgreementResponse.getCar().getId()));
    }
}
