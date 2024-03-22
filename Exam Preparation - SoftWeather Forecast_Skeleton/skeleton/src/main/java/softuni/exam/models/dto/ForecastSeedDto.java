package softuni.exam.models.dto;

import org.springframework.lang.Nullable;
import softuni.exam.util.LocalTimeAdaptor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedDto {
    @XmlElement(name = "day_of_week")
    @NotNull
    private String dayOfWeek;
    @XmlElement(name = "max_temperature")
    @Min(-20)
    @Max(60)
    private int maxTemperature;
    @XmlElement(name = "min_temperature")
    @Min(-50)
    @Max(40)
    private int minTemperature;
    @XmlJavaTypeAdapter(LocalTimeAdaptor.class)
    private LocalTime sunrise;
    @XmlJavaTypeAdapter(LocalTimeAdaptor.class)
    private LocalTime sunset;
    @XmlElement
    private long city;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }
}
