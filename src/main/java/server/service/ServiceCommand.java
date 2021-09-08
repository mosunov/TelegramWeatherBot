package server.service;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class ServiceCommand extends BotCommand implements IBotCommand {

    public ServiceCommand(String identifier, String description) {
        super(identifier, description);
    }

    public void sendAnswer(AbsSender absSender, Long chatId,
                     String text){
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try{
            absSender.execute(message);
        } catch (TelegramApiException ex){
            ex.printStackTrace();
        }
    }

    public void sendAnswer(AbsSender absSender, Long chatId,
                           String text, ReplyKeyboardMarkup replyKeyboardMarkup){
        SendMessage message = new SendMessage();
        message.setReplyMarkup(replyKeyboardMarkup);
        message.setChatId(chatId.toString());
        message.setText(text);
        try{
            absSender.execute(message);
        } catch (TelegramApiException ex){
            ex.printStackTrace();
        }
    }
}
