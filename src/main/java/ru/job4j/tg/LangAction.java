package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LangAction implements Action {
    private final SessionTg sessionTg;
    private final I18n i18n;

    public LangAction(SessionTg sessionTg, I18n i18n) {
        this.sessionTg = sessionTg;
        this.i18n = i18n;
    }

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var lang = sessionTg.get(chatId, "lang","en");
        var text = i18n.get(lang, "lang.choose");
        return new SendMessage(chatId, text);
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        sessionTg.put(chatId, "lang", msg.getText());
        var lang = sessionTg.get(chatId, "lang", "en");
        var text = i18n.get(lang, "lang.current");
        return new SendMessage(chatId, text);
    }


}
