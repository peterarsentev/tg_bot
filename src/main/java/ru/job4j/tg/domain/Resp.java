package ru.job4j.tg.domain;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class Resp {
    private final boolean repeat;
    private final BotApiMethod message;

    public Resp(boolean repeat, BotApiMethod message) {
        this.repeat = repeat;
        this.message = message;
    }

    public boolean repeat() {
        return repeat;
    }

    public BotApiMethod message() {
        return message;
    }

    public boolean hasMessage() {
        return message != null;
    }
}
