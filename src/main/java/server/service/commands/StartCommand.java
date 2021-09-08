package server.service.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import server.service.ServiceCommand;

public class StartCommand extends ServiceCommand {

    public StartCommand(String identifier, String description){
        super(identifier,description);
    }

    @Override
    public String getCommandIdentifier() {
        return getCommand();
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {
        User user = message.getFrom();
        Chat chat = message.getChat();
        String userName = (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
        sendAnswer(absSender, chat.getId(),"Давайте начнём! Если Вам нужна помощь, нажмите /help");
    }
}
