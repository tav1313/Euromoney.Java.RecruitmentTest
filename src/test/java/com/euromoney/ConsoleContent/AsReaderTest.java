package test.java.com.euromoney.ConsoleContent;

import com.euromoney.ConsoleContent.datastore.NegReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsReaderTest {
    private ContentEditor editor;

    @Before
    public final void before() {
        this.editor = new ContentEditor("#", new NegReader("foo bar foobar f fo baaaaaaaaaaar"));
    }

    @Test
    public final void filterNegativeWords_oneWordTwoOccurrences_twoExpected() {
        final String content = "foo foobar1 foo";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(2, result.getCount());
        Assert.assertEquals("f#o foobar1 f#o", result.getContent());
    }

    @Test
    public final void filterNegativeWords_oneWord_oneExpected() {
        final String content = "foobar foobar1 foobar2";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals("f####r foobar1 foobar2", result.getContent());
    }

    @Test
    public final void filterNegativeWords_twoDiffWords_twoExpected() {
        final String content = "foobar bar1 foo foobareeee foofrom";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(2, result.getCount());
        Assert.assertEquals("f####r bar1 f#o foobareeee foofrom", result.getContent());
    }

    @Test
    public final void filterNegativeWords_oneUpper_oneExpected() {
        final String content = "FOO";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals("F#O", result.getContent());
    }

    @Test
    public final void filterNegativeWords_oneMixCase_oneExpected() {
        final String content = "fOo";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals("f#o", result.getContent());
    }

    @Test
    public final void filterNegativeWords_oneLetterNeg_oneExpected() {
        final String content = "f foobar1 bar";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(2, result.getCount());
        Assert.assertEquals("f foobar1 b#r", result.getContent());
    }

    @Test
    public final void filterNegativeWords_twoLetterNeg_oneExpected() {
        final String content = "fo foobar1";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(1, result.getCount());
        Assert.assertEquals("fo foobar1", result.getContent());
    }

    @Test
    public final void filterNegativeWords_oneLongNeg_twoExpected() {
        final String content = "fo foobar1 baaaaaaaaaaar";
        FilterResult result = this.editor.filterNegativeWords(content);
        Assert.assertEquals(2, result.getCount());
        Assert.assertEquals("fo foobar1 b###########r", result.getContent());
    }
}
