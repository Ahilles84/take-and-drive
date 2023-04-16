package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.mapper.CarMapper;
import by.it.academy.takeanddrive.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;

    @Override
    public CarResponse getCar(Integer id) {
        return carRepository.findById(id)
                .map(carMapper::buildCarResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s doesn't exist", id)));
    }

    @Override
    public List<CarResponse> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable).stream()
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
    public void deleteCar(Integer carId) {
        Car carToDelete = carRepository.findCarById(carId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car with id %s doesn't exist", carId)));
        carToDelete.setUser(null);
        carRepository.delete(carToDelete);
    }
}
