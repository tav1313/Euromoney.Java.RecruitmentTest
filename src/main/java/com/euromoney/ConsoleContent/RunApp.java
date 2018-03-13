package com.euromoney.ConsoleContent;

import java.io.IOException;

public class RunApp {
    private static final String OFF_FILTERING_OPTION = "-d";

    /**
     * Initialises the application in the
     * console.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 0 && args[0] != null && RunApp.OFF_FILTERING_OPTION.equals(args[0]))
            AppConfig.getInstance().setOffFiltering("true");

        String[] negativeWords = {"swine", "bad", "nasty", "horrible"};
        String content = "The weather in Manchester in winter is bad. It rains all the time - " +
                "it must be horrible for people visiting.";

        ContentEditor editor = new ContentEditor("#", null);

        //===First Story
        editor.negativeWordsCount(negativeWords, content);

        //===Second Story
        //configured using ./data/config.properties wrong words
        editor.negativeWordsCount(content);

        //===Third Story
        editor.filterNegativeWords(content);

    }

}
