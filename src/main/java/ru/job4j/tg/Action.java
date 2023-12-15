package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.job4j.tg.domain.Resp;

import java.util.ArrayList;
import java.util.Iterator;

public interface Action {
    Resp handle(Update update);

    default Iterator<? extends Action> bindingActions() {
       return new ArrayList<Action>().iterator();
   }
}
