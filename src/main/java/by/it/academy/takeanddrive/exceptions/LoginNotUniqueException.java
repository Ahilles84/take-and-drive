package by.it.academy.takeanddrive.exceptions;

public class LoginNotUniqueException extends RuntimeException{
    public LoginNotUniqueException(String message) {
        super(message);
    }
}
