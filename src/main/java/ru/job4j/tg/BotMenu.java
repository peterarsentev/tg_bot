package ru.job4j.tg;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.job4j.tg.actions.RegCreateAction;
import ru.job4j.tg.domain.Resp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BotMenu extends TelegramLongPollingBot {
    private final Map<String, ListIterator<Action>> bindingBy = new ConcurrentHashMap<>();
    private final Map<String, List<Action>> actions;
    private final String username;
    private final String token;

    public BotMenu(Map<String, List<Action>> actions, String username, String token) {
        this.actions = actions;
        this.username = username;
        this.token = token;
    }

    public String getBotUsername() {
        return username;
    }

    public String getBotToken() {
        return token;
    }

    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) {
            return;
        }
        var key = update.getMessage().getText();
        var chatId = update.getMessage().getChatId().toString();
        if (actions.containsKey(key)) {
            bindingBy.put(chatId, actions.get(key).listIterator());
        }
        if (!bindingBy.containsKey(chatId)) {
            return;
        }
        var bindingActions = bindingBy.get(chatId);
        if (bindingActions == null || !bindingActions.hasNext()) {
            bindingBy.remove(chatId);
            return;
        }
        Resp resp;
        do {
            resp = bindingActions.next().handle(update);
            if (resp.repeat()) {
                bindingActions.previous();
            }
        } while (!resp.hasMessage());
        send(resp.message());
    }

    private void send(BotApiMethod msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
