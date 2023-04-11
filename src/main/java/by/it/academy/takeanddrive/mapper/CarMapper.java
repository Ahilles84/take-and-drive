package by.it.academy.takeanddrive.mapper;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarResponse buildCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .registrationNumber(car.getRegistrationNumber())
                .carBusy(car.isCarBusy())
                .rentalPrice(car.getRentalPrice())
                .user(car.getUser())
                .build();
    }

    public Car buildCar(CarRequest carRequest) {
        return Car.builder()
                .model(carRequest.getModel())
                .registrationNumber(carRequest.getRegistrationNumber())
                .rentalPrice(carRequest.getRentalPrice())
                .build();
    }
}
