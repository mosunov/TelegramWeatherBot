package server.service.location;

import org.telegram.telegrambots.meta.api.objects.Location;
import server.weather.CurrentWeather;

public interface LocationService {

     CurrentWeather getWeatherThisLocation(Location location);
}
