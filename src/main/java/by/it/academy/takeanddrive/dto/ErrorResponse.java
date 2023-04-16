package by.it.academy.takeanddrive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private final String helpResource = "For more information use help.";
}
