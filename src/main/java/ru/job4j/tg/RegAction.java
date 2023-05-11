package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RegAction implements Action {
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var text = "Введите почту для регистрации нового пользователя:";
        return new SendMessage(chatId, text);
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        var email = msg.getText();
        // userRepository.save(new User(email));
        var text = "Пользователь успешно зарегистрирован:" + email;
        return new SendMessage(chatId, text);
    }
}
