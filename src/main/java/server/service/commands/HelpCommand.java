package server.service.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import server.service.ServiceCommand;

public class HelpCommand extends ServiceCommand {

    public HelpCommand(String identifier, String description){
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
        sendAnswer(absSender, chat.getId(), "Вот что я могу:.\n" +
                "Запоминаю твое местоположение и в установленное время отправляю прогноз погоды, чтобы " +
                "непогода не смогла застать тебя в расплох\n" +
                "Вот список моих команд:\n" +
                "/help - выводит на экран список команд\n" +
                "/weather - выдаст тебе текущую погоду твоего местоположения\n" +
                "/settings - запустит настройку основных условий: Время вывода текущей погоды," +
                "Ручное задание местоположения");
    }
}
