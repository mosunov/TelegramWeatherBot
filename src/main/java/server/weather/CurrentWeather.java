package server.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    @JsonProperty("coord")
    private Coordinates coord;
    @JsonProperty("main")
    private MainWeatherCondition main;
    @JsonProperty("wind")
    private WindPOJO wind;

}
