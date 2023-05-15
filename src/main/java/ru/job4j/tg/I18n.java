package ru.job4j.tg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class I18n {
    private final Map<String, Map<String, String>> dic = new HashMap<>();

    public void loadDic(String lang) throws IOException {
        var prop = new Properties();
        try (var langPr = Main.class.getClassLoader()
                .getResourceAsStream(String.format("lang_%s.properties", lang))) {
            prop.load(langPr);
        }
        var map = new HashMap<String, String>();
        for (Object name : prop.keySet()) {
            map.put(name.toString(), prop.getProperty(name.toString()));
        }
        dic.put(lang, map);
    }

    public String get(String lang, String key) {
        return dic.get(lang).get(key);
    }
}
