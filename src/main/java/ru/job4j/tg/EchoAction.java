package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EchoAction implements Action {
    private final String action;

    public EchoAction(String action) {
        this.action = action;
    }

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Введите любой текст";
        return new SendMessage(chatId, text);
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Action: " + action + ", data: " + msg.getText();
        return new SendMessage(chatId, text);
    }


}
