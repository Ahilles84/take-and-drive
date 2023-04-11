package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("{id}")
    public CarResponse getCarById(@PathVariable Integer id) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<CarResponse> getAllCars(Pageable pageable) {
        return carService.getAllCars(pageable);
    }

    @PostMapping
    public CarResponse createCar(@Validated @RequestBody CarRequest carRequest) {
        return carService.createCar(carRequest);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
    }
}
