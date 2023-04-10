package by.it.academy.takeanddrive.repositories;

import by.it.academy.takeanddrive.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
