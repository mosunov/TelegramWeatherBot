package server.appconfig;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.bot.Bot;
import server.bot.NonCommand;
import server.service.location.LocationServiceImpl;
import server.service.commands.StartCommand;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botName;
    private String botToken;

    @Bean
    public Bot botBean(){
        return new Bot(botName,botToken);
    }
    @Bean
    public NonCommand nonCommandBean(){
        return new NonCommand();
    }
    @Bean
    public LocationServiceImpl locationServiceImplBean(){
        return new LocationServiceImpl();
    }
    @Bean
    public StartCommand startCommandBean(){
        return new StartCommand("start","Старт");
    }
}
