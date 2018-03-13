package com.euromoney.ConsoleContent;

public class FilterResult {
    private int count;
    private String content;

    public FilterResult(int count, String content) {
        //TODO: check to nulls and empties
        this.count = count;
        this.content = content;
    }

    public int getCount() {
        return this.count;
    }

    public String getContent() {
        return this.content;
    }
}
