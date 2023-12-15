package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.SessionTg;
import ru.job4j.tg.domain.Resp;

public class NameStoreAction implements Action {
    private final SessionTg sessionTg;

    public NameStoreAction(SessionTg sessionTg) {
        this.sessionTg = sessionTg;
    }

    @Override
    public Resp handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var name = msg.getText();
        if ("Petr".equals(name)) {
            return new Resp(true, new SendMessage(chatId, "Имя уже занято. Введите другое."));
        }
        sessionTg.put(chatId, "name", name);
        return new Resp(false, null);
    }
}
