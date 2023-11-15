package ru.job4j.tg.actions;

import org.checkerframework.checker.nullness.Opt;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.SessionTg;

import java.util.Optional;

public class EmailAskAction implements Action {
    private final SessionTg sessionTg;

    public EmailAskAction(SessionTg sessionTg) {
        this.sessionTg = sessionTg;
    }

    @Override
    public Optional<BotApiMethod> handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Введите email для регистрации нового пользователя:";
        return Optional.of(new SendMessage(chatId, text));
    }
}
