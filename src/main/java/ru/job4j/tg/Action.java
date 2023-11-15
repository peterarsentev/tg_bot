package ru.job4j.tg;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public interface Action {
    Optional<BotApiMethod> handle(Update update);

    default Iterator<? extends Action> bindingActions() {
       return new ArrayList<Action>().iterator();
   }
}
