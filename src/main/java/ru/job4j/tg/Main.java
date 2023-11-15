package ru.job4j.tg;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.job4j.tg.actions.*;

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
        var sessionTg = new SessionTg();
        var i18n = new I18n();
        i18n.loadDic("ru");
        i18n.loadDic("en");
        var actions = Map.of(
                "/start", List.of(new InfoAction(sessionTg, i18n), new ShowBindInputAction("info")),
                "/echo", List.<Action>of(new EchoAction("echo")),
                "/reg", List.of(new NameAskAction(sessionTg), new NameStoreAction(sessionTg),
                        new EmailAskAction(sessionTg), new EmailStoreAction(sessionTg),
                        new RegCreateAction(sessionTg))
        );
        tg.registerBot(new BotMenu(actions, config.getProperty("tg.username"), config.getProperty("tg.token")));
    }
}
