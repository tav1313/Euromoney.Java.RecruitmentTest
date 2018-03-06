package main.java.com.euromoney.ConsoleContent;

import com.euromoney.ConsoleContent.datastore.NegReader;
import com.euromoney.ConsoleContent.datastore.WordsReader;

public class ContentEditor {
    private String HASH_STRING;
    private static final String DEFAULT_HASH_STRING = "#";
    private String[] negativeWords;
    private boolean filteringOffFlag;

    public ContentEditor(String hash_str, WordsReader reader) {
        //TODO: check null values
        if (reader == null) reader = new NegReader("");
        this.HASH_STRING = (hash_str != null) ? hash_str : ContentEditor.DEFAULT_HASH_STRING;
        this.negativeWords = reader.read().length != 0 ? reader.read() : AppConfig.getInstance().getNegativeWords();
        this.filteringOffFlag = AppConfig.getInstance().getOffFiltering();
    }

    public int negativeWordsCount(String[] negativeWords, String content) {
        int negativeCount = 0;

        if (negativeWords == null || negativeWords.length == 0 || content == null) {
            System.out.println("==Scanned text: \n\n" + content + "\n");
            System.out.println("==Total number of negative words: \n\n" + negativeCount + "\n");
            return negativeCount;
        }

        //we do support diff cases filtering/counting
        content = content.toLowerCase();

        for (String currNegWord : negativeWords) {
            int searchIdx = 0;

            //go through all possible occurrences
            while (true) {
                int currSearchIdx = content.indexOf(currNegWord, searchIdx);
                char prevChar = '6'; //could be any value excluding ' '
                boolean isStartOfString = false;
                try {
                    prevChar = content.charAt(currSearchIdx - 1);
                } catch (StringIndexOutOfBoundsException e) {
                    isStartOfString = true;
                }

                if (currSearchIdx != -1) {
                    searchIdx = currSearchIdx + currNegWord.length();
                    if (prevChar == ' ' || isStartOfString) {
                        char nextChar = '6'; //could be anything excluding ' '
                        boolean isEndOfString = false;
                        try {
                            nextChar = content.charAt(searchIdx);
                        } catch (StringIndexOutOfBoundsException e) {
                            isEndOfString = true;
                        }
                        if (nextChar == ' ' || isEndOfString) {
                            ++negativeCount;
                            if (isEndOfString) break;
                        }
                    }
                } else
                    break;
            }
        }

        System.out.println("==Scanned text: \n\n" + content + "\n");
        System.out.println("==Total number of negative words: \n\n" + negativeCount + "\n");

        return negativeCount;
    }

    public int negativeWordsCount(String content) {
        //TODO: check null value
        return negativeWordsCount(this.negativeWords, content);
    }

    public FilterResult filterNegativeWords(String content) {
        int negativeCount = 0;

        if (this.negativeWords == null || negativeWords.length == 0 || content == null) {
            System.out.println("==Scanned text: \n\n" + content + "\n");
            System.out.println("==Total number of negative words: \n\n" + negativeCount + "\n");
            return new FilterResult(negativeCount, content);
        }

        //TODO: check null value
        StringBuffer bufContent = new StringBuffer(content);
        //we do support case-insensitive filtering/counting
        String lowerCaseContent = content.toLowerCase();

        for (String currNegWord : this.negativeWords) {
            int searchIdx = 0;

            //TODO: seems like too complex impl - need to refactor the algorithm using regex
            //TODO: omit code dup after refactoring
            //go through all possible occurrences
            while (true) {
                int currSearchIdx = lowerCaseContent.indexOf(currNegWord, searchIdx);
                char prevChar = '6'; //could be any value excluding ' '
                boolean isStartOfString = false;
                try {
                    prevChar = lowerCaseContent.charAt(currSearchIdx - 1);
                } catch (StringIndexOutOfBoundsException e) {
                    isStartOfString = true;
                }

                if (currSearchIdx != -1) {
                    searchIdx = currSearchIdx + currNegWord.length();

                    if (prevChar == ' ' || isStartOfString) {
                        char nextChar = '6'; //could be anything excluding ' '
                        boolean isEndOfString = false;
                        try {
                            nextChar = lowerCaseContent.charAt(searchIdx);
                        } catch (StringIndexOutOfBoundsException e) {
                            isEndOfString = true;
                        }
                        if (nextChar == ' ' || isEndOfString) {
                            this.hashContent(bufContent, currNegWord, searchIdx, currSearchIdx);
                            ++negativeCount;
                            if (isEndOfString) break;
                        }
                    }
                } else
                    break;
            }
        }

        System.out.println("==Scanned text: \n\n" + bufContent + "\n");
        System.out.println("==Total number of negative words: \n\n" + negativeCount + "\n");

        return new FilterResult(negativeCount, bufContent.toString());
    }

    private void hashContent(StringBuffer bufContent, String currNegWord, int searchIdx, int currSearchIdx) {
        if (!this.filteringOffFlag) {
            StringBuffer hashedString = new StringBuffer("");

            if (currNegWord.length() > 2) {
                for (int jIdx = 0; jIdx < searchIdx - currSearchIdx - 2; jIdx++)
                    hashedString.append(HASH_STRING);
            }

            try {
                bufContent.replace(currSearchIdx + 1, searchIdx - 1, hashedString.toString());
            } catch (StringIndexOutOfBoundsException e) {
            }

        }
    }
}