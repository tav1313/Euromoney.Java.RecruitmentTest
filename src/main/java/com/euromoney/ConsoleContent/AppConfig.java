package com.euromoney.ConsoleContent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static AppConfig _instance = new AppConfig();

    public static AppConfig getInstance() {
        return _instance;
    }

    private static String NEGATIVE_WORDS = "NEGATIVE_WORDS";
    private static String OFF_FILTERING = "OFF_FILTERING";

    private Properties properties;

    private AppConfig() {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            //TODO: add logger
            System.out.println("Can't initiate Application Configuration class");
            System.exit(1);
        }
    }

    public String[] getNegativeWords() {
        //TODO check for empty/null value
        String raw = properties.getProperty(AppConfig.NEGATIVE_WORDS);
        return raw.toLowerCase().split(" ");
    }

    public void setOffFiltering(String v) {
        properties.setProperty(AppConfig.OFF_FILTERING, v);
    }

    public boolean getOffFiltering() {
        return Boolean.parseBoolean(properties.getProperty(AppConfig.OFF_FILTERING));
    }

}
