package com.euromoney.ConsoleContent.datastore;

public class NegReader implements WordsReader {
    private String raw;
    private static final String[] EMPTY_ARRAY = new String[0];

    public NegReader(String raw) {
        //TODO: check null/empty values
        this.raw = raw;
    }

    public String[] read() {

        if ("".equals(this.raw))
            return EMPTY_ARRAY;
        
        return this.raw.toLowerCase().split(" ");
    }
}
