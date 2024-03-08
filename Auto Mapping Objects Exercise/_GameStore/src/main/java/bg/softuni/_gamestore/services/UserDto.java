package bg.softuni._gamestore.services;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    @Pattern(regexp = "\\w+@\\w+\\.\\w+",message = "This email is not in the correct format.")
    private String email;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*",message = "Password is not in the correct format.")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserDto() {
    }

    public UserDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
