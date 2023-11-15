package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;

import java.util.Optional;

public class ShowBindInputAction implements Action {
    private final String action;

    public ShowBindInputAction(String action) {
        this.action = action;
    }

    @Override
    public Optional<BotApiMethod> handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Введите любой текст";
        return Optional.of(new SendMessage(chatId, text));
    }
}
