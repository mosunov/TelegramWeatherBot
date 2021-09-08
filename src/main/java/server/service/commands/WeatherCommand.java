package server.service.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import server.service.keyboard.KeyboardServiceImpl;
import server.service.ServiceCommand;

public class WeatherCommand extends ServiceCommand {
    private static final KeyboardServiceImpl keyBoard = new KeyboardServiceImpl();

    public WeatherCommand(String identifier, String description){
        super(identifier,description);
    }

    @Override
    public String getCommandIdentifier() {
        return getCommand();
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {
        Chat chat = message.getChat();
        if(keyBoard.getIsSingle()){
            keyBoard.setCustomKeyboard();
        }
        sendAnswer(absSender, chat.getId(),"Получаю координаты",keyBoard.getReplyKeyboardMarkup());
    }
}
