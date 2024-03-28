package softuni.exam.models.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MechanicSeedDto {
    @Pattern(regexp = ".*@.*\\..*")
    private String email;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Size(min = 2)
    @Nullable
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
