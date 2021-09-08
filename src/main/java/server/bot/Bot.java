package server.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import server.service.commands.HelpCommand;
import server.service.commands.StartCommand;
import server.service.commands.WeatherCommand;

@Slf4j
public class Bot extends TelegramLongPollingCommandBot {
    
    private final String NAME_BOT;
    private final String BOT_TOKEN;
    @Autowired
    private NonCommand nonCommandResolver;

    public Bot(String botName, String botToken) {
        super();
        log.info("Конструктор супер класса отработал");
        NAME_BOT = botName;
        BOT_TOKEN = botToken;
        log.info("Проинициализированны поля токена и имени");
        register(new HelpCommand("help","Помощь"));
        register(new WeatherCommand("weather","Погода"));
        register(new StartCommand("start","Старт"));
    }

    @Override
    public String getBotUsername() {
        return NAME_BOT;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String userName = getUserName(message);
        String answer = nonCommandResolver.nonCommandProcessing(userName,message);
        setAnswer(chatId,answer);
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    private void setAnswer(Long chatId, String textAnswer) {
        SendMessage answerMessage = new SendMessage();
        answerMessage.setText(textAnswer);
        answerMessage.setChatId(chatId.toString());

        try {
            execute(answerMessage);
        } catch (TelegramApiException e) {
            log.info("Exception from setAnswer method when try execute");
        }
    }

    private String getUserName(Message message) {
        User user = message.getFrom();
        String userName = user.getUserName();
        return (userName!=null)? userName: String.format("%s %s",user.getFirstName(),user.getLastName());
    }
}
