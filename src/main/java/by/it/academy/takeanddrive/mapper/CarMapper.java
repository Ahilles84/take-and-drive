package by.it.academy.takeanddrive.mapper;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.entities.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarMapper {
    private final UserMapper userMapper;
    public CarResponse buildCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .registrationNumber(car.getRegistrationNumber())
                .busy(car.isBusy())
                .rentalPrice(car.getRentalPrice())
                .user(userMapper.buildUserResponse(car.getUser()))
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
