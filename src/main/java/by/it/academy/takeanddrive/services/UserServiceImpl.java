package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.entities.Car;
import by.it.academy.takeanddrive.entities.User;
import by.it.academy.takeanddrive.exceptions.UserNotFoundException;
import by.it.academy.takeanddrive.mapper.UserMapper;
import by.it.academy.takeanddrive.repositories.CarRepository;
import by.it.academy.takeanddrive.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public List<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll().stream()
                .map(userMapper::buildUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with login '%s' doesn't exist", login)));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.buildUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.buildUserResponse(savedUser);
    }

    @Override
    public void deleteUser(String login) {
        UserResponse userResponse = getUserByLogin(login);
        User deletedUser = userMapper.buildUser(userResponse);
        releaseRentedCar(deletedUser);
        userRepository.delete(deletedUser);
    }

    @Override
    public boolean isLoginExist(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    public UserResponse getUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password)
                .map(userMapper::buildUserResponse)
                .orElseThrow(()-> new UserNotFoundException("Wrong login or password entered!"));
    }
    private void releaseRentedCar(User user){
        List<Car> cars = carRepository.findAll();
        Optional<Car> optionalCar = cars.stream()
                .filter(car -> car.getUser() != null && Objects.equals(car.getUser().getId(), user.getId()))
                .findFirst();
        if(optionalCar.isPresent()){
            Car car = optionalCar.get();
            car.setUser(null);
            car.setStatus(false);
        }
    }
}
