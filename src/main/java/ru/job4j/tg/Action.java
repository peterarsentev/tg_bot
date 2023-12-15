package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.domain.Resp;

public interface Action {
    Resp handle(Update update);
}
