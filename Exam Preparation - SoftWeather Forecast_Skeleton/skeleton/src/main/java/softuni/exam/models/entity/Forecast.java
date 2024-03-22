package softuni.exam.models.entity;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity{
    @Column(name = "day_of_week",nullable = false)
    @Enumerated(EnumType.STRING)
    private dayOfWeek dayOfWeek;
    @Column(name = "max_temperature",nullable = false)
    private double maxTemperature;
    @Column(name = "min_temperature",nullable = false)
    private double minTemperature;
    @Column(nullable = false)
    private LocalTime sunrise;
    @Column(nullable = false)
    private LocalTime sunset;
    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public softuni.exam.models.entity.dayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(softuni.exam.models.entity.dayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
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
}
