package by.it.academy.takeanddrive.controllers;

import by.it.academy.takeanddrive.dto.UserRequest;
import by.it.academy.takeanddrive.dto.UserResponse;
import by.it.academy.takeanddrive.exceptions.LoginNotUniqueException;
import by.it.academy.takeanddrive.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(Pageable pageable){
        return userService.getAllUsers(pageable);
    }
    @GetMapping("{login}")
    public UserResponse getUserByLogin(@PathVariable String login){
        return userService.getUserByLogin(login);
    }
    @PostMapping("{login}/{password}")
    public void loginUser(@PathVariable String login, @PathVariable String password, HttpServletRequest request){
        UserResponse user = userService.getUserByLoginAndPassword(login, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());
        session.setAttribute("login", login);
    }
    @PostMapping
    public UserResponse createUser(@Validated @RequestBody UserRequest userRequest){
        if (userService.isLoginExist(userRequest.getLogin())){
            throw new LoginNotUniqueException("User with such login already exists!");
        } else {
            return userService.createUser(userRequest);
        }
    }
    @PostMapping("delete/{login}")
    public void deleteUser(@PathVariable String login){
        userService.deleteUser(login);
    }
}
