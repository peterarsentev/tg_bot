package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.SessionTg;

import java.util.Optional;

public class RegCreateAction implements Action {
    private final SessionTg sessionTg;

    public RegCreateAction(SessionTg sessionTg) {
        this.sessionTg = sessionTg;
    }

    @Override
    public Optional<BotApiMethod> handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var name = sessionTg.get(chatId, "name", "");
        var email = sessionTg.get(chatId, "email", "");
        var text = String.format("Пользователь зарегистрирован: \n name:%s, \n email:%s", name, email) ;
        return Optional.of(new SendMessage(chatId, text));
    }
}
