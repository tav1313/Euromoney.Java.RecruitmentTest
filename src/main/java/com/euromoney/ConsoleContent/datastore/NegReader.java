package main.java.com.euromoney.ConsoleContent.datastore;

public class NegReader implements WordsReader {
    private String raw;

    public NegReader(String raw) {
        //TODO: check null/empty values
        this.raw = raw;
    }

    public String[] read() {
        return this.raw.toLowerCase().split(" ");
    }
}
