package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.User;
import by.it.academy.takeanddrive.exceptions.CarNotFoundException;
import by.it.academy.takeanddrive.mapper.CarMapper;
import by.it.academy.takeanddrive.mapper.UserMapper;
import by.it.academy.takeanddrive.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final UserMapper userMapper;

    @Override
    public CarResponse getCar(Integer id) {
        return carRepository.findById(id)
                .map(carMapper::buildCarResponse)
                .orElseThrow(() -> new CarNotFoundException(String.format("Car with id %s doesn't exist", id)));
    }

    @Override
    public List<CarResponse> getAllCars(Pageable pageable) {
        return carRepository.findAll().stream()
                .map(carMapper::buildCarResponse)
                .toList();
    }

    @Override
    public CarResponse createCar(CarRequest carRequest) {
        Car car = carMapper.buildCar(carRequest);
        Car savedCar = carRepository.save(car);
        return carMapper.buildCarResponse(savedCar);
    }

    @Override
    public void deleteCar(Integer id) {
        CarResponse carResponse = getCar(id);
        releaseCar(carResponse);
        Car deletedCar = carMapper.buildCar(carResponse);
        carRepository.delete(deletedCar);
    }

    @Override
    public void releaseCar(CarResponse carResponse) {
        Car releasedCar = carMapper.buildCar(carResponse);
        releasedCar.setStatus(false);
        releasedCar.setUser(null);
    }

    @Override
    public void rentCarByUser(CarResponse carResponse, UserResponse userResponse) {
        Car rentedCar = carMapper.buildCar(carResponse);
        User user = userMapper.buildUser(userResponse);
        rentedCar.setUser(user);
        rentedCar.setStatus(true);
    }
}
