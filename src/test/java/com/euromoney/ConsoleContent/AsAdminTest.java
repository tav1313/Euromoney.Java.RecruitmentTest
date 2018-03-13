package com.euromoney.ConsoleContent;

import com.euromoney.ConsoleContent.datastore.NegReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AsAdminTest {
    public ContentEditor editor;

    @Before
    public final void before() {
        this.editor = new ContentEditor("#",
                new NegReader("foo bar foobar"));
    }

    @Test
    public final void negativeWords_nullContent_zeroExpected() throws IOException {
        Assert.assertEquals(editor.negativeWordsCount(null), 0);
    }

    @Test
    public final void negativeWords_emptyContent_zeroExpected() throws IOException {
        Assert.assertEquals(editor.negativeWordsCount(""), 0);
    }

    @Test
    public final void negativeWords_oneWord_oneExpected() throws IOException {
        Assert.assertEquals(1, editor.negativeWordsCount("foo"));
    }

    @Test
    public final void negativeWords_twoDiffWords_twoExpected() throws IOException {
        Assert.assertEquals(2, editor.negativeWordsCount("foo foobar"));
    }

    @Test
    public final void negativeWords_twoSameWords_twoExpected() throws IOException {
        Assert.assertEquals(2, editor.negativeWordsCount("foo foo"));
    }

    @Test
    public final void negativeWords_oneWordAsSubStart_zeroExpected() throws IOException {
        Assert.assertEquals(1, editor.negativeWordsCount("foobar1 bar"));
    }

    @Test
    public final void negativeWords_oneWordAsSubEnd_oneExpected() throws IOException {
        Assert.assertEquals(1, editor.negativeWordsCount("foo foobar1"));
    }

    @Test
    public final void negativeWords_oneUpper_oneExpected() throws IOException {
        Assert.assertEquals(1, editor.negativeWordsCount("FOOBAR"));
    }

    @Test
    public final void negativeWords_oneMixedCase_oneExpected() throws IOException {
        Assert.assertEquals(1, editor.negativeWordsCount("FooBAR"));
    }
}
