package ru.job4j.tg.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.Action;
import ru.job4j.tg.I18n;
import ru.job4j.tg.SessionTg;

import java.util.Optional;

public class LangAction implements Action {
    private final SessionTg sessionTg;
    private final I18n i18n;

    public LangAction(SessionTg sessionTg, I18n i18n) {
        this.sessionTg = sessionTg;
        this.i18n = i18n;
    }

    @Override
    public Optional<BotApiMethod> handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var lang = sessionTg.get(chatId, "lang","en");
        var text = i18n.get(lang, "lang.choose");
        return Optional.of(new SendMessage(chatId, text));
    }
}
