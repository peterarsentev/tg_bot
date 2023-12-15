package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.I18n;
import ru.job4j.tg.SessionTg;

import java.util.Optional;
import ru.job4j.tg.domain.Resp;
public class InfoAction implements Action {
    private final SessionTg sessionTg;
    private final I18n i18n;

    public InfoAction(SessionTg sessionTg, I18n i18n) {
        this.sessionTg = sessionTg;
        this.i18n = i18n;
    }

    @Override
    public Resp handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var out = new StringBuilder();
        var lang = sessionTg.get(chatId, "lang", "en");
        out.append(i18n.get(lang, "menu.title")).append("\n");
        out.append(i18n.get(lang, "command.lang")).append("\n");
        return new Resp(false, new SendMessage(chatId, out.toString()));
    }
}
