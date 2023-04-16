package by.it.academy.takeanddrive.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class UserRequest {
    @Size
    @NotBlank
    private String firstName;
    @Size
    @NotBlank
    private String lastName;
    @Past
    @NotNull
    private LocalDate birthDate;
    @Size
    @NotBlank
    private String login;
    @Size
    @NotBlank
    private String password;
}
