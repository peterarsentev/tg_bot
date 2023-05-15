package ru.job4j.tg;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SessionTg {
    private final Map<String, Map<String, String>> data = new HashMap<>();

    public String get(String chatId, String key, String defValue) {
        return data.getOrDefault(chatId, Collections.emptyMap())
                .getOrDefault(key, defValue);
    }

    public void put(String chatId, String key, String value) {
        data.putIfAbsent(chatId, new HashMap<>());
        var session = data.get(chatId);
        session.put(key, value);
    }
}
