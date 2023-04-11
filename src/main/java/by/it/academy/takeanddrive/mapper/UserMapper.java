package by.it.academy.takeanddrive.mapper;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UserMapper {
    public UserResponse buildUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User buildUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(countAge(userRequest.getBirthDate()))
                .login(userRequest.getLogin())
                .password(userRequest.getPassword())
                .build();
    }

    private Integer countAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
