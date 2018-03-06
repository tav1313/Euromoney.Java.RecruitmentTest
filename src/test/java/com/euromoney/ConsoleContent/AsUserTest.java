package test.java.com.euromoney.ConsoleContent;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsUserTest {
    public ContentEditor editor;

    @Before
    public final void before() {
        this.editor = new ContentEditor("#", null);
    }

    @Test
    public final void negativeWords_nullNegWordsNContent_zeroExpected() throws IOException {
        Assert.assertEquals(editor.negativeWordsCount(null, null), 0);
    }

    @Test
    public final void negativeWords_emptyContent_zeroExpected() throws IOException {
        String[] bw = {"foo"};
        Assert.assertEquals(editor.negativeWordsCount(bw, ""), 0);
    }

    @Test
    public final void negativeWords_emptyNegWords_zeroExpected() throws IOException {
        String[] bw = {};
        Assert.assertEquals(editor.negativeWordsCount(bw, "foo bar"), 0);
    }

    @Test
    public final void negativeWords_oneWord_oneExpected() throws IOException {
        String[] bw = {"foo"};

        Assert.assertEquals(1, editor.negativeWordsCount(bw, "foo"));
    }

    @Test
    public final void negativeWords_twoDiffWords_twoExpected() throws IOException {
        String[] bw = {"foo", "bar"};

        Assert.assertEquals(2, editor.negativeWordsCount(bw, "foo bar"));
    }

    @Test
    public final void negativeWords_oneWordTwoOccurence_twoExpected() throws IOException {
        String[] bw = {"foo"};

        Assert.assertEquals(2, editor.negativeWordsCount(bw, "foo foo"));
    }

    @Test
    public final void negativeWords_oneUpper_oneExpected() throws IOException {
        String[] bw = {"foobar"};

        Assert.assertEquals(1, editor.negativeWordsCount(bw, "FOOBAR"));
    }

    @Test
    public final void negativeWords_oneMixCase_oneExpected() throws IOException {
        String[] bw = {"foobar"};

        Assert.assertEquals(1, editor.negativeWordsCount(bw, "fOObAr"));
    }

    @Test
    public final void negativeWords_oneSpecCharNeg_oneExpected() throws IOException {
        String[] bw = {"foobar", "foo"};

        Assert.assertEquals(1, editor.negativeWordsCount(bw, "fOObAr! foo"));
    }

}
