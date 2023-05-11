package ru.job4j.tg;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException {
        var tg = new TelegramBotsApi(DefaultBotSession.class);
        var config = new Properties();
        try (var app = Main.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(app);
        }
        var actions = Map.of(
                "/start", new InfoAction(
                        List.of(
                        "/start - Команды бота",
                        "/echo - Ввод данных для команды",
                                "/new - Регистрация пользователя")
                ),
                  "/echo", new EchoAction("/echo"),
                "/new", new RegAction()
        );
        tg.registerBot(new BotMenu(actions, config.getProperty("tg.username"), config.getProperty("tg.token")));
    }
}
