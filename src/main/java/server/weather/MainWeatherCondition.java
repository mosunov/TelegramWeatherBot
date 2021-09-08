package server.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherCondition {
    private double temp;
    private double feels_like;

}
