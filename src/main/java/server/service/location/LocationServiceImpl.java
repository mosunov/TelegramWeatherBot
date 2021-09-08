package server.service.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.telegram.telegrambots.meta.api.objects.Location;
import server.weather.CurrentWeather;

import java.io.IOException;

@Slf4j
public class LocationServiceImpl implements LocationService {
    private final HttpClient httpClient = HttpClients.createDefault();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public CurrentWeather getWeatherThisLocation(Location location) {
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        String key = System.getenv("API_KEY");
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + key;
        final HttpGet httpget = new HttpGet(url);
        ResponseHandler<CurrentWeather> handler = new ResponseHandler<CurrentWeather>() {
            @Override
            public CurrentWeather handleResponse(HttpResponse response) throws IOException {
                final int status = response.getStatusLine().getStatusCode();
                if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        return null;
                    }
                    try {
                        return mapper.readValue(entity.getContent(), CurrentWeather.class);
                    } catch (final ParseException ex) {
                        throw new ClientProtocolException(ex);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };

        try {
            return httpClient.execute(httpget, handler);
        } catch (IOException ex) {
            log.error("Cannot send request to OpenWeather", ex);
        }
        return null;
    }
}
