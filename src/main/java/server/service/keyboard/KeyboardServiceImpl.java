package server.service.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class KeyboardServiceImpl implements KeyboardService {
    private static final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private static final ArrayList<KeyboardRow> keyboardRowList = new ArrayList<>();
    private static final KeyboardButton keyboardButton = new KeyboardButton();
    private static final KeyboardRow keyboardRow = new KeyboardRow();
    private boolean isSingle = true;

    public boolean getIsSingle(){
        return isSingle;
    }
    @Override
    public void setCustomKeyboard() {
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        keyboardButton.setRequestLocation(true);
        keyboardButton.setRequestContact(false);
        keyboardButton.setText("Отправить местоположение");
        keyboardRow.add(0,keyboardButton);
        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        isSingle = false;
    }

    @Override
    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        return replyKeyboardMarkup;
    }
}
