package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.User;
import by.it.academy.takeanddrive.exceptions.LoginNotUniqueException;
import by.it.academy.takeanddrive.mapper.UserMapper;
import by.it.academy.takeanddrive.repositories.CarRepository;
import by.it.academy.takeanddrive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public List<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(userMapper::buildUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with login '%s' doesn't exist", login)));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (isLoginExist(userRequest.getLogin())) {
            throw new LoginNotUniqueException("This login is busy, choose another one.");
        } else {
            User user = userMapper.buildUser(userRequest);
            User savedUser = userRepository.save(user);
            return userMapper.buildUserResponse(savedUser);
        }
    }

    @Override
    public void deleteUser(String login) {
        User userToDelete = userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with login %s not found.", login)));
        releaseRentedCar(userToDelete);
        userRepository.delete(userToDelete);
    }

    @Override
    public boolean isLoginExist(String login) {
        return userRepository.existsUserByLogin(login);
    }

    private void releaseRentedCar(User user) {
        List<Car> cars = carRepository.findAll();
        Optional<Car> optionalCar = cars.stream()
                .filter(car -> car.getUser() != null && Objects.equals(car.getUser().getId(), user.getId()))
                .findFirst();
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setUser(null);
            car.setBusy(false);
        }
    }
}
