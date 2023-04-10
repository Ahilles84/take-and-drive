package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.CarRequest;
import by.it.academy.takeanddrive.dto.CarResponse;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.exceptions.CarIsBusyException;
import by.it.academy.takeanddrive.exceptions.UserNotAuthorizedException;
import by.it.academy.takeanddrive.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @PostMapping("delete/{id}")
    public void deleteCar(@PathVariable Integer id){
        carService.deleteCar(id);
    }
    @PostMapping("rent/{id}")
    public void rentCar(@PathVariable Integer id, HttpServletRequest request) {
        CarResponse car = carService.getCar(id);
        HttpSession session = request.getSession();
        UserResponse user = (UserResponse) session.getAttribute("user");
        if (user == null) {
            throw new UserNotAuthorizedException("Please authorise, before continue!");
        } else if (car.isStatus()) {
            throw new CarIsBusyException("Car is busy, choose another one!");
        } else {
            carService.rentCarByUser(car, user);
        }
    }
}
