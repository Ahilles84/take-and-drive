package by.it.academy.takeanddrive.dto;

import by.it.academy.takeanddrive.entities.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String login;
    private String password;
    private Role role;
}
