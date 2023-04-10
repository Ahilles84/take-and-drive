package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.dto.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarResponse getCar(Integer id);
    List<CarResponse> getAllCars(Pageable pageable);
    CarResponse createCar(CarRequest carRequest);
    void deleteCar(Integer id);
    void releaseCar(CarResponse carResponse);
    void rentCarByUser(CarResponse carResponse, UserResponse userResponse);
}
