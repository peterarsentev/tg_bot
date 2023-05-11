package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class InfoAction implements Action {
    private final List<String> actions;

    public InfoAction(List<String> actions) {
        this.actions = actions;
    }

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var out = new StringBuilder();
        out.append("Выберите действие:").append("\n");
        for (String action : actions) {
            out.append(action).append("\n");
        }
        return new SendMessage(chatId, out.toString());
    }

    @Override
    public BotApiMethod callback(Update update) {
        return handle(update);
    }
}
