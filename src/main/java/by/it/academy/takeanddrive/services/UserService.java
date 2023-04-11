package by.it.academy.takeanddrive.services;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers(Pageable pageable);

    UserResponse getUserByLogin(String login);

    UserResponse createUser(UserRequest userRequest);

    void deleteUser(String login);

    boolean isLoginExist(String login);
}
