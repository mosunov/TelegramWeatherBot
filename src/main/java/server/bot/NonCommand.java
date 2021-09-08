package server.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;
import server.db.UserService;
import server.db.UserServiceImpl;
import server.db.entities.User;
import server.service.location.LocationService;
import server.weather.CurrentWeather;

import java.text.DecimalFormat;
@Slf4j
public class NonCommand {
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserServiceImpl sender;
    private final DecimalFormat df = new DecimalFormat("##");

    public String nonCommandProcessing(String userName, Message message){
        log.info("Пользователь {}. Начата обработка сообщения \"{}\", не являющегося командой",
                userName,message.getText());
        if(message.hasLocation()){
            CurrentWeather location = locationService.getWeatherThisLocation(message.getLocation());
            sendUserToDB(new User(), message, userName);
            return "Температура воздуха сейчас: " + df.format(location.getMain().getTemp() - 273) +"\u2103 \uD83C\uDF21 \n" +
                    "Ощущается как: " + df.format(location.getMain().getFeels_like() - 273)+"\u2103 \n" +
                    "Скорость ветра: " + df.format(location.getWind().getSpeed())+ "km/h";
        }
            return "Не понимаю вас";
    }
    public void sendUserToDB(User user, Message message, String userName){
        user.setChat_id(message.getChatId());
        user.setName(userName);
        user.setLocation(df.format(message.getLocation().getLatitude())
                + " " + df.format(message.getLocation().getLongitude()));
        sender.create(user);
    }
}
