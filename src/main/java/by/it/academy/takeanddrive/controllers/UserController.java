package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("{login}")
    public UserResponse getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping
    public UserResponse createUser(@Validated @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @DeleteMapping("{login}")
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }
}
