package by.it.academy.takeanddrive.dto;

import by.it.academy.takeanddrive.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String login;
    private String password;
    private Role role;
}
