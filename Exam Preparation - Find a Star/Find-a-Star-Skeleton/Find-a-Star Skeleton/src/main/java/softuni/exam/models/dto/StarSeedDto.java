package softuni.exam.models.dto;


import java.lang.String;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class StarSeedDto {
    @Size(min = 6)
    private String description;
    @Min(0)
    private double lightYears;
    @Size(min = 2,max = 30)
    private String name;
    private String starType;
    private long constellation;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public long getConstellation() {
        return constellation;
    }

    public void setConstellation(long constellation) {
        this.constellation = constellation;
    }
}
