package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.SessionTg;

import java.util.Optional;
import ru.job4j.tg.domain.Resp;
public class NameAskAction implements Action {
    private final SessionTg sessionTg;

    public NameAskAction(SessionTg sessionTg) {
        this.sessionTg = sessionTg;
    }

    @Override
    public Resp handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Введите отображаемое имя для регистрации нового пользователя:";
        return new Resp(false, new SendMessage(chatId, text));
    }
}
