package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class InfoAction implements Action {
    private final SessionTg sessionTg;
    private final I18n i18n;

    public InfoAction(SessionTg sessionTg, I18n i18n) {
        this.sessionTg = sessionTg;
        this.i18n = i18n;
    }

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var out = new StringBuilder();
        var lang = sessionTg.get(chatId, "lang", "en");
        out.append(i18n.get(lang, "menu.title")).append("\n");
        out.append(i18n.get(lang, "command.lang")).append("\n");
        return new SendMessage(chatId, out.toString());
    }

    @Override
    public BotApiMethod callback(Update update) {
        return handle(update);
    }
}
