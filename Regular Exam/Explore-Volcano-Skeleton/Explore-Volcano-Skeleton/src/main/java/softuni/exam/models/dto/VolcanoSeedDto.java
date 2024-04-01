package softuni.exam.models.dto;

import org.springframework.lang.Nullable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class VolcanoSeedDto {
    @NotNull
    @Size(min = 2,max = 30)
    private String name;
    @NotNull
    @Positive
    private int elevation;
    @NotNull
    private boolean isActive;
    @Nullable
    private String lastEruption;
    @Nullable
    private String volcanoType;
    private long country;

    @Nullable
    public String getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(@Nullable String volcanoType) {
        this.volcanoType = volcanoType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
